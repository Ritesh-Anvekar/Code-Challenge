package util;

import io.qameta.allure.Step;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class util_Resources {

    private static Logger logger = LogManager.getLogger(util_Resources.class);

    private util_Resources(){
        //constr
    }

    @Step("Get Resource {0} as String")
    public static String getResource(String pResource) throws IOException {
        logger.debug("-- [util_Resources] getResource() --");
        String lResource;
        ClassLoader classLoader = util_Resources.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(pResource);
        lResource = IOUtils.toString(Objects.requireNonNull(inputStream), String.valueOf(StandardCharsets.UTF_8));
        return lResource;
    }

}


