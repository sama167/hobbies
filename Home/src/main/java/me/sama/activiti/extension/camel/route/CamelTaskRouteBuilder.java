package me.sama.activiti.extension.camel.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * @author SAMA
 * @since 05/23/2018.
 */
public class CamelTaskRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Activiti endpoint
        // camel endpoint:processId:camelServiceTaskId?
        String fromActivitiEndPoint = "activiti:testCamelTask:sendMsgToCamel?copyCamelBodyToBody=true";

        // File endpoints
        String dirSource = "g:/tmp/";
        String dirTarget = dirSource + "target/";
        String fileName = "some.txt";
        String logMsg = "Testing Apache Camel route invocation from an Activiti Camel service task.";
        String logMsg2 = "Testing Apache Camel route invocation from a file.";
        String fromFileEndpoint = String.format("file://%s?fileName=%s&noop=true", dirSource, fileName);
        String toFileEndpoint = String.format("file://%s?fileName=%s", dirTarget, fileName);
        String toBeanEndpoint = "bean:someService?method=process";
        from(fromActivitiEndPoint).log(LoggingLevel.INFO, logMsg).to(toBeanEndpoint);
        from(fromFileEndpoint).log(LoggingLevel.INFO, logMsg2).to(toFileEndpoint);
    }

}
