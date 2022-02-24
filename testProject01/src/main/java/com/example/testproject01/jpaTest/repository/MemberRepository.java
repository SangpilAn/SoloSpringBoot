package com.example.testproject01.jpaTest.repository;

import com.example.testproject01.jpaTest.vo.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberVo, Long> {

    public List<MemberVo> findById(String id);

    public List<MemberVo> findByName(String name);

    public List<MemberVo> findByNameLike(String keyword);

}
