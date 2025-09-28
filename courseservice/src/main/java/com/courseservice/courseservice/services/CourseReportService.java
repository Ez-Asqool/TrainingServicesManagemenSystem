/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseservice.courseservice.services;

import com.courseservice.courseservice.models.Course;
import com.courseservice.courseservice.models.CourseReport;
import com.courseservice.courseservice.repositories.CourseReportRepository;
import com.courseservice.courseservice.vo.AssignedCourse;
import com.courseservice.courseservice.vo.EnrolledCourse;
import com.courseservice.courseservice.vo.Trainee;
import com.courseservice.courseservice.vo.Trainer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author hp
 */
@Service
public class CourseReportService {
    
    @Autowired
    private CourseReportRepository courseReportRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private RestTemplate restTemplate;
    
    public CourseReport addCourseReport(CourseReport courseReport){
        return this.courseReportRepository.save(courseReport);
    }
    
    public CourseReport getCourseReport(Long courseId, Long traineeId){
        CourseReport courseReport = this.courseReportRepository.findByCourseIdAndTraineeId(courseId, traineeId);
        return courseReport;
    }
    
    public void deleteCourseReport(CourseReport courseReport){
        this.courseReportRepository.delete(courseReport);
    }
    
    
    @Async
    public CompletableFuture<List<EnrolledCourse>> getEnrollmentReport(Long traineeId){
        List<CourseReport> enrollmentCourseReport = this.courseReportRepository.findByTraineeId(traineeId);
        List<EnrolledCourse> enrolledCourses = new ArrayList<EnrolledCourse>();
        
        for (CourseReport enrollmentReport : enrollmentCourseReport) {
            Course course = this.courseService.getCourseById(enrollmentReport.getCourseId());
            Trainer trainer = this.restTemplate.getForObject("http://localhost:9001/trainer/"+course.getTrainerId(), Trainer.class);
            enrolledCourses.add(new EnrolledCourse(course.getId(), course.getTitle(), course.getDescription(),
                    course.getDays(), course.getDuration(), course.getStatus(),
                    course.getStartDate(), course.getEndDate(), trainer));
        }
        
        return CompletableFuture.completedFuture(enrolledCourses);
    }
    
    
    @Async
    public CompletableFuture<List<AssignedCourse>> getAssignmentReport(Long trainerId){
        //Give Trainer Courses
        List<Course> trainerCourses = this.courseService.getCoursesByTrainerId(trainerId);
        
        List<AssignedCourse> assignedCourses = new ArrayList<AssignedCourse>();
        
        //Give All EnrollmentReport Assosiated With Course and then, give all trainees in that course.
        for (Course course : trainerCourses) {
            List<CourseReport> courseTrainees = this.courseReportRepository.findByCourseId(course.getId());
            List<Trainee> trainees = new ArrayList<Trainee>();
            
            for (CourseReport courseTrainee : courseTrainees) {
                Trainee trainee = this.restTemplate.getForObject("http://localhost:9002/trainee/"+courseTrainee.getTraineeId(), Trainee.class);
                trainees.add(trainee);
            }
            
            assignedCourses.add(new AssignedCourse(course.getId(), course.getTitle(), course.getDescription(),
                    course.getDays(), course.getDuration(), course.getStatus(),
                    course.getStartDate(), course.getEndDate(), trainees));
        }
        
        return CompletableFuture.completedFuture(assignedCourses);
    }
    
}
