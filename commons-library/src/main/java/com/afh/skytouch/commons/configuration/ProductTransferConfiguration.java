package com.afh.skytouch.commons.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
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
    public static final String findAllRequest = "product.findAllRequest";
    public static final String findAllResponse = "product.findAllResponse";
    public static final String status = "product.status";

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Bean
    public Exchange productExchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue createQueue(){
        return new Queue(create,true);
    }

    @Bean
    public Queue findAllRequestQueue(){ return new Queue(findAllRequest,true); }

    @Bean
    public Queue findAllResponseQueue(){ return new Queue(findAllResponse,true); }

    @Bean
    public Queue statusQueue(){
        return new Queue(status,true);
    }

    @Bean
    public Binding createQueueBinding(){
        return BindingBuilder.bind(createQueue())
                .to(productExchange())
                .with(create)
                .noargs();
    }

    @Bean
    public Binding findAllRequestQueueBinding(){
        return BindingBuilder.bind(findAllRequestQueue())
                .to(productExchange())
                .with(findAllRequest)
                .noargs();
    }

    @Bean
    public Binding findAllResponseQueueBinding(){
        return BindingBuilder.bind(findAllResponseQueue())
                .to(productExchange())
                .with(findAllResponse)
                .noargs();
    }

    @Bean
    public Binding getStatusQueueBinding(){
        return BindingBuilder.bind(statusQueue())
                .to(productExchange())
                .with(status)
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
        template.setMessageConverter(converter());
        return template;
    }

    @Bean(name="statusTemplate")
    public RabbitTemplate statusTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setExchange(exchange);
        template.setRoutingKey(status);
        template.setMessageConverter(converter());
        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
