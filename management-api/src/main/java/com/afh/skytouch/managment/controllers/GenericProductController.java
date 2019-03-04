package com.afh.skytouch.managment.controllers;

import com.afh.skytouch.commons.dto.FindAllMessage;
import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.commons.dto.ProductStatus;
import com.afh.skytouch.managment.inboxes.QueueInbox;
import com.afh.skytouch.managment.queue.producers.FindAllMessageSender;
import com.afh.skytouch.managment.queue.producers.ProductSender;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;

@Controller
@RequestMapping("/genericProduct")
public class GenericProductController {

    static final String PRODUCT_ATRIBUTE_NAME = "product";
    static final String CREATION_PAGE = "create-product";
    static final String HOME_PAGE = "home";
    static final String WAIT_PAGE = "wait";
    static final String SHOW_PRODUCTS_PAGE = "list-products";

    private Long timeout;

    private ProductSender productSender;

    private FindAllMessageSender allMessageSender;

    private QueueInbox<String, ProductStatus> creationInbox;

    private QueueInbox<String, FindAllMessage> productListInbox;

    @Autowired
    public void setCreationInbox(QueueInbox<String, ProductStatus> creationInbox) {
        this.creationInbox = creationInbox;
    }

    @Autowired
    public void setProductListInbox(QueueInbox<String, FindAllMessage> productListInbox) {
        this.productListInbox = productListInbox;
    }

    @Value("${management.service.timeout}")
    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    @Autowired
    public void setProductSender(ProductSender productSender) {
        this.productSender = productSender;
    }

    @Autowired
    public void setAllMessageSender(FindAllMessageSender allMessageSender) {
        this.allMessageSender = allMessageSender;
    }

    @GetMapping("/showProduct")
    public String getProducts(@ModelAttribute("id") String id,
                              @ModelAttribute("time") String time,
                              Model model) {
        if (productListInbox.isMessagePresent(id)) {
            FindAllMessage message = productListInbox.readMessage(id).get();
            model.addAttribute(PRODUCT_ATRIBUTE_NAME, message.getProducts());
            return SHOW_PRODUCTS_PAGE;
        }
        return isWaitPage(time, model, id, "GET", "/genericProduct/showProduct");
    }

    @GetMapping("/requestProducts")
    public String requestProducts(@ModelAttribute("time") String time,
                                  Model model) {
        String id = allMessageSender.sendRequest();
        return isWaitPage(time, model, id, "GET", "/genericProduct/showProduct");
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(PRODUCT_ATRIBUTE_NAME) GenericProduct product,
                             @ModelAttribute("time") String time,
                             Model model) {
        String id = productSender.sendProduct(product);
        return isWaitPage(time, model, id, "GET", "/genericProduct/status");
    }


    @GetMapping("/status")
    public String getProductStatus(@ModelAttribute("id") String id,
                                   @ModelAttribute("time") String time,
                                   Model model) {
        if (creationInbox.isMessagePresent(id)) {
            ProductStatus status = creationInbox.readMessage(id).get();
            model.addAttribute("message", status.getMessage());
            return HOME_PAGE;
        }
        return isWaitPage(time, model, id, "GET", "/genericProduct/status");
    }

    @GetMapping("/home")
    public String productHome() {
        return HOME_PAGE;
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        model.addAttribute(PRODUCT_ATRIBUTE_NAME, new GenericProduct());
        return CREATION_PAGE;
    }

    private Long getTime(Long time) {
        return time == null ? Instant.now().toEpochMilli() : time;
    }

    private boolean isTimeout(Long time) {
        if (time == null) {
            return false;
        }

        Long current = Instant.now().toEpochMilli();
        return current - time > timeout;//shame
    }

    private String isWaitPage(@ModelAttribute("time") String time, Model model, String id, String method, String redirect) {
        Long parsedTime = time.isEmpty() ? null : Long.valueOf(time);
        if (!isTimeout(parsedTime)) {
            model.addAttribute("method", method);
            model.addAttribute("location", redirect);
            model.addAttribute("id", id);
            model.addAttribute("time", getTime(parsedTime));
            return WAIT_PAGE;
        }
        model.addAttribute("message", "Service not available try harder");
        return HOME_PAGE;
    }

}
