package com.typicode.jsonPlaceHolder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Test_User {

    private static Logger logger = LogManager.getLogger(Test_User.class);
    private User user = new User();

    @Test
    void validateUserIdUniqueness() {
        logger.debug("-- validateUserIdUniqueness() --");
        List<Integer> iUserId = user.fetchUserId(user.fetchUserDetails("Delphine"));
        SoftAssertions.assertSoftly(softly ->
                softly.assertThat(iUserId.size())
                        .overridingErrorMessage("Expected One User Id, But we found '%s'", iUserId.size())
                        .isEqualTo(1));
    }

}
