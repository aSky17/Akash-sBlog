package com.akash.blogApplication.repositories;

import com.akash.blogApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
