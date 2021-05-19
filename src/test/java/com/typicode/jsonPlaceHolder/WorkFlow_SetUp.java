package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class WorkFlow_SetUp {

    User user;
    Post post;
    Comment comment;
    SoftAssertions softAssertions;

    @BeforeEach
    @Description("WorkFlow] SetUp")
    public void workFlowSetUp() {
        comment = new Comment();
        user = new User();
        post = new Post();
        softAssertions = new SoftAssertions();
    }

    @AfterEach
    @Description("WorkFlow] TearDown")
    public void workFlowTearDown() {
        this.user.mResponse = null;
        this.post.mResponse = null;
        this.comment.mResponse = null;
    }
}
