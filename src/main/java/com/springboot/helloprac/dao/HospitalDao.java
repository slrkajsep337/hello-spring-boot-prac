package com.springboot.helloprac.dao;

import com.springboot.helloprac.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component //SpringBootApplication에서 @Component가 달려있는 클래스를 Bean으로 등록한다
//-> 그래서 DaoFactory를 만들어주지 않아도 @Component어노테이션으로 factory역할을 할 수 있다 -> DI가능
public class HospitalDao {
    private final JdbcTemplate jt;

    public HospitalDao(JdbcTemplate jt) {
        this.jt = jt;
    }

    public int add(Hospital hospital) {
        return this.jt.update("INSERT INTO `hospital`.`nation_wide_hospital` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, " +
                "`phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_cnt`, `patient_room_cnt`, `total_number_of_beds`, `total_area_size`) \n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
                hospital.getId(),
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
//        String sql = "INSERT INTO `hospital`.`nation_wide_hospital` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, " +
//                "`phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_cnt`, `patient_room_cnt`, `total_number_of_beds`, `total_area_size`) \n" +
//                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"; //총 16개 column
//        this.jt.update(sql, hospital.getId(),
//                hospital.getOpenServiceName(),
//                hospital.getOpenLocalGovernmentCode(),
//                hospital.getManagementNumber(),
//                hospital.getLicenseDate(),
//                hospital.getBusinessStatus(),
//                hospital.getBusinessStatusCode(),
//                hospital.getPhone(),
//                hospital.getFullAddress(),
//                hospital.getRoadNameAddress(),
//                hospital.getHospitalName(),
//                hospital.getBusinessTypeName(),
//                hospital.getHealthcareProviderCount(),
//                hospital.getPatientRoomCount(),
//                hospital.getTotalNumberOfBeds(),
//                hospital.getTotalAreaSize());
    }

    public int getCount() {
        String sql = "select count(id) from nation_wide_hospital;";
        return this.jt.queryForObject(sql, Integer.class);
        //queryForObject(a, b) :  a-sql문, b-반환받을 객체의 타입(e.g.) String- String.class)
    }

    public int deleteAll() {
        return this.jt.update("delete from nation_wide_hospital;");
    }

    RowMapper<Hospital> rowMapper = (rs, rowNum) -> {
        Hospital hospital = new Hospital();
        hospital.setId(rs.getInt("id"));
        hospital.setOpenServiceName(rs.getString("open_service_name"));
        hospital.setOpenLocalGovernmentCode(rs.getInt("open_local_government_code"));
        hospital.setManagementNumber(rs.getString("management_number"));
        hospital.setLicenseDate(rs.getTimestamp("license_date").toLocalDateTime());
        hospital.setBusinessStatus(rs.getInt("business_status"));
        hospital.setBusinessStatusCode(rs.getInt("business_status_code"));
        hospital.setPhone(rs.getString("phone"));
        hospital.setFullAddress(rs.getString("full_address"));
        hospital.setRoadNameAddress(rs.getString("road_name_address"));
        hospital.setHospitalName(rs.getString("hospital_name"));
        hospital.setBusinessTypeName(rs.getString("business_type_name"));
        hospital.setHealthcareProviderCount(rs.getInt("healthcare_provider_cnt"));
        hospital.setPatientRoomCount(rs.getInt("patient_room_cnt"));
        hospital.setTotalNumberOfBeds(rs.getInt("total_number_of_beds"));
        hospital.setTotalAreaSize(rs.getFloat("total_area_size"));

        return hospital;
    };

    public Hospital findById(int id) {
        return this.jt.queryForObject("select * from nation_wide_hospital where id=?", rowMapper, id);
        //queryForObject(a, b, c) :  a-sql문, b-반환받을 객체의 타입(e.g.) String- String.class), c- ?에 들어갈 값
    }
}
