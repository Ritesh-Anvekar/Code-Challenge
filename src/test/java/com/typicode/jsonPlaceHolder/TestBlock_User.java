package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


@Feature("JsonPlaceHolder > User > Validations")
class TestBlock_User {

    private static Logger logger = LogManager.getLogger(TestBlock_User.class);
    private User user;

    @BeforeEach
    @Description("Set Up")
    void setUp(){
        user = new User();
    }

    @AfterEach
    @Description("Tear Down")
    void tearDown(){
        this.user.mResponse = null;
    }

    @Test
    @Description("Validate whether the User Name 'Delphine' is Unique")
    void validateUserNameUniqueness() {
        logger.debug("-- validateUserIdUniqueness() --");
        List<String> iUserId = user.extractUserAttribute(user.USER_ID, user.fetchUserDetails(user.USER_USERNAME, "Delphine"));
        SoftAssertions.assertSoftly(softly ->
                softly.assertThat(iUserId.size())
                        .overridingErrorMessage("Expected One User Id, But we found '%s'", iUserId.size())
                        .isEqualTo(1));
    }

    @Test
    @Description("Validate whether the addition of User is successful, though its faked/mocked !!")
    void validateUserAddition() {
        int iLastAllocatedUserId = Integer.parseInt(user.extractLastAllocatedUserId());
        Response responseOnUserAddition = user.addUser("Ritz");
        SoftAssertions.assertSoftly(softly ->
                softly.assertThat(responseOnUserAddition.getStatusLine())
                        .overridingErrorMessage("Expected Status Line 'HTTP/1.1 201 Created', But we found '%s'", responseOnUserAddition.getStatusLine())
                        .containsIgnoringCase("201 Created"));

        String iNewlyAllocatedUserId = String.valueOf(user.extractUserAttribute(user.USER_ID, responseOnUserAddition).get(0));
        SoftAssertions.assertSoftly(softly ->
                softly.assertThat(Integer.parseInt(iNewlyAllocatedUserId))
                        .overridingErrorMessage("Expected User Id '%s', But we found '%s'", iLastAllocatedUserId+1, iNewlyAllocatedUserId)
                        .isEqualTo(iLastAllocatedUserId+1));
    }

    @Test
    @Description("Validate whether the deletion of User is successful, though its faked/mocked !!")
    void validateUserDeletion() {
        Response responseOnUserDeletion = user.deleteUser("10");
        SoftAssertions.assertSoftly(softly ->
                softly.assertThat(responseOnUserDeletion.getStatusLine())
                        .overridingErrorMessage("Expected Status Line 'HTTP/1.1 200 OK', But we found '%s'", responseOnUserDeletion.getStatusLine())
                        .containsIgnoringCase("200 OK"));

    }

}
