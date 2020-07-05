package com.kh.spring.notice.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.notice.model.dao.NoticeDao;
import com.kh.spring.notice.model.vo.Notice;

@Service("nService")
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	NoticeDao nDao;

	@Override
	public ArrayList<Notice> noticeList() {

		return nDao.noticeList();
	}

	@Override
	public Notice selectOne(int nId) {

		return nDao.selectOne(nId);
	}

	@Override
	public int insertNotice(Notice n) {

		return nDao.insertNotice(n);
	}

	@Override
	public int updateNotice(Notice n) {
		
		return nDao.updateNotice(n);
	}

	@Override
	public int deleteNotice(int nId) {
		
		return nDao.deleteNotice(nId);
	}
}
