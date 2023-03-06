package com.example.Doctor.controller;

import com.example.Doctor.dao.DoctorRepository;
import com.example.Doctor.model.Doctor;
import com.example.Doctor.model.Patient;
import com.example.Doctor.service.PatientService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class PatientController {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientService service;
    @PostMapping(value="/patient")
    public String savePatient(@RequestBody String patientRequest){

        JSONObject json=new JSONObject(patientRequest);
        Patient patient=setPatient(json);
        service.savePatient(patient);

        return "Saved patient";
    }

    private Patient setPatient(JSONObject json) {
        Patient patient=new Patient();
        patient.setPatientId(json.getInt("patientId"));
        patient.setPatientName(json.getString("patientName"));
        patient.setAge(json.getInt("age"));
        patient.setPhoneNumber(json.getString("phoneNumber"));
        patient.setDiseaseType(json.getString("diseaseType"));
        patient.setGender(json.getString(("gender")));

        Timestamp currTime=new Timestamp(System.currentTimeMillis());
        patient.setAdmitDate(currTime);

        String doctorId=json.getString("doctor");
        Doctor doctor=doctorRepository.findById(Integer.valueOf(doctorId)).get();
        patient.setDoctor(doctor);

        return patient;
    }

    @GetMapping(value="/patient")
    public ResponseEntity getPatients(@Nullable @RequestParam String doctorId,
                                      @Nullable @RequestParam String patientId){
           JSONArray patientDetails=service.getPatient(doctorId,patientId);

           return new ResponseEntity(patientDetails.toString(), HttpStatus.OK);
    }
}
