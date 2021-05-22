package com.typicode.jsonplaceholder.util;

import io.qameta.allure.Step;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ResourcesUtil {

    private static Logger logger = LogManager.getLogger(ResourcesUtil.class);

    private ResourcesUtil(){
        //constr
    }

    @Step("Get Resource {0} as String")
    public static String getResourceAsString(String pResource) {
        logger.debug("-- [util_Resources] getResource() --");
        String lResource;
        ClassLoader classLoader = ResourcesUtil.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(pResource);
        try {
            lResource = IOUtils.toString(Objects.requireNonNull(inputStream), String.valueOf(StandardCharsets.UTF_8));
        } catch (Exception eX) {
            throw new ExceptionUtil("Unable to find resource : "+pResource, eX);
        }
        return lResource;
    }

}


