package com.typicode.jsonplaceholder.util;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertiesUtil {

    private static Logger logger = LogManager.getLogger(PropertiesUtil.class);
    public static final String APPLICATIONS = "application.properties";
    public static final String RESOURCES = "resource.properties";

    public PropertiesUtil(){
        //constr
    }

    @Step("Get Value for Property Name {0}")
    public static Properties loadProperties(String sPropName) {
        logger.debug("-- [util_Properties] loadProperties() --");
        Properties properties = new Properties();
        URL url = ClassLoader.getSystemResource(sPropName);
        try{
            properties.load(url.openStream());
        } catch (IOException IOx) {
            logger.debug("Unable to load property file ! \n" + IOx.getMessage());
            throw new ExceptionUtil(IOx.getCause());
        }
        return properties;
    }
}
