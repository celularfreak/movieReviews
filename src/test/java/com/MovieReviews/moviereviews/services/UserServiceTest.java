package com.MovieReviews.moviereviews.services;

import com.MovieReviews.moviereviews.security.models.Role;
import com.MovieReviews.moviereviews.security.models.User;
import com.MovieReviews.moviereviews.security.repositories.RoleRepository;
import com.MovieReviews.moviereviews.security.repositories.UserRepository;
import com.MovieReviews.moviereviews.security.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_ExistingUsername_ReturnsUserDetails() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        Role role = new Role();
        role.setName("ROLE_USER");
        user.getRoles().add(role);
        when(userRepository.findByUsername(username)).thenReturn(user);

        // Act
        UserDetails userDetails = userService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(user.getUsername(), userDetails.getUsername());
    }

    @Test
    void loadUserByUsername_NonExistingUsername_ThrowsUsernameNotFoundException() {
        // Arrange
        String nonExistingUsername = "nonExistingUser";
        when(userRepository.findByUsername(nonExistingUsername)).thenReturn(null);

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(nonExistingUsername));
    }

    @Test
    void saveUser_ValidUser_ReturnsSavedUser() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User savedUser = userService.saveUser(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getPassword());
    }

    @Test
    void saveRole_ValidRole_ReturnsSavedRole() {
        // Arrange
        Role role = new Role();
        role.setName("ROLE_USER");
        when(roleRepository.save(role)).thenReturn(role);

        // Act
        Role savedRole = userService.saveRole(role);

        // Assert
        assertNotNull(savedRole);
        assertEquals(role.getName(), savedRole.getName());
    }

    @Test
    void addRoleToUser_ValidRoleAndUser_SuccessfullyAddsRoleToUser() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        Role role = new Role();
        role.setName("ROLE_USER");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        when(roleRepository.findByName(role.getName())).thenReturn(role);

        // Act
        userService.addRoleToUser(user.getUsername(), role.getName());

        // Assert
        assertTrue(user.getRoles().contains(role));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUser_ExistingUsername_ReturnsUser() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(user);

        // Act
        User retrievedUser = userService.getUser(username);

        // Assert
        assertNotNull(retrievedUser);
        assertEquals(username, retrievedUser.getUsername());
    }

    @Test
    void getUsers_ReturnsListOfUsers() {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> retrievedUsers = userService.getUsers();

        // Assert
        assertNotNull(retrievedUsers);
        assertEquals(userList.size(), retrievedUsers.size());
    }
}
