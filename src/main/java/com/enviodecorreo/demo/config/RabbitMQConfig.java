package com.enviodecorreo.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.queue}")
    private String queue;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    @Value("${app.rabbitmq.pagos.queue}")
    private String pagosQueue;

    @Value("${app.rabbitmq.pagos.exchange}")
    private String pagosExchange;

    @Value("${app.rabbitmq.pagos.routing-key}")
    private String pagosRoutingKey;

    @Bean
    public Queue queue() {
        // durable = true para que no se pierda al reiniciar
        return new Queue(queue, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    // ========= Pagos =========
    @Bean
    public Queue pagosQueue() {
        return new Queue(pagosQueue, true);
    }

    @Bean
    public DirectExchange pagosExchange() {
        return new DirectExchange(pagosExchange);
    }

    @Bean
    public Binding pagosBinding(Queue pagosQueue, DirectExchange pagosExchange) {
        return BindingBuilder.bind(pagosQueue).to(pagosExchange).with(pagosRoutingKey);
    }
}
