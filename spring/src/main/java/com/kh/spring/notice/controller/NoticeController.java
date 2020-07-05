package com.kh.spring.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.notice.model.service.NoticeService;
import com.kh.spring.notice.model.vo.Notice;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService nService;
	
	// 게시글 목록
	@RequestMapping(value="nlist", method=RequestMethod.GET)
	public ModelAndView noticeList(ModelAndView mv) {
		ArrayList<Notice> list = nService.noticeList();
		
//		System.out.println("con list : " + list);
		
		if(!list.isEmpty()) {
			mv.addObject("list" , list);
			mv.setViewName("notice/noticeListView");
		} else {
			mv.setViewName("notice/notcieListView");
			System.out.println("공지사항 목록 보기 실패");
		}
		
		return mv;
		
	}
	
	// 게시글 상세보기
	@RequestMapping(value="ndetail" , method=RequestMethod.GET)
	public String noticeDetail(Model model, int nId, Notice n) {
		n = nService.selectOne(nId);
//		System.out.println("con n : " + n);
		
		if(n != null) {
			model.addAttribute("notice", n);
		} else {
			System.out.println("공지사항 상세보기 실패");
		}
		return "notice/noticeDetailView";
	}
	
	// 게시판 글쓰기 view
	@RequestMapping(value="nWriterView", method=RequestMethod.GET)
	public String nWriterView() {
		return "notice/noticeWriteForm";
	}
	
	// 게시판 글쓰기 saveFile
	public String saveFile(MultipartFile file, HttpServletRequest request) {
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\nuploadFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		String filePath = folder + "\\" + file.getOriginalFilename();
		
		try {
			file.transferTo(new File(filePath));
		} catch (IllegalStateException | IOException e) {
			System.out.println("파일 전송 에러");
			e.printStackTrace();
		}
		
		return filePath;
	}
	
	
	// 게시판 글쓰기
	@RequestMapping(value="ninsert", method=RequestMethod.POST)
	public String noticeInsert(Notice n , HttpServletRequest request, @RequestParam(name="uploadFile", required=false) MultipartFile file) {
		
		if(!file.getOriginalFilename().contentEquals("")) {	// 파일이 있으면
			String savePath = saveFile(file, request);
			
			if(savePath != null) {
				n.setFilePath(file.getOriginalFilename());
			}
		}
		
		int result = nService.insertNotice(n);
		System.out.println("공지사항 글쓰기 result : " + result);
		if(result <= 0 ) {
			System.out.println("공지사항 등록 실패");
		} 
			return "redirect:nlist";
	}
	
	// 게시글 수정하기 View
	@RequestMapping(value="nupView", method=RequestMethod.GET)
	public String noticeUpdateView(Model model, int nId) {
		model.addAttribute("notice", nService.selectOne(nId));
		return "notice/noticeUpdateView";
	}
	
	// deleteFile
	public void deleteFile(HttpServletRequest request, String fileName) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\nuploadFiles";
		File f = new File(savePath + "\\" + fileName);
		
		if(f.exists()) {
			f.delete();
		}
	}
	
	
	// 게시글 수정하기
	@RequestMapping(value="nupdate", method=RequestMethod.POST)
	public String noticeUpdate(HttpServletRequest request, Notice n,
								@RequestParam(value="reuploadFile", required=false) MultipartFile reuploadFile ) {
		if(reuploadFile != null) {
			if(n.getFilePath() != null) {
				deleteFile(request, n.getFilePath());
			}
		}
		
		String savePath = saveFile(reuploadFile, request);
	
		if(savePath != null) {
			n.setFilePath(reuploadFile.getOriginalFilename());
		}
		
		
		int result = nService.updateNotice(n);
		System.out.println("공지사항 수정하기 result : " + result);
		if(result <= 0) {
			System.out.println("공지사항 업데이트 실패");
		}
		return "redirect:nlist";
	}
	
	// 게시글 삭제하기
	@RequestMapping(value="ndelete", method=RequestMethod.GET)
	public String noticeDelete(int nId, HttpServletRequest request) {
		Notice n = nService.selectOne(nId);
		
		if(n.getFilePath() != null) {
			deleteFile(request, n.getFilePath());
		}
		
		int result = nService.deleteNotice(nId);
		
		if(result <= 0) {
			System.out.println("공지사항 삭제 실패");
		}
		
		return "redirect:nlist";
	}
	
}
