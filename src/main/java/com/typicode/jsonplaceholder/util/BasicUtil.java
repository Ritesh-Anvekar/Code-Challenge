package com.typicode.jsonplaceholder.util;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicUtil {

    private static Logger logger = LogManager.getLogger(BasicUtil.class);

    @Step("util_Basic] Match String {0} With Pattern(RegEx) {1}")
    public static boolean regExPatternMatcher(String sStringToMatch, String sRegEx) {
        logger.debug("-- [util_Basic] regExPatternMatcher() --");
        Pattern pattern = Pattern.compile(sRegEx);
        Matcher matcher = pattern.matcher(sStringToMatch);
        return matcher.matches();
    }

}
