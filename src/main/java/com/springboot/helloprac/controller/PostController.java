package com.springboot.helloprac.controller;


import com.springboot.helloprac.domain.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    public String postExample() {
        return "Hello Post API!";
    }

    @PostMapping(value = "/member")
    //key값이 어떤 타입일지 서버는 모르기 때문에 object타입 으로 생성
    public String postMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder(); // Builder Pattern
        postData.entrySet().forEach(map->sb.append(map.getKey()+":"+map.getValue()+"\n"));
        return sb.toString();
    }

    @PostMapping(value = "/member2")
    public String postMember2(@RequestBody MemberDto md) {
        return md.toString();
    }



}
