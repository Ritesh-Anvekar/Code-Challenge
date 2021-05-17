package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class util_Properties {

    private static Logger logger = LogManager.getLogger(util_Properties.class);
    public static final String APPLICATIONS = "application.properties";

    public util_Properties(){

    }

    public static Properties loadProperties(String sPropName) {
        Properties properties = new Properties();
        URL url = ClassLoader.getSystemResource(sPropName);
        try{
            properties.load(url.openStream());
        } catch (IOException IOx) {
            logger.info("Unable to load property file ! \n" + IOx.getMessage());
        }
        return properties;
    }
}
