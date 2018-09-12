package com.afh.skytouch.managment.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit
public class ProductTransferConfiguration {

    public static final String exchange = "product";
    public static final String create = "product.create";
    public static final String error = "product.error";
    public static  final String success= "product.success";
    public static  final String getAll = "product.getAll";

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Bean
    public TopicExchange productExchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue createQueue(){
        return new Queue(create,true);
    }

    @Bean
    public Queue errorQueue(){
        return new Queue(error,true);
    }

    @Bean
    public Queue successQueue(){
        return new Queue(success,true);
    }

    @Bean
    public Queue getAllQueue(){
        return new Queue(getAll,true);
    }


    @Bean
    public Binding createBinding() {
        return BindingBuilder.bind(createQueue()).to(productExchange()).with(create);
    }

    @Bean
    public Binding errorBinding() {
        return BindingBuilder.bind(errorQueue()).to(productExchange()).with(error);
    }

    @Bean
    public Binding successBinding() {
        return BindingBuilder.bind(successQueue()).to(productExchange()).with(success);
    }

    @Bean
    public Binding getAllBinding() {
        return BindingBuilder.bind(getAllQueue()).to(productExchange()).with(getAll);
    }

    @Bean
    public RabbitTemplate createTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setExchange(exchange);
        template.setRoutingKey(create);
        template.setMessageConverter(converter());
        return template;
    }

    @Bean
    public RabbitTemplate errorTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setExchange(exchange);
        template.setRoutingKey(error);
        template.setMessageConverter(converter());
        return template;
    }

    @Bean
    public RabbitTemplate successTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setExchange(exchange);
        template.setRoutingKey(success);
        template.setMessageConverter(converter());
        return template;
    }

    @Bean
    public RabbitTemplate getAllTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setExchange(exchange);
        template.setRoutingKey(getAll);
        template.setMessageConverter(converter());
        return template;
    }



}
