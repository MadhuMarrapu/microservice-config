package com.user.service.repositories;

import com.user.service.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //if you want to implement any custom method or query
    //write
}
