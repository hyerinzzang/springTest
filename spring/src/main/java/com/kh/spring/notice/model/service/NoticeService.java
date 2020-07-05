package com.kh.spring.notice.model.service;

import java.util.ArrayList;

import com.kh.spring.notice.model.vo.Notice;

public interface NoticeService {

	ArrayList<Notice> noticeList();

	Notice selectOne(int nId);

	int insertNotice(Notice n);

	int updateNotice(Notice n);

	int deleteNotice(int nId);

}
