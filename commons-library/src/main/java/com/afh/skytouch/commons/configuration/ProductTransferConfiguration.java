package com.afh.skytouch.commons.configuration;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
@EnableRabbit
public class ProductTransferConfiguration {

    private ConnectionFactory rabbitConnectionFactory;

    @Autowired
    public void setRabbitConnectionFactory(ConnectionFactory rabbitConnectionFactory){
        this.rabbitConnectionFactory = rabbitConnectionFactory;
    }

    @Bean
    public Exchange productExchange(){
        return new DirectExchange(QueueProperties.EXCHANGE);
    }

    @Bean
    public Queue createQueue(){
        return new Queue(QueueProperties.CREATE,true);
    }

    @Bean
    public Queue allQueue(){
        return new Queue(QueueProperties.GET_ALL,true);
    }

    @Bean
    public Binding createQueueBinding(){
        return BindingBuilder.bind(createQueue())
                .to(productExchange())
                .with(QueueProperties.CREATE)
                .noargs();
    }

    @Bean
    public Binding getAllQueueBinding(){
        return BindingBuilder.bind(createQueue())
                .to(productExchange())
                .with(QueueProperties.GET_ALL)
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
        template.setExchange(QueueProperties.CREATE);
        template.setRoutingKey(QueueProperties.GET_ALL);
        template.setMessageConverter(converter());
        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
