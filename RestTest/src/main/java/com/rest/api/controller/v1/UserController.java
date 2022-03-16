package com.rest.api.controller.v1;

import com.rest.api.entity.User;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.repository.UserJpaRepo;
import com.rest.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. User"})
@RequiredArgsConstructor    // 클래스 상단에 선언 시 클래스 내부에 final 로 선언된 객체에 대해 Constructor Injection 수행 
@RestController     // 결과 데이터를 JSON 으로 내보냄
@RequestMapping(value = "/v1")  // api resource 를 버전별로 관리하기 위해 /v1을 모든 리소스 주소에 적용되도록 처리
public class UserController {
    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService;

    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다.")
    @GetMapping(value = "/user")
    public ListResult<User> findAllUser(){
        return responseService.getListResult(userJpaRepo.findAll());
    }

    public SingleResult<User> findUserById(@ApiParam(value = "회원ID", required = true) @PathVariable long msrl){
        return responseService.getSingleResult(userJpaRepo.findById(msrl).orElse(null));
    }

    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다.")
    @PostMapping(value = "/user")
    public User save(@ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
                     @ApiParam(value = "회원이름", required = true) @RequestParam String name){
        User user = User.builder()
                .uid(uid)
                .name(name)
                .build();
        return userJpaRepo.save(user);
    }
}
