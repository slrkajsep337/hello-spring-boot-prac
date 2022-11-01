package com.springboot.helloprac.dao;

import com.springboot.helloprac.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component //SpringBootApplication에서 @Component가 달려있는 클래스를 Bean으로 등록한다
//-> 그래서 DaoFactory를 만들어주지 않아도 @Component어노테이션으로 factory역할을 할 수 있다 -> DI가능
public class HospitalDao {
    private final JdbcTemplate jt;

    public HospitalDao(JdbcTemplate jt) {
        this.jt = jt;
    }

    public void add(Hospital hospital) {
        String sql = "INSERT INTO `hospital`.`nation_wide_hospital` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, " +
                "`phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_cnt`, `patient_room_cnt`, `total_number_of_beds`, `total_area_size`) \n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"; //총 16개 column
        this.jt.update(sql, hospital.getId(),
                hospital.getOpenServiceName(),
                hospital.getOpenLocalGovernmentCode(),
                hospital.getManagementNumber(),
                hospital.getLicenseDate(),
                hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(),
                hospital.getPhone(),
                hospital.getFullAddress(),
                hospital.getRoadNameAddress(),
                hospital.getHospitalName(),
                hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(),
                hospital.getPatientRoomCount(),
                hospital.getTotalNumberOfBeds(),
                hospital.getTotalAreaSize());
    }

    public int getCount() {
        String sql = "select count(id) from nation_wide_hospital;";
        return this.jt.queryForObject(sql, Integer.class);
        //queryForObject(a, b) :  a-sql문, b-반환받을 객체의 타입(e.g.) String- String.class)
    }
}
