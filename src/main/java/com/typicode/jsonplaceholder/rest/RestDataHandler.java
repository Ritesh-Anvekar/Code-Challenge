package com.typicode.jsonplaceholder.rest;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.typicode.jsonplaceholder.util.PropertiesUtil;
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
        return PropertiesUtil.loadProperties(PropertiesUtil.APPLICATIONS).getProperty("URI");
    }

    @Step("RestDataHandler] Get Defaulted Data Path")
    protected String getDefaultedDataPath(){
        logger.debug("-- [RestDataHandler] getDefaultedDataPath() --");
        return PropertiesUtil.loadProperties(PropertiesUtil.RESOURCES).getProperty("DATAPATH");
    }

    @Step("RestDataHandler] Get Desired Data Path, With SubFolders : {0}")
    protected String getDataPath(List<String> sSubFolders){
        logger.debug("-- [RestDataHandler] getDataPath() --");
        StringBuilder sDataPath = new StringBuilder().append(PropertiesUtil.loadProperties(PropertiesUtil.RESOURCES).getProperty("DATA_PATH"));
        for (String sSubFolder : sSubFolders) {
            sDataPath.append(sSubFolder).append("/");
        }
        return String.valueOf(sDataPath);
    }
}
