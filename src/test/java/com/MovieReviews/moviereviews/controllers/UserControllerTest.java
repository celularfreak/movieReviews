package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.security.controllers.UserController;
import com.MovieReviews.moviereviews.security.models.User;
import com.MovieReviews.moviereviews.security.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers_ReturnsListOfUsers() {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        when(userService.getUsers()).thenReturn(userList);

        // Act
        List<User> retrievedUsers = userController.getUsers();

        // Assert
        assertEquals(userList.size(), retrievedUsers.size());
    }

    @Test
    void addUser_ValidUser_ReturnsCreatedUser() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        when(userService.saveUser(user)).thenReturn(user);

        // Act
        User createdUser = userController.addUser(user);

        // Assert
        assertEquals(user.getUsername(), createdUser.getUsername());
    }
}
