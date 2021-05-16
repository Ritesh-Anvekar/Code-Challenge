package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import java.util.List;

@Feature("JsonPlaceHolder > User > Validations")
class Test_User {

    private static Logger logger = LogManager.getLogger(Test_User.class);
    private User user = new User();

    @Test
    @Description("Validate whether the User Name 'Delphine' is Unique")
    void validateUserIdUniqueness() {
        logger.debug("-- validateUserIdUniqueness() --");
        List<Integer> iUserId = user.fetchUserId(user.fetchUserDetails("Delphine"));
        SoftAssertions.assertSoftly(softly ->
                softly.assertThat(iUserId.size())
                        .overridingErrorMessage("Expected One User Id, But we found '%s'", iUserId.size())
                        .isEqualTo(1));
    }
}
