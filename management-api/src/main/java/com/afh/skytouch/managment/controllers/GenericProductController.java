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

    private static final String productAtributeName = "product";
    private static final String creationPage = "create-product";
    private static final String homePage = "home";
    private static final String waitPage = "wait";
    private static final String showProductsPage = "list-products";

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
            model.addAttribute(productAtributeName,message.getProducts());
            return showProductsPage;
        }
        model.addAttribute("method","GET");
        model.addAttribute("location","/genericProduct/showProduct");
        model.addAttribute("id", id);
        return waitPage;
    }

    @GetMapping("/requestProducts")
    public String requestProducts(Model model){
        String id = allMessageSender.sendRequest();
        model.addAttribute("method","GET");
        model.addAttribute("location","/genericProduct/showProduct");
        model.addAttribute("id", id);
        return waitPage;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(productAtributeName) GenericProduct product,Model model){
        System.out.println("Product sended: " + product.getId());
        String id = productSender.sendProduct(product);
        model.addAttribute("method","GET");
        model.addAttribute("location","/genericProduct/status");
        model.addAttribute("id", id);
        return waitPage;
    }

    @GetMapping("/status")
    public String getProductStatus(@ModelAttribute("id") String id,Model model){
        System.out.println("Checking again: " + id);
        if(creationInbox.isMessagePresent(id)){
            ProductStatus status = creationInbox.readMessage(id);
            model.addAttribute("message",status.getMessage());
            return homePage;
        }
        model.addAttribute("method","GET");
        model.addAttribute("location","/genericProduct/status");
        model.addAttribute("id", id);
        return waitPage;
    }

    @GetMapping("/home")
    public String productHome(){
        return homePage;
    }

    @GetMapping("/create")
    public String createProduct(Model model){
        model.addAttribute(productAtributeName,new GenericProduct());
        return creationPage;
    }

}
