package com.example.testproject01.jpaTest.controller;

import com.example.testproject01.jpaTest.repository.MemberRepository;
import com.example.testproject01.jpaTest.service.MemberService;
import com.example.testproject01.jpaTest.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;


//@RunWith(SpringRunner.class) - Junit4 사용 시
@SpringBootTest(
        properties = {
                "testId=feele",
                "testName=상필"
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Transactional
@AutoConfigureMockMvc
@Slf4j
class TestJpaRestControllerTest {

    @Value("${testId}")
    private String testId;

    @Value("${testName}")
    private String testName;

    // Junit4 사용 시 MockBean 테스트에 사용
    /*
    @MockBean
    private MemberRepository memberRepository;
    */

    @Autowired
    MockMvc mvc;

    @Autowired
    private TestRestTemplate restTemplate;

    // Service 로 등록하는 빈
    @Autowired
    private MemberService memberService;

    // MockMvc 한글 깨짐 현상 시 필터 추가 START
    /*
    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach()
    public void setup(){
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .alwaysDo(print())
                .build();
    }
    */
    // MockMvc 한글 깨짐 현상 시 필터 추가 END


    @Test
    void getMember() throws Exception{

        log.info("####### Properties Test ########");
        log.info("testId : " + testId);
        log.info("testName : " + testName);

        //*********** START : MOC MVC test ***********
        log.info("*********** START : MOC MVC test ***********");
        mvc.perform(get("/memberTest/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("feele")))
                .andDo(print());
        log.info("*********** END : MOC MVC test ***********");
        //*********** END : MOC MVC test ***********

        //*********** START : TestRestTemplate test ***********
        log.info("*********** START : TestRestTemplate test ***********");
        ResponseEntity<MemberVo> response = restTemplate.getForEntity("/memberTest/1", MemberVo.class);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        log.info("*********** END : TestRestTemplate test ***********");
        //*********** END : TestRestTemplate test ***********

        //*********** START : MockBean test ***********
        log.info("*********** START : MockBean test ***********");
        
        // Junit4 사용 시
        /*
        MemberVo memberVo = MemberVo.builder()
                .id(testId)
                .name(testName)
                .build();
        
        given(memberRepository.findById(1L))
                .willReturn(Optional.of(memberVo));
        */
        
        Optional<MemberVo> member = memberService.findById(1L);
        if(member.isPresent()){
            // Junit4 사용 시
            // assertThat(memberVO.getId()).isEqualTo(member.get().getId());
            // assertThat(memberVO.getName()).isEqualTo(member.get().getName());
            
            // Junit5 BDD 사용 시
            then("feele").isEqualTo(member.get().getId());
            then("상필").isEqualTo(member.get().getName());
        }
        log.info("*********** END : MockBean test ***********");
        //*********** END : MockBean test ***********

    }
}