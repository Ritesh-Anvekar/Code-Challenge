package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@Feature("JsonPlaceHolder > User > Validations")
class Test_User extends Suite_SetUp {

    private static Logger logger = LogManager.getLogger(Test_User.class);

    @Test
    @Description("Validate whether the User Name 'Delphine' is Unique")
    void validateUserIdUniqueness() {
        logger.debug("-- validateUserIdUniqueness() --");
        List<Integer> iUserId = user.extractUserIds(user.fetchUserDetails("Delphine"));
        SoftAssertions.assertSoftly(softly ->
                softly.assertThat(iUserId.size())
                        .overridingErrorMessage("Expected One User Id, But we found '%s'", iUserId.size())
                        .isEqualTo(1));
    }

    @Test
    @Description("Validate whether the addition of User is successful, though its faked/mocked !!")
    void addUser() {
        Response responseUser = user.addUser("Ritz");
        SoftAssertions.assertSoftly(softly ->
                softly.assertThat(responseUser.getStatusLine())
                        .overridingErrorMessage("Expected Status Line 'HTTP/1.1 201 Created', But we found '%s'", responseUser.getStatusLine())
                        .containsIgnoringCase("201 Created"));
    }

}
