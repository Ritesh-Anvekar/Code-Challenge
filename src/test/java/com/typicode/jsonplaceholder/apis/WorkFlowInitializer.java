package com.typicode.jsonplaceholder.apis;

import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WorkFlowInitializer {

    User user;
    Post post;
    Comment comment;
    SoftAssertions softAssertions;

    @BeforeEach
    @Description("WorkFlow] SetUp")
    public void setUpWorkFlow() {
        comment = new Comment();
        user = new User();
        post = new Post();
        softAssertions = new SoftAssertions();
    }

    @AfterEach
    @Description("WorkFlow] TearDown")
    public void tearDownWorkFlow() {
        this.user = null;
        this.post = null;
        this.comment = null;
    }
}
