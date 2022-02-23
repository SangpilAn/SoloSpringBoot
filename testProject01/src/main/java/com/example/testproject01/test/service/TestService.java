package com.example.testproject01.test.service;

import com.example.testproject01.test.mapper.TestMapper;
import com.example.testproject01.test.vo.TestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TestMapper mapper;

    public List<TestVo> selectTest(){
        return mapper.selectTest();
    }
}
