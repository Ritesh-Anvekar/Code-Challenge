package com.typicode.jsonPlaceHolder;

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
    public void setUp() {
        comment = new Comment();
        user = new User();
        post = new Post();
    }

    @AfterAll
    public void tearDown() {
        //TODO parked for clean-up
    }
}
