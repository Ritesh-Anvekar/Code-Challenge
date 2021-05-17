package restAssured;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.util_Properties;
import java.util.List;

public class RestDataHandler {

    private static Logger logger = LogManager.getLogger(RestDataHandler.class);
    protected static String defaultDataFileType = ".json";

    @Step("RestDataHandler] Get Defaulted Data Path")
    protected String getDefaultedDataPath(){
        logger.debug("-- getDefaultedDataPath() --");
        return util_Properties.loadProperties(util_Properties.RESOURCES).getProperty("DATAPATH");
    }

    @Step("RestDataHandler] Get Desired Data Path, With SubFolders : {0}")
    protected String getDataPath(List<String> sSubFolders){
        logger.debug("-- getDataPath() --");
        StringBuilder sDataPath = new StringBuilder().append(util_Properties.loadProperties(util_Properties.RESOURCES).getProperty("DATA_PATH"));
        for (String sSubFolder : sSubFolders) {
            sDataPath.append(sSubFolder).append("/");
        }
        return String.valueOf(sDataPath);
    }
}
