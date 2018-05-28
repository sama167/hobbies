package me.sama.activiti.extension.conf.camel;

import me.sama.activiti.extension.bean.SomeService;
import me.sama.activiti.extension.camel.route.CamelTaskRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SAMA
 * @since 05/23/2018.
 */
@Configuration
public class CamelConfiguration {

    @Bean(name = "camelContext")
    public CamelContext camel() throws Exception{
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("someService", new SomeService());
        CamelContext camelContext = new DefaultCamelContext(registry);
        camelContext.addRoutes(new CamelTaskRouteBuilder());
        camelContext.start();
        return camelContext;
    }
}
