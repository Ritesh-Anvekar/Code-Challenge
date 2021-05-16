package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class util_Basic {

    private static Logger logger = LogManager.getLogger(util_Basic.class);

    public static boolean regExPatternMatcher(String sStringToMatch, String sRegEx) {
        logger.debug("-- [util_Basic] regExPatternMatcher() --");
        Pattern pattern = Pattern.compile(sRegEx);
        Matcher matcher = pattern.matcher(sStringToMatch);
        return matcher.matches();
    }

}
