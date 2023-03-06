package com.example.Doctor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Table(name="tbl_doctor")
@Data
@AllArgsConstructor
public class Doctor {
    @Id
    @Column(name="doctor_id")
    private Integer doctorId;

    @Column(name="doctor_name")
    private String doctorName;

    @Column(name="experience")
    private String experience;

    @Column(name="specialized_id")
    private String specializedIn;

    public Doctor(){

    }
}
