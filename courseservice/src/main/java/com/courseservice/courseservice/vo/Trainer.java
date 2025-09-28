/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseservice.courseservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hp
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
    private String name;
    private Integer age;
    private String email;            
    private String phoneNumber;      
    private String specialization;   
    private int yearsOfExperience;   
    private String bio;
}
