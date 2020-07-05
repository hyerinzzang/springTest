package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository("mDao")
public class MemberDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	public Member loginMember(Member m) {
		
		return sqlSessionTemplate.selectOne("memberMapper.selectOne", m);
	}
	public int insertMember(Member m) {

		return sqlSessionTemplate.insert("memberMapper.memberInsert",m);
	}
	public int checkIdDup(String id) {

		return sqlSessionTemplate.selectOne("memberMapper.checkIdDup", id);
	}
	public int updateMember(Member m) {

		return sqlSessionTemplate.update("memberMapper.memberUpdate", m);
	}
	public int deleteMember(String id) {

		return sqlSessionTemplate.delete("memberMapper.memberDelete", id);
	}
	
	
}
