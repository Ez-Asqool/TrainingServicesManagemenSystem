/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseservice.courseservice.vo;

import java.time.LocalDate;
import java.util.List;
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
public class AssignedCourse {
    private Long id;
    private String title;
    private String description;
    private String days;
    private String duration;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Trainee> trainees;
}
