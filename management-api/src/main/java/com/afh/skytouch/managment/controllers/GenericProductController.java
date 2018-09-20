package com.afh.skytouch.managment.controllers;

import com.afh.skytouch.commons.dto.FindAllMessage;
import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.commons.dto.ProductStatus;
import com.afh.skytouch.managment.inboxes.QueueInbox;
import com.afh.skytouch.managment.queue.producers.FindAllMessageSender;
import com.afh.skytouch.managment.queue.producers.ProductSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/genericProduct")
public class GenericProductController {

    private static final String PRODUCT_ATRIBUTE_NAME = "product";
    private static final String CREATION_PAGE = "create-product";
    private static final String HOME_PAGE = "home";
    private static final String WAIT_PAGE = "wait";
    private static final String SHOW_PRODUCTS_PAGE = "list-products";

    private ProductSender productSender;

    private FindAllMessageSender allMessageSender;

    private QueueInbox<String,ProductStatus> creationInbox;

    private QueueInbox<String, FindAllMessage> productListInbox;

    @Autowired
    public void setCreationInbox(QueueInbox<String,ProductStatus> creationInbox){
        this.creationInbox = creationInbox;
    }

    @Autowired
    public void setProductListInbox(QueueInbox<String, FindAllMessage>  productListInbox){
        this.productListInbox = productListInbox;
    }

    @Autowired
    public void setProductSender(ProductSender productSender){
        this.productSender = productSender;
    }

    @Autowired
    public void setAllMessageSender(FindAllMessageSender allMessageSender){
        this.allMessageSender = allMessageSender;
    }

    @GetMapping("/showProduct")
    public String getProducts(@ModelAttribute("id") String id,Model model){
        System.out.println("Checking again: " + id);
        if(productListInbox.isMessagePresent(id)){
            FindAllMessage message = productListInbox.readMessage(id);
            model.addAttribute(PRODUCT_ATRIBUTE_NAME,message.getProducts());
            return SHOW_PRODUCTS_PAGE;
        }
        model.addAttribute("method","GET");
        model.addAttribute("location","/genericProduct/showProduct");
        model.addAttribute("id", id);
        return WAIT_PAGE;
    }

    @GetMapping("/requestProducts")
    public String requestProducts(Model model){
        String id = allMessageSender.sendRequest();
        model.addAttribute("method","GET");
        model.addAttribute("location","/genericProduct/showProduct");
        model.addAttribute("id", id);
        return WAIT_PAGE;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(PRODUCT_ATRIBUTE_NAME) GenericProduct product, Model model){
        System.out.println("Product sended: " + product.getId());
        String id = productSender.sendProduct(product);
        model.addAttribute("method","GET");
        model.addAttribute("location","/genericProduct/status");
        model.addAttribute("id", id);
        return WAIT_PAGE;
    }

    @GetMapping("/status")
    public String getProductStatus(@ModelAttribute("id") String id,Model model){
        System.out.println("Checking again: " + id);
        if(creationInbox.isMessagePresent(id)){
            ProductStatus status = creationInbox.readMessage(id);
            model.addAttribute("message",status.getMessage());
            return HOME_PAGE;
        }
        model.addAttribute("method","GET");
        model.addAttribute("location","/genericProduct/status");
        model.addAttribute("id", id);
        return WAIT_PAGE;
    }

    @GetMapping("/home")
    public String productHome(){
        return HOME_PAGE;
    }

    @GetMapping("/create")
    public String createProduct(Model model){
        model.addAttribute(PRODUCT_ATRIBUTE_NAME,new GenericProduct());
        return CREATION_PAGE;
    }

}
