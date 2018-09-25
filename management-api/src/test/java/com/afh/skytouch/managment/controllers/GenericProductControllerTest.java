package com.afh.skytouch.managment.controllers;

import com.afh.skytouch.commons.dto.FindAllMessage;
import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.commons.dto.ProductStatus;
import com.afh.skytouch.managment.configuration.ManagementApplication;
import com.afh.skytouch.managment.inboxes.QueueInbox;
import com.afh.skytouch.managment.queue.producers.FindAllMessageSender;
import com.afh.skytouch.managment.queue.producers.ProductSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@SpringBootTest(classes = {ManagementApplication.class})
public class GenericProductControllerTest {

    public MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Mock
    private ProductSender producSender;

    @Mock
    private FindAllMessageSender messageSender;

    private QueueInbox<String,ProductStatus> creationInbox;

    private QueueInbox<String, FindAllMessage> productListInbox;

    @InjectMocks
    GenericProductController controller;

    @Before
    public void setUp(){
        creationInbox = new QueueInbox<>();
        productListInbox = new QueueInbox<>();
        controller.setCreationInbox(creationInbox);
        controller.setProductListInbox(productListInbox);
        controller.setTimeout(1000L);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/views/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testHome() throws Exception {
        this.mockMvc.perform(get("/genericProduct/home"))
                    .andExpect(status().isOk())
                    .andExpect(view().name(GenericProductController.HOME_PAGE));

    }

    @Test
    public void testCreate() throws Exception {
        this.mockMvc.perform(get("/genericProduct/create"))
                .andExpect(status().isOk())
                .andExpect(view().name(GenericProductController.CREATION_PAGE));

    }

    @Test
    public void testAddProduct() throws Exception {
        GenericProduct product = new GenericProduct();
        product.setName("test");
        product.setDescription("none");
        this.mockMvc.perform(post("/genericProduct/add")
                .flashAttr(GenericProductController.PRODUCT_ATRIBUTE_NAME,product)
        ).andExpect(status().isOk())
                .andExpect(view().name(GenericProductController.WAIT_PAGE));
    }


    @Test
    public void testStatusNotPresent() throws Exception{
        this.mockMvc.perform(get("/genericProduct/status")
                .flashAttr("id","random")
        )
                .andExpect(status().isOk())
                .andExpect(view().name(GenericProductController.WAIT_PAGE));
    }


    @Test
    public void testStatusPresent() throws Exception{
        ProductStatus status = new ProductStatus();
        status.setProductId("test");
        creationInbox.writeMessage("test",status);

        this.mockMvc.perform(get("/genericProduct/status")
                .flashAttr("id",status.getProductId())
        )
                .andExpect(status().isOk())
                .andExpect(view().name(GenericProductController.HOME_PAGE));
    }

    @Test
    public void testRequestProducts() throws Exception{
        this.mockMvc.perform(get("/genericProduct/requestProducts"))
                .andExpect(status().isOk())
                .andExpect(view().name(GenericProductController.WAIT_PAGE));

    }

    @Test
    public void testProductsPresent() throws Exception{
        List<GenericProduct> products = new ArrayList<>();
        FindAllMessage message = new FindAllMessage();
        message.addAll(products);
        productListInbox.writeMessage(message.getId(),message);

        this.mockMvc.perform(get("/genericProduct/showProduct")
                .flashAttr("id",message.getId())
        )
                .andExpect(status().isOk())
                .andExpect(view().name(GenericProductController.SHOW_PRODUCTS_PAGE));
    }

    @Test
    public void testProductsNotPresent() throws Exception{
        this.mockMvc.perform(get("/genericProduct/showProduct")
                .flashAttr("id","random")
        )
                .andExpect(status().isOk())
                .andExpect(view().name(GenericProductController.WAIT_PAGE));
    }

}