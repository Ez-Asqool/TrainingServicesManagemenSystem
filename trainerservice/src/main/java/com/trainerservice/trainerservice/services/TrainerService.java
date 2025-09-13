/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trainerservice.trainerservice.services;

import com.trainerservice.trainerservice.models.Trainer;
import com.trainerservice.trainerservice.repositories.TrainerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class TrainerService {
    
    @Autowired
    private TrainerRepository trainerRepository; 
    
    public List<Trainer> getAllTrainers(){
        return this.trainerRepository.findAll();
    }
    
    
}
