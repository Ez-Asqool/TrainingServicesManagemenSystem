/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseservice.courseservice.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import java.time.LocalDate;
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
public class ActiveCourse {
    private Long id;
    private String title;
    private String description;
    private String days;
    private String duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private Trainer trainer;
}
