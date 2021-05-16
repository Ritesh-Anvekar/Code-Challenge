package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

@Feature("JsonPlaceHolder > Code Challenge")
class Test_CodeChallenge {

    private static Logger logger = LogManager.getLogger(Test_CodeChallenge.class);
    private User user;
    private Post post;
    private Comment comment;

    Test_CodeChallenge(){
        this.user = new User();
        this.post = new Post();
        this.comment = new Comment();
    }

    @Test
    @Description("To perform the validations for the comments for the post made by a specific user")
    void validateEmailsInTheEachPostedCommentsForSpecifiedUser() {
        logger.debug("-- validateEmailsInTheEachPostedCommentsForSpecifiedUser() --");

        String sUserName = "Delphine";
        logger.info("Starting to extract User Id for User Name: '"+sUserName+"'.....");
        List<Integer> iUserId = user.fetchUserId(user.fetchUserDetails(sUserName));
        assertThat(iUserId)
                .overridingErrorMessage("Expected One User Id, But we found '%s'", iUserId.size())
                .hasSizeBetween(1,1);

        logger.info("Starting to extract Posts made by User Id: '"+iUserId+"'.....");
        List <Integer> iPostIds = post.fetchPostIds(post.fetchPosts(iUserId.get(0)));


        List <String> sEmails = null;
        for (Integer iPostId : iPostIds) {
            logger.info("Starting to extract Emails from each comments for Post Id: '"+iPostId+"'.....");
            sEmails = comment.fetchEmailsFromComments(comment.fetchComments(iPostId));
        }

        assertThat(sEmails)
                .overridingErrorMessage("Expected Emails, But we found None")
                .isNotNull();

        logger.info("Starting to validate Email Format.....");

        sEmails.forEach(sEmail ->
                SoftAssertions.assertSoftly(softly ->
                        softly.assertThat(comment.IsEmailFormatCorrect(sEmail)).isTrue()));

    }

}
