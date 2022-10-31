package com.springboot.helloprac.parser;

import com.springboot.helloprac.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //SpringBoot가 스캔해서 등록한 Bean을 test코드에서 사용할 수 있도록 해준다(singleton)
class HospitalParserTest {

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\"";


    @Autowired //매번 객체 생성을위해 new를 사용하지 않아도 된다 (singleton)
    ReadLineContext<Hospital> hospitalReadiLineContext;

    @Test
    @DisplayName("10만건 초과 데이터가 파싱되는지 test")
    void oneHundredthousandRows() throws IOException {
        // 서버환경에서 build할 때 문제가 생길 수 있습니다.
        // 어디에서든지 실행할 수 있게 짜는 것이 목표.
        String filename = "/Users/leesuujeen/Desktop/fulldata_01_01_02_P_의원1.csv";
        List<Hospital> hospitalList = hospitalReadiLineContext.readByLine(filename);
        assertTrue(hospitalList.size()>10000);
        assertTrue(hospitalList.size()>100000);
        for (int i = 0; i < 10; i++) {
            System.out.println(hospitalList.get(i).getHospitalName());
        }
        System.out.printf("파싱된 데이터 개수:", hospitalList.size());
    }

    @Test
    @DisplayName("csv 한줄을 hospital로 잘 변경하는지 test")
    void convertToHospital() {
//        HospitalParser hp = new HospitalParser();
//        Hospital hospital = hp.parse("");

        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId()); // col:0
        assertEquals("의원", hospital.getOpenServiceName());//col:1
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode()); // col: 3
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber()); // col:4
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612 //col:5
        assertEquals(1, hospital.getBusinessStatus());  //col:7
        assertEquals(13, hospital.getBusinessStatusCode());  //col:9
        assertEquals("062-515-2875", hospital.getPhone());  //col:
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); //col:18
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());//col:19
        assertEquals("효치과의원", hospital.getHospitalName());  //col:
        assertEquals("치과의원", hospital.getBusinessTypeName());
        assertEquals(1, hospital.getHealthcareProviderCount()); //col:30
        assertEquals(0, hospital.getPatientRoomCount()); //col:31
        assertEquals(0, hospital.getTotalNumberOfBeds()); //col:32
        assertEquals(52.29f, hospital.getTotalAreaSize()); //col:33
    }

}