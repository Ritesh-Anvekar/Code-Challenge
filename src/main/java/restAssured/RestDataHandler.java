package restAssured;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.util_Properties;
import java.util.List;

public class RestDataHandler {

    private static Logger logger = LogManager.getLogger(RestDataHandler.class);
    protected RequestSpecBuilder requestSpecBuilder;
    protected static String defaultDataFileType = ".json";

    RestDataHandler(){
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(getDefaultedURI());
    }

    @Step("RestDataHandler] Get Defaulted URI")
    private String getDefaultedURI(){
        logger.debug("-- [RestDataHandler] getDefaultedURI() --");
        return util_Properties.loadProperties(util_Properties.APPLICATIONS).getProperty("URI");
    }

    @Step("RestDataHandler] Get Defaulted Data Path")
    protected String getDefaultedDataPath(){
        logger.debug("-- [RestDataHandler] getDefaultedDataPath() --");
        return util_Properties.loadProperties(util_Properties.RESOURCES).getProperty("DATAPATH");
    }

    @Step("RestDataHandler] Get Desired Data Path, With SubFolders : {0}")
    protected String getDataPath(List<String> sSubFolders){
        logger.debug("-- [RestDataHandler] getDataPath() --");
        StringBuilder sDataPath = new StringBuilder().append(util_Properties.loadProperties(util_Properties.RESOURCES).getProperty("DATA_PATH"));
        for (String sSubFolder : sSubFolders) {
            sDataPath.append(sSubFolder).append("/");
        }
        return String.valueOf(sDataPath);
    }
}
