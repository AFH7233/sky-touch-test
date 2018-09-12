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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/*@Configuration
@EnableRabbit*/
public class ProductTransferConfiguration {

    public static final String exchange = "product";
    public static final String create = "product.create";
    public static final String status = "product.status";
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
    public Queue getCreateQueue(){
        return new Queue(create,true);
    }

    @Bean
    public Queue getStatusQueue(){
        return new Queue(status,true);
    }

    @Bean
    public Queue getAllQueue(){
        return new Queue(getAll,true);
    }


    @Bean
    public Binding getCreateBinding() {
        return BindingBuilder.bind(getCreateQueue())
                .to(productExchange())
                .with(create);
    }

    @Bean
    public Binding getStatusBinding() {
        return BindingBuilder.bind(getStatusQueue())
                .to(productExchange())
                .with(status);
    }

    @Bean
    public Binding getAllBinding() {
        return BindingBuilder.bind(getAllQueue())
                .to(productExchange())
                .with(getAll);
    }

    @Bean(name="createTemplate")
    public RabbitTemplate createTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setExchange(exchange);
        template.setRoutingKey(create);
        template.setReplyAddress(exchange + "/" + status);
        template.setMessageConverter(converter());
        return template;
    }

    @Bean(name="allTemplate")
    public RabbitTemplate allTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setExchange(exchange);
        template.setRoutingKey(getAll);
        template.setMessageConverter(converter());
        return template;
    }
}
