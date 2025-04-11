package com.diploma.diploma.repositories;

import com.diploma.diploma.models.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomobileRepository  extends JpaRepository<Automobile, Long> {
    List<Automobile> findByUserId(Long userId);
}
