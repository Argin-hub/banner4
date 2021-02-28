package com.reactjs.banner4.repository;


import com.reactjs.banner4.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
  Category findByName(String name);

  Category findById(int id);

  void deleteById(int id);
}
