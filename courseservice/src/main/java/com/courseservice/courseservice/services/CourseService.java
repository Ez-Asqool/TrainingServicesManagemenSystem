/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseservice.courseservice.services;

import com.courseservice.courseservice.models.Course;
import com.courseservice.courseservice.repositories.CourseRepository;
import com.courseservice.courseservice.vo.ActiveCourse;
import com.courseservice.courseservice.vo.EnrolledCourse;
import com.courseservice.courseservice.vo.Trainer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author hp
 */
@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public List<ActiveCourse> getActiveCoursesWithTrainers(){
        List<Course> activeCourses = this.courseRepository.findByStatus("Active");
        List<ActiveCourse> activeCoursesRes = new ArrayList<ActiveCourse>();
        
        for (Course activeCourse : activeCourses) {
            Trainer trainer = this.restTemplate.getForObject("http://localhost:9001/trainer/"+activeCourse.getTrainerId(), Trainer.class);
            activeCoursesRes.add(
                    new ActiveCourse(activeCourse.getId(), activeCourse.getTitle(), activeCourse.getDescription(),
                    activeCourse.getDays(), activeCourse.getDuration(), 
                    activeCourse.getStartDate(), activeCourse.getEndDate(), trainer));
        }
        
        return activeCoursesRes;
    }
    
    
    
    public Course getCourseById(Long id){
        return this.courseRepository.findById(id).get();
    }
    
    public List<Course> getCoursesByTrainerId(Long trainerId){
        return this.courseRepository.findByTrainerId(trainerId);
    }
    
    
    public Course addCourse(Course course){
        return this.courseRepository.save(course);
    }
    
    public Course updateCourse(Course course){
        Course courseExists = this.getCourseById(course.getId());
        
        courseExists.setTitle(course.getTitle());
        courseExists.setDescription(course.getDescription());
        courseExists.setDays(course.getDays());
        courseExists.setDuration(course.getDuration());
        courseExists.setStartDate(course.getStartDate());
        courseExists.setEndDate(course.getEndDate());
        courseExists.setTrainerId(course.getTrainerId());
        courseExists.setStatus(course.getStatus());
        
        courseRepository.save(courseExists);
        
        return courseExists;
    }
    
    public Course deleteCourse(Course course){
        courseRepository.delete(course);
        return course;
    }
    
    
}
