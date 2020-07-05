package com.kh.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	Member loginMember(Member m);

	int insertMember(Member m);

	int checkIdDup(String id);

	int updateMember(Member m);

	int deleteMember(String id);


}
