package com.example.Doctor.service;

import com.example.Doctor.dao.PatientRepository;
import com.example.Doctor.model.Patient;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepository repository;
    public void savePatient(Patient patient) {

        repository.save(patient);
    }

//        public List<Patient> getPatient(String doctorId, String patientId){
//          List<Patient> patients=new ArrayList<>();
//          List<Patient> onlyPatient=new ArrayList<>();
//          if(null!=doctorId){
//              patients=repository.findAll();
//              for(Patient p:patients){
//                  if(p.getDoctorId().getDoctorId()==(Integer.parseInt(doctorId))){
//                      onlyPatient.add(p);
//                  }
//              }
//              return onlyPatient;
//          }
//          else if(patientId!=null){
//              List<Integer> ids=new ArrayList<>();
//              ids.add(Integer.parseInt(patientId));
//              patients=repository.findAllById(ids);
//              return patients;
//          }
//          else if(doctorId==null&&patientId==null){
//              patients=repository.findAll();
//          }
//          return patients;

    public JSONArray getPatient(String doctorId, String patientId){
        List<Patient> patientList=repository.findAll();

        JSONArray patientArr=new JSONArray();

        for(Patient patient:patientList){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("patientId",patient.getPatientId());
            jsonObject.put("patient_name",patient.getPatientName());
            jsonObject.put("age",patient.getAge());
            jsonObject.put("phoneNumber",patient.getPhoneNumber());
            jsonObject.put("diseaseType",patient.getDiseaseType());
            jsonObject.put("gender",patient.getGender());
            jsonObject.put("admitDate",patient.getAdmitDate());
            jsonObject.put("doctorId",patient.getDoctor().getDoctorId());
            patientArr.put(jsonObject);
        }
        return patientArr;
    }

}
