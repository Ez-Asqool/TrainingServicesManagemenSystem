/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseservice.courseservice.controllers;

import com.courseservice.courseservice.models.Course;
import com.courseservice.courseservice.models.CourseReport;
import com.courseservice.courseservice.services.CourseReportService;
import com.courseservice.courseservice.services.CourseService;
import com.courseservice.courseservice.vo.ActiveCourse;
import com.courseservice.courseservice.vo.ApiResponse;
import com.courseservice.courseservice.vo.AssignedCourse;
import com.courseservice.courseservice.vo.DeleteCourseRequest;
import com.courseservice.courseservice.vo.EnrolledCourse;
import com.courseservice.courseservice.vo.UnEnrollCourseRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/course/")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseReportService courseReportService;
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id){
        Course course = this.courseService.getCourseById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    
    //Add Course 
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCourse(@RequestBody Course course){
        //set corse status and add course
        course.setStatus("Active");
        Course savedCourse = this.courseService.addCourse(course);
        
        //return approperiate response
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus("success");
        apiResponse.setMessage("Course: " + savedCourse.getTitle() + " created successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    
    
    //Update Course 
    @PostMapping("/update/{courseId}")
    public ResponseEntity<ApiResponse> updateCourse(@PathVariable Long courseId, @RequestBody Course course){
        //Check course Id
        Course courseExists;
        try {
             courseExists = this.courseService.getCourseById(courseId);
        } catch (Exception e) {
            //if course not exists , return approperiate response
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus("faild");
            apiResponse.setMessage("There is No Course with Id: "+ courseId);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        
        //update Course
        course.setId(courseId);
        Course updatedCourse = this.courseService.updateCourse(course);
        
        //return approperiate response
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus("success");
        apiResponse.setMessage("Course: " + updatedCourse.getTitle() + " updated successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    
    
    //Delete Course 
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteCourse(@RequestBody DeleteCourseRequest deleteCourseRequest) {
        //Check course Id
        Course courseExists;
        try {
             courseExists = this.courseService.getCourseById(deleteCourseRequest.getCourseId());
        } catch (Exception e) {
            //if course not exists , return approperiate response
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus("faild");
            apiResponse.setMessage("There is No Course with Id: "+ deleteCourseRequest.getCourseId());
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        
        //check trainer Id
        if(courseExists.getTrainerId() != deleteCourseRequest.getTrainerId()){
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus("faild");
            apiResponse.setMessage("Sorry, You Are Not Authorized To Delete This Course.");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        
        //Delete Course
        Course deletedCourse = courseService.deleteCourse(courseExists);
        
        //return approperiate response
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus("success");
        apiResponse.setMessage("Course: " + deletedCourse.getTitle() + " Deleted Successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    
    
    //GetAssignmentReport (Async)
    @GetMapping("/assignmentreport/{trainerId}")
    public CompletableFuture<ResponseEntity<List<AssignedCourse>>> getAssignmentReport(@PathVariable Long trainerId) {
        return courseReportService.getAssignmentReport(trainerId)
        .thenApply(report -> new ResponseEntity<>(report, HttpStatus.OK));
    }
    
    /******* Trainee EndPoints *****/
    
    @GetMapping("/all")
    public ResponseEntity<List<ActiveCourse>> getAllCourses(){
        List<ActiveCourse> activeCourses = this.courseService.getActiveCoursesWithTrainers();
        return new ResponseEntity<>(activeCourses, HttpStatus.OK);
    }
    
    //Enroll Trainee and Add EnrollmentReport
    @PostMapping("/enroll")
    public ResponseEntity<ApiResponse> enrollTraineeInCourse(@RequestBody CourseReport courseReport){
        //Check course Id
        Course courseExists;
        try {
             courseExists = this.courseService.getCourseById(courseReport.getCourseId());
        } catch (Exception e) {
            //if course not exists , return approperiate response
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus("faild");
            apiResponse.setMessage("There is No Course with Id: "+ courseReport.getCourseId());
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        
        
        //set courseReport type and add it.
        this.courseReportService.addCourseReport(courseReport);
        
        //return approperiate response
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus("success");
        apiResponse.setMessage("Course: " + courseExists.getTitle() + " Enrollment Done Successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    
    //UnEnroll Course
    @PostMapping("/unenroll")
    public ResponseEntity<ApiResponse> unEnrollCourse(@RequestBody UnEnrollCourseRequest unEnrollCourseRequest) {
        //Check course Id
        Course courseExists;
        try {
            courseExists = this.courseService.getCourseById(unEnrollCourseRequest.getCourseId());
        } catch (Exception e) {
            //if course not exists , return approperiate response
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus("faild");
            apiResponse.setMessage("There is No Course with Id: "+ unEnrollCourseRequest.getCourseId());
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        
        //check CourseReport (EnrollmentReport)
        CourseReport enrollmentReportExists;
        enrollmentReportExists = this.courseReportService.getCourseReport(
                unEnrollCourseRequest.getCourseId(),unEnrollCourseRequest.getTraineeId());
        if(enrollmentReportExists == null){
            //if enrollment report not exists , return approperiate response
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus("faild");
            apiResponse.setMessage("You Are Not Enrolled In This Course");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
            
        
        //unEnroll Trainee.
        this.courseReportService.deleteCourseReport(enrollmentReportExists);
        
        
        //return approperiate response
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus("success");
        apiResponse.setMessage("UnEnrollment Course Done Successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    
    
    //Get Enrollment Report
    @GetMapping("/enrollmentreport/{traineeId}")
    public CompletableFuture<ResponseEntity<List<EnrolledCourse>>> getEnrollmentReport(@PathVariable Long traineeId) {
        return courseReportService.getEnrollmentReport(traineeId)
        .thenApply(report -> new ResponseEntity<>(report, HttpStatus.OK));
    }
    
}
