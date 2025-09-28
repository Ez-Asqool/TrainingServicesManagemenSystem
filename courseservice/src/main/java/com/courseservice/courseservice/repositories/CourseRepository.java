/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.courseservice.courseservice.repositories;

import com.courseservice.courseservice.models.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    
    List<Course> findByStatus(String status);
    
    List<Course> findByTrainerId(Long trainerId);
}
