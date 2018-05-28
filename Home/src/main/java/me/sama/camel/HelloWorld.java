package me.sama.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.component.rabbitmq.RabbitMQComponent;
import org.apache.camel.component.rabbitmq.RabbitMQConstants;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author SAMA
 * @since 04/18/2018.
 */
public class HelloWorld {

    public static void main(String[] args) {
        CamelContext cc = new DefaultCamelContext();
        cc.addComponent("", new RabbitMQComponent());

        /*
        *
        https://examples.javacodegeeks.com/enterprise-java/apache-camel/apache-camel-hello-world-example/
        * */
    }
}
