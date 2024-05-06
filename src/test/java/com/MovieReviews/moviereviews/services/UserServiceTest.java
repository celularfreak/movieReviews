package com.MovieReviews.moviereviews.service;

import com.MovieReviews.moviereviews.model.User;
import com.MovieReviews.moviereviews.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "John", "john@example.com", "password", 100));
        userList.add(new User(2L, "Alice", "alice@example.com", "password", 150));
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetUserById() {
        User user = new User(1L, "John", "john@example.com", "password", 100);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals("John", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    public void testAddUser() {
        User user = new User(1L, "John", "john@example.com", "password", 100);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.addUser(user);

        assertEquals("John", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    public void testUpdateUser() {
        User user = new User(1L, "John", "john@example.com", "password", 100);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = new User(1L, "John Doe", "john@example.com", "newpassword", 150);
        User result = userService.updateUser(1L, updatedUser);

        assertEquals("John Doe", result.getName());
        assertEquals("newpassword", result.getPassword());
        assertEquals(150, result.getKarma());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
