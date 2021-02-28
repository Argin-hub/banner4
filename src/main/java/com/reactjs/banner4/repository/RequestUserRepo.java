package com.reactjs.banner4.repository;


import com.reactjs.banner4.entity.RequestUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface RequestUserRepo extends JpaRepository<RequestUser, Integer> {

}
