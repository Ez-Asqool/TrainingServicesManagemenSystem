/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trainerservice.trainerservice.controllers;

import com.trainerservice.trainerservice.models.Trainer;
import com.trainerservice.trainerservice.services.TrainerService;
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
@RequestMapping("/trainer")
public class TrainerController {
    
    @Autowired
    private TrainerService trainerService;
    
    @GetMapping("/")
    public ResponseEntity<List<Trainer>> showAllTrainers(){
        List<Trainer> trainers = this.trainerService.getAllTrainers();
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id){
        Trainer retrievedTrainer = this.trainerService.getTrainerById(id);
        return new ResponseEntity<>(retrievedTrainer, HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer){
        Trainer newTrainer = this.trainerService.addTrainer(trainer);
        return new ResponseEntity<>(newTrainer, HttpStatus.CREATED);
    }
    
}
