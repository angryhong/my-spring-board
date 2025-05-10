// src/main/java/com/example/aiweb/repository/CollectionRepository.java
package com.example.aiweb.repository;

import com.example.aiweb.repository.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
