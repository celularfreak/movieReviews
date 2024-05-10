package com.MovieReviews.moviereviews.controllers;

import com.MovieReviews.moviereviews.security.controllers.RoleController;
import com.MovieReviews.moviereviews.security.dto.RoleToUserDto;
import com.MovieReviews.moviereviews.security.models.Role;
import com.MovieReviews.moviereviews.security.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RoleControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private RoleController roleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addRole_ValidRole_ReturnsCreatedRole() {
        // Arrange
        Role role = new Role();
        role.setName("ROLE_USER");
        when(userService.saveRole(role)).thenReturn(role);

        // Act
        Role createdRole = roleController.addRole(role);

        // Assert
        assertEquals(role.getName(), createdRole.getName());
    }
}
