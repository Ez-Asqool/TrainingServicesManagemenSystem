/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trainerservice.trainerservice.models;

import jakarta.persistence.Entity;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hp
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
    private Long id;
    private String name;
    private Integer age;
    private String email;            
    private String phoneNumber;      
    private String specialization;   
    private int yearsOfExperience;   
    private String bio;
    private LocalDate dateOfBirth;
}
