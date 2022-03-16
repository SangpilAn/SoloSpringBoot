package com.rest.api.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping(value = "/helloworld/string")
    @ResponseBody
    public String helloworldString(){
        return "HelloWorld";
    }

    @GetMapping(value = "helloworld/json")
    @ResponseBody
    public Hello helloworldJson(){
        Hello hello = new Hello();
        hello.message = "HelloWorld";
        return hello;
    }

    @GetMapping(value = "/helloworld/page")
    public String helloworld(){
        return "HelloWorld";
    }

    @Setter
    @Getter
    public static class Hello{
        private String message;
    }

}
