package com.kh.spring.notice.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.notice.model.vo.Notice;

@Repository("nDao")
public class NoticeDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public ArrayList<Notice> noticeList() {

		return (ArrayList)sqlSessionTemplate.selectList("noticeMapper.selectList");
	}

	public Notice selectOne(int nId) {
		
		return sqlSessionTemplate.selectOne("noticeMapper.selectOne", nId);
	}

	public int insertNotice(Notice n) {

		return sqlSessionTemplate.insert("noticeMapper.insertNotice" , n);
	}

	public int updateNotice(Notice n) {

		return sqlSessionTemplate.update("noticeMapper.updateNotice",n);
	}

	public int deleteNotice(int nId) {

		return sqlSessionTemplate.update("noticeMapper.deleteNotice", nId);
	}

}
