package me.sama.activiti.extension.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SAMA
 * @since 05/23/2018.
 */
public class SomeService {

    private static Logger logger = LoggerFactory.getLogger(SomeService.class);

    public String process() {
        logger.info("Doing some processing...");
        return "Finished processing!";
    }
}
