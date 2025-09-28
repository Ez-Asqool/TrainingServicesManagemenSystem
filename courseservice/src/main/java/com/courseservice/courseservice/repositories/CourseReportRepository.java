/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.courseservice.courseservice.repositories;

import com.courseservice.courseservice.models.CourseReport;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public interface CourseReportRepository extends JpaRepository<CourseReport, Long> {
    
    CourseReport findByCourseIdAndTraineeId(Long courseId, Long traineeId);
    List<CourseReport> findByTraineeId(Long traineeId);
    List<CourseReport> findByCourseId(Long courseId);
}
