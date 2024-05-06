package com.MovieReviews.moviereviews.repositories;

import com.MovieReviews.moviereviews.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
