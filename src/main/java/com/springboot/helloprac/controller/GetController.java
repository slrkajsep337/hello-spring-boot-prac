package com.springboot.helloprac.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//RestController: 컨트롤러 클래스 하위 메서드에 @ResponseBody 어노테이션이 없어도 문자열과 JSON 등을 전송할 수 있습니다.
@RequestMapping("/api/v1/get-api")
//RequestMapping: 요청(get, put, post 등)과 메소드를 매칭해줌
public class GetController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello world!";
    }

    @GetMapping(value="/variable1/{variable}")
    //get요청과 메소드를 매핑
    public String getVariable1(@PathVariable String variable) {
        return variable;
    }

    @GetMapping(value = "/variable2/{variable}")
    //여기서는 PathVariable어노테이션이 위의 variable변수와 밑의 var변수를 매핑해준다
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

    //PathVariable, RequestParam 둘다 데이터를 전달하기 위해 사용된다. 차이점은?
    @GetMapping(value = "/request1")
    public String getRequestParam(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization) {
        return name+" "+email+" "+" "+organization;
    }

    @GetMapping(value="/request2")
    public String getVariable2(@RequestParam Map<String, String> param) {
        param.entrySet().forEach((map) -> {
            System.out.printf("key: %s value:%s\n", map.getKey(), map.getValue());
        });
        return "request2가 호출 완료 되었습니다";
    }

}
