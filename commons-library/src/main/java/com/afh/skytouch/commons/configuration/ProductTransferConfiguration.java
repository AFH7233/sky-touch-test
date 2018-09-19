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

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Bean
    public Exchange productExchange(){
        return new TopicExchange(QueueProperties.EXCHANGE);
    }

    @Bean
    public Queue createQueue(){
        return new Queue(QueueProperties.CREATE,true);
    }

    @Bean
    public Queue findAllRequestQueue(){ return new Queue(QueueProperties.FIND_ALL_REQUEST,true); }

    @Bean
    public Queue findAllResponseQueue(){ return new Queue(QueueProperties.FIND_ALL_RESPONSE,true); }

    @Bean
    public Queue statusQueue(){
        return new Queue(QueueProperties.STATUS,true);
    }

    @Bean
    public Binding createQueueBinding(){
        return BindingBuilder.bind(createQueue())
                .to(productExchange())
                .with(QueueProperties.CREATE)
                .noargs();
    }

    @Bean
    public Binding findAllRequestQueueBinding(){
        return BindingBuilder.bind(findAllRequestQueue())
                .to(productExchange())
                .with(QueueProperties.FIND_ALL_REQUEST)
                .noargs();
    }

    @Bean
    public Binding findAllResponseQueueBinding(){
        return BindingBuilder.bind(findAllResponseQueue())
                .to(productExchange())
                .with(QueueProperties.FIND_ALL_RESPONSE)
                .noargs();
    }

    @Bean
    public Binding getStatusQueueBinding(){
        return BindingBuilder.bind(statusQueue())
                .to(productExchange())
                .with(QueueProperties.STATUS)
                .noargs();
    }


    @Bean(name="createTemplate")
    public RabbitTemplate createTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setExchange(QueueProperties.EXCHANGE);
        template.setRoutingKey(QueueProperties.CREATE);
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
        template.setExchange(QueueProperties.EXCHANGE);
        template.setRoutingKey(QueueProperties.STATUS);
        template.setMessageConverter(converter());
        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
