package com.springboot.helloprac.controller;


import com.springboot.helloprac.dao.UserDao;
import com.springboot.helloprac.domain.User;
import com.springboot.helloprac.domain.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    //Autowired사용할 때는 final 사용X
    @Autowired
    private UserDao ud;

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

//    @GetMapping("/user")
//    public void add() {
//        ud.add(new User("1", "dd", "1234"));
//    }
//    @GetMapping("/user")
//    public ResponseEntity<Integer> add() {
//        User user = new User("1", "dd", "1234");
//        return ResponseEntity.ok().body(ud.add(user));
//    }

    @GetMapping("/user")
    public ResponseEntity<Integer> add(@RequestBody UserRequestDto urd) {
        User user = new User(urd.getId(), urd.getName(), urd.getPassword());
        return ResponseEntity.ok().body(ud.add(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.ud.findById(id));
    }

//    @DeleteMapping("/user")
//    public void deleteAll() {
//        ud.deleteAll();
//    }

    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteAll() {
        return ResponseEntity.ok().body(ud.deleteAll());

    }









}
