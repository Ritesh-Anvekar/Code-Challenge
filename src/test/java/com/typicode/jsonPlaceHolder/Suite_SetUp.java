package com.typicode.jsonPlaceHolder;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class Suite_SetUp {

    User user;
    Post post;
    Comment comment;

    @BeforeAll
    @Step("## Suite SetUp ##")
    public void setUp() {
        System.setProperty("Application","jsonplaceholder");
        comment = new Comment();
        user = new User();
        post = new Post();
    }

    @AfterAll
    @Step("## Suite TearDown ##")
    public void tearDown() {
        //TODO parked for clean-up
    }
}
