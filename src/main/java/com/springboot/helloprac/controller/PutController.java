package com.springboot.helloprac.controller;


import com.springboot.helloprac.domain.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    @RequestMapping(value = "/member")
    //key값이 어떤 타입일지 서버는 모르기 때문에 object타입 으로 생성
    public String putMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder(); // Builder Pattern
        postData.entrySet().forEach(map-> {
            sb.append(map.getKey() + ":" + map.getValue() + "\n");
        });
        return sb.toString();
    }

    @PostMapping(value = "/member2")
    public String putMember2(@RequestBody MemberDto md) {
        return md.toString();
    }
    @PutMapping("/member3")
    public MemberDto putMember3(@RequestBody MemberDto md) {
        return md;
    }

    @PutMapping("/member4")
    public ResponseEntity<MemberDto> putMember4(@RequestBody MemberDto memberDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDto);
    }

}
