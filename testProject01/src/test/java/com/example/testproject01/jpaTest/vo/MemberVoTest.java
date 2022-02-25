package com.example.testproject01.jpaTest.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberVoTest {

    @Test
    public void getId(){
        final MemberVo memberVo = MemberVo.builder()
                .id("feele")
                .name("상필")
                .build();
        final String id = memberVo.getId();
        assertEquals("feele", id);
    }

    @Test
    public void getName(){
        final MemberVo memberVo = MemberVo.builder()
                .id("feele")
                .name("상필")
                .build();
        final String name = memberVo.getName();
        assertEquals("필", name);
    }

}