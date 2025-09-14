/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.traineeservice.traineeservice.controllers;

import com.traineeservice.traineeservice.models.Trainee;
import com.traineeservice.traineeservice.services.TraineeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/trainee/")
public class TraineeController {
    
    @Autowired
    private TraineeService traineeService;
    
    @GetMapping("/")
    public ResponseEntity<List<Trainee>> getAllTrainees(){
        List<Trainee> trainees = this.traineeService.getAllTrainees();
        return new ResponseEntity<>(trainees, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Trainee> getTraineeById(@PathVariable Long id){
        Trainee trainee = this.traineeService.getTraineeById(id);
        return new ResponseEntity<>(trainee, HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Trainee> addTrainee(@RequestBody Trainee trainee){
        return new ResponseEntity<>(this.traineeService.addTrainee(trainee), HttpStatus.CREATED);
    }
}
