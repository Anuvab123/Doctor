package com.example.Doctor.controller;

import com.example.Doctor.model.Doctor;
import com.example.Doctor.service.DoctorService;
import jakarta.annotation.Nullable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    DoctorService service;
    @PostMapping(value="/doctor")
    public ResponseEntity<String> SaveDoctor(@RequestBody String requestDoctor){
        JSONObject json=new JSONObject(requestDoctor);

        //Doctor doctor= validateDoctor(json);

        List<String> validationList=validateDoctor(json);

//        if(null!=doctor){
//            service.saveDoctor(doctor);
//            return new ResponseEntity<>("Doctor saved", HttpStatus.CREATED);
//        }
//
//        //return service.saveDoctor(doctor);
//        return new ResponseEntity<>("Please pass mandatory parameters", HttpStatus.BAD_REQUEST);

        if(validationList.isEmpty()){
            Doctor doctor=setDoctor(json);
            service.saveDoctor(doctor);
            return new ResponseEntity<>("Doctor saved", HttpStatus.CREATED);
        }else{
            String[] answer= Arrays.copyOf(validationList.toArray(),validationList.size(),String[].class);

            return new ResponseEntity<>("Please pass this mandatory parameters- "+Arrays.toString(answer),HttpStatus.BAD_REQUEST);
        }
    }


    //here we did both findall and findbyid together
    @GetMapping(value="/getDoctor")
    public List<Doctor> getDoctor(@Nullable @RequestParam String doctorId){

        return service.getDoctor(doctorId);
    }

    private List<String> validateDoctor(JSONObject json) {
//        Doctor doctor=new Doctor();
//        if(json.has("doctorId")){
//            String doctorId=json.getString("doctorId");
//            doctor.setDoctorId(Integer.valueOf(doctorId));
//        }else{
//            return null;
//        }
//
//        if(json.has("doctorName")){
//            String doctorName= json.getString("doctorName");
//            doctor.setDoctorName(doctorName);
//        }else{
//            return null;
//        }
//
//        if(json.has("specializedIn")){
//            String specializedIn= json.getString("specializedIn");
//            doctor.setSpecializedIn(specializedIn);
//        }else{
//            return null;
//        }
//
//        if(json.has("experience")){
//            String exp=json.getString("experience");
//            doctor.setExperience(exp);
//        }
//
//        return doctor;
          List<String> errorList=new ArrayList<>();
          if(!json.has("doctorId")){
              errorList.add("doctorId");
          }

        if(!json.has("doctorName")){
            errorList.add("doctorName");
        }

        if(!json.has("specializedIn")){
            errorList.add("specializedIn");
        }

        return errorList;
    }

    public Doctor setDoctor(JSONObject json){
        Doctor doctor=new Doctor();

        String doctorId=json.getString("doctorId");
        doctor.setDoctorId(Integer.valueOf(doctorId));

        String doctorName= json.getString("doctorName");
        doctor.setDoctorName(doctorName);

        String specializedIn= json.getString("specializedIn");
        doctor.setSpecializedIn(specializedIn);

        if(json.has("experience")){
           String exp=json.getString("experience");
           doctor.setExperience(exp);
       }

        return doctor;
    }
}
