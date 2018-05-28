package me.sama.camel;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.Simple;

import java.util.HashMap;
import java.util.Map;

import static org.activiti.camel.ActivitiProducer.PROCESS_KEY_PROPERTY;

/**
 * @author SAMA
 * @since 05/23/2018.
 */
public class ActivitiCamelBuilder extends RouteBuilder {


    private final Helper helper = new Helper();

    @Override
    public void configure() throws Exception {
        from("file:var/activiti-camel/subscription").setBody(bean(helper))
                .setProperty(PROCESS_KEY_PROPERTY, simple("file:name"))
                .to("activit:SubscriptionProcess")
                .log("Process to handle incoming subscription file has been started (process instance id ${body})");

        from("file:var/activiti-camel/delivery")
                .log("Notifying process about delivery for order ${file:name}")
                .setBody(bean(helper))
                .setProperty(PROCESS_KEY_PROPERTY, simple("file:name"))
                .to("activiti:OrderProcess:receiveDelivery");
        from("activiti:OrderSubscription:processSubscription?copyVariablesToProperties=true")
                .log("  original message: ${property.message}")
                .log("Processing order ${property.orderid} created on ${property.timestamp}");

        from("activiti:OrderProcess:processDelivery?copyVariablesToProperties=true")
                .log("  original message: ${property.message}")
                .log("Processing delivery for order ${property.orderid} created on ${property.timestamp}");
    }


    private static final class Helper {


        @Handler
        public Map getProcessVariables(@Body String body,
                                       @Header(Exchange.FILE_NAME) String header,
                                       @Simple("${date:now:yyyy-MM-dd kk:mm:ss}") String timestampe) {
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("message", body);
            vars.put("userId", header);
            vars.put("timestamp", timestampe);
            return vars;
        }

    }
}
