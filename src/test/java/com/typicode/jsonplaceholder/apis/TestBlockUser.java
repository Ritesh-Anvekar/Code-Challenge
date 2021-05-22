package com.typicode.jsonplaceholder.apis;

import java.util.List;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;


@Epic("JsonPlaceHolder > User > Validations")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestBlockUser {

    private static Logger logger = LogManager.getLogger(TestBlockUser.class);
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

    @Order(1)
    @Feature("User Name Should Be Unique")
    @Description("Validate whether the User Name is always unique in the system")
    @ParameterizedTest(name = "Validate User Name : {0}")
    @ValueSource(strings = {"Delphine", "Elwyn.Skiles", "Maxime_Nienow"})
    @DisplayName("Validate User Name Uniqueness")
    void validateUserNameUniqueness(String sUserName) {
        logger.debug("-- validateUserIdUniqueness() --");
        List<String> iUserId = user.extractUserAttribute(user.USER_ID, user.fetchUserDetails(user.USER_USERNAME, sUserName));
        assertThat(iUserId.size())
                .overridingErrorMessage("Expected One User Id, But we found '%s'", iUserId.size())
                .isEqualTo(1);
    }

    @Order(2)
    @Feature("Addition Of User Should Be Allowed")
    @Description("Validate whether the addition of User is successful, though its faked/mocked !!")
    @ParameterizedTest(name = "Validate Addition of User Name: {0}")
    @ValueSource(strings = {"Ritesh.Anvekar", "Chetan.Anvekar"})
    @DisplayName("Validate User Addition")
    void validateUserAddition(String sUserName) {
        int iLastAllocatedUserId = Integer.parseInt(user.extractLastAllocatedUserId());
        Response responseOnUserAddition = user.addUser(sUserName);
        assertThat(responseOnUserAddition.getStatusLine())
                .overridingErrorMessage("Expected Status Line 'HTTP/1.1 201 Created', But we found '%s'", responseOnUserAddition.getStatusLine())
                .containsIgnoringCase("201 Created");

        String iNewlyAllocatedUserId = String.valueOf(user.extractUserAttribute(user.USER_ID, responseOnUserAddition).get(0));
        assertThat(Integer.parseInt(iNewlyAllocatedUserId))
                .overridingErrorMessage("Expected User Id '%s', But we found '%s'", iLastAllocatedUserId + 1, iNewlyAllocatedUserId)
                .isEqualTo(iLastAllocatedUserId + 1);
    }


    @Order(3)
    @Feature("Deletion Of User Should Be Allowed")
    @Description("Validate whether the deletion of User is successful, though its faked/mocked !!")
    @ParameterizedTest(name = "Validate Deletion of User Id: {0}")
    @ValueSource(strings = {"8", "10"})
    @DisplayName("Validate User Deletion")
    void validateUserDeletion(String sUserId) {
        Response responseOnUserDeletion = user.deleteUser(sUserId);
        assertThat(responseOnUserDeletion.getStatusLine())
                .overridingErrorMessage("Expected Status Line 'HTTP/1.1 200 OK', But we found '%s'", responseOnUserDeletion.getStatusLine())
                .containsIgnoringCase("200 OK");

    }

    @Order(4)
    @Feature("User Website Should Be Accessible")
    @Description("Validate whether the User Website is up & running!!")
    @ParameterizedTest(name = "Validate Website for User Name : {0}")
    @ValueSource(strings = {"Delphine", "Karianne"})
    @DisplayName("Validate User Deletion")
    void validateUserWebsite(String sUserName) {
        Response response = user.validateWebsite(sUserName);
        assertThat(response.getStatusLine())
                .overridingErrorMessage("Expected Status Line 'HTTP/1.1 200', But we found '%s'", response.getStatusLine())
                .containsIgnoringCase("200");

    }
}
