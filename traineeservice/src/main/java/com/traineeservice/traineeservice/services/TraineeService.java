/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.traineeservice.traineeservice.services;

import com.traineeservice.traineeservice.Repositories.TraineeRepository;
import com.traineeservice.traineeservice.models.Trainee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class TraineeService {
    
    @Autowired
    private TraineeRepository traineeRepository;
    
    public List<Trainee> getAllTrainees(){
        return this.traineeRepository.findAll();
    }
    
    public Trainee getTraineeById(Long id){
        return this.traineeRepository.findById(id).get();
    }
    
    public Trainee addTrainee(Trainee trainee){
        return this.traineeRepository.save(trainee);
    }
}
