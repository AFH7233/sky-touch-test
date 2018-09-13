package com.afh.skytouch.managment.configuration;


import org.springframework.amqp.core.*;
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
    public static  final String getAll = "product.getAll";

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Bean
    public Exchange productExchange(){
        return new DirectExchange(exchange);
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
    public Queue allQueue(){
        return new Queue(getAll,true);
    }

    @Bean
    public Binding createQueueBinding(){
        return BindingBuilder.bind(createQueue())
                .to(productExchange())
                .with(create)
                .noargs();
    }

    @Bean
    public Binding getAllQueueBinding(){
        return BindingBuilder.bind(createQueue())
                .to(productExchange())
                .with(getAll)
                .noargs();
    }


    @Bean(name="createTemplate")
    public RabbitTemplate createTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setExchange(exchange);
        template.setRoutingKey(create);
        template.setUseTemporaryReplyQueues(true);
        template.setMessageConverter(converter());
        return template;
    }

    @Bean(name="allTemplate")
    public RabbitTemplate allTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setExchange(exchange);
        template.setRoutingKey(getAll);
        template.setMessageConverter(converter());
        template.setUseTemporaryReplyQueues(true);
        return template;
    }
}
