package com.example.demo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroupEntity, String> {
    List<ProductGroupEntity> findProductGroupByIdRole(String idRole);
}