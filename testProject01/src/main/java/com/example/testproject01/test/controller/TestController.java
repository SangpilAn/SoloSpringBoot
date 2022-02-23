package com.example.testproject01.test.controller;

import com.example.testproject01.test.service.TestService;
import com.example.testproject01.test.vo.TestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/home")
    public String home(){

        return "index.html";
    }

    @ResponseBody
    @RequestMapping("/valueTest")
    public String valueTest(){
        String value = "테스트 String";
        return value;
    }

    /*@RequestMapping("/test")
    public ModelAndView test() throws Exception{
        ModelAndView mav = new ModelAndView("test");
        mav.addObject("name", "feele");

        List<String> testList = new ArrayList<>();
        testList.add("a");
        testList.add("b");
        testList.add("c");

        mav.addObject("list", testList);
        return mav;
    }*/

    /*@RequestMapping("/thymeleafTest")
    public String thymeleafTest(Model model){
        TestVo testModel = new TestVo("feele", "상필");
        model.addAttribute("testModel", testModel);
        return "thymeleaf/thymeleafTest";
    }*/

    @Autowired
    TestService testService;

    @RequestMapping(value = "/test")
    public ModelAndView test() throws Exception{
        ModelAndView mav = new ModelAndView("test");

        List<TestVo> testList = testService.selectTest();
        mav.addObject("list", testList);

        return mav;
    }

}
