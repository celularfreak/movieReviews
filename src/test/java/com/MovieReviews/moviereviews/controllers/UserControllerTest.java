package com.MovieReviews.moviereviews.controller;

import com.MovieReviews.moviereviews.dto.UserDTO;
import com.MovieReviews.moviereviews.model.User;
import com.MovieReviews.moviereviews.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "john@example.com", "John", "password", 100));
        userList.add(new User(2L, "alice@example.com", "Alice", "password", 150));
        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<UserDTO>> responseEntity = userController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testGetUserById() {
        User user = new User(1L, "john@example.com", "John", "password", 100);
        when(userService.getUserById(1L)).thenReturn(user);

        ResponseEntity<UserDTO> responseEntity = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("john@example.com", responseEntity.getBody().getEmail());
        assertEquals("John", responseEntity.getBody().getName());
    }

    @Test
    public void testAddUser() {
        UserDTO userDTO = new UserDTO("john@example.com", "John", "password", 100);
        User user = new User(1L, "john@example.com", "John", "password", 100);
        when(userService.addUser(any(User.class))).thenReturn(user);

        ResponseEntity<UserDTO> responseEntity = userController.addUser(userDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("john@example.com", responseEntity.getBody().getEmail());
        assertEquals("John", responseEntity.getBody().getName());
    }

    @Test
    public void testUpdateUser() {
        UserDTO userDTO = new UserDTO("john@example.com", "John", "password", 100);
        User user = new User(1L, "john@example.com", "John", "password", 100);
        when(userService.updateUser(anyLong(), any(User.class))).thenReturn(user);

        ResponseEntity<UserDTO> responseEntity = userController.updateUser(1L, userDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("john@example.com", responseEntity.getBody().getEmail());
        assertEquals("John", responseEntity.getBody().getName());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<Void> responseEntity = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
