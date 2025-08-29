package com.kh.app09Security.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestApiController {

    @GetMapping("user")
    public void user() {
        System.out.println("TestApiController.user");
    }

    @GetMapping("admin")
    public void admin() {
        System.out.println("TestApiController.admin");
    }


}
