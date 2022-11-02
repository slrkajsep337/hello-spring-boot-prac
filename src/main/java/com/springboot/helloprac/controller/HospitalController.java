package com.springboot.helloprac.controller;


import com.springboot.helloprac.dao.HospitalDao;
import com.springboot.helloprac.domain.Hospital;
import com.springboot.helloprac.domain.dto.HospitalRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class HospitalController {

    @Autowired
    private HospitalDao hd;

    //왜 Integer로 바꿨는지 ?
    @GetMapping("/hospital")
    public ResponseEntity<Integer> add(@RequestBody HospitalRequestDto hrd) {
        Hospital hospital = new Hospital(hrd.getId(), hrd.getOpenServiceName(), hrd.getOpenLocalGovernmentCode(),
                hrd.getManagementNumber(), hrd.getLicenseDate(), hrd.getBusinessStatus(), hrd.getBusinessStatusCode(),
                hrd.getPhone(), hrd.getFullAddress(), hrd.getRoadNameAddress(), hrd.getHospitalName(),
                hrd.getBusinessTypeName(), hrd.getHealthcareProviderCount(), hrd.getPatientRoomCount(),
                hrd.getTotalNumberOfBeds(), hrd.getTotalAreaSize());
        return ResponseEntity.ok().body(hd.add(hospital));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(this.hd.findById(id));
    }

    @DeleteMapping("/hospital")
    public ResponseEntity<Integer> deleteAll() {
        return ResponseEntity.ok().body(hd.deleteAll());
    }
}
