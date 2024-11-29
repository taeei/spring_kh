package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.BCryptPwd;
import common.Common;
import common.Paging;
import dao.VisitDAO;
import vo.VisitVO;

@Controller
public class VisitController {
	
	@Autowired
	ServletContext application;
	
	@Autowired
	HttpServletRequest request;
	
	BCryptPwd bcp;
	public void setBcp(BCryptPwd bcp) {
		this.bcp = bcp;
	}
	
	VisitDAO visit_dao;
	public VisitController(VisitDAO visit_dao) {
		this.visit_dao = visit_dao;
	}
	
	//방명록 조회
	@RequestMapping(value={"/", "visit_list.do"})
	public String selectList(Model model, Integer page) {
		
		//게시글 페이징 처리
		int nowPage = 1;
		if( page != null ) {
			nowPage = page;
		}
		
		int start = (nowPage - 1) * Common.Visit.BLOCKLIST + 1;
		int end = start + Common.Visit.BLOCKLIST - 1;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		List<VisitVO> list = visit_dao.selectList(map);
		
		int row_toal = visit_dao.getRowTotal();
		
		//페이지 메뉴(◀ 1 2 ▶)생성하기
		String pageMenu = Paging.getPaging(
				"visit_list.do", nowPage, row_toal, 
				Common.Visit.BLOCKLIST,
				Common.Visit.BLOCKPAGE);
		
		model.addAttribute("pageMenu", pageMenu);
		model.addAttribute("list", list);
		return Common.Visit.VIEW_PATH + "visit_list.jsp?page="+nowPage;
	}
	
	
	//방명록 추가화면으로 전환
	@RequestMapping("insert_form.do")
	 public String insertForm() {
		return Common.Visit.VIEW_PATH + "insert_form.jsp";
	}
	
	//방명록 추가
	@RequestMapping("insert.do")
	public String insert(VisitVO vo, Model model) {
		
		//파일 업로드
		String webPath = "/resources/upload/"; //상대경로
		String savePath = application.getRealPath(webPath); //절대경로
		
		System.out.println("절대경로 : " + savePath);
		
		//업로드를 위한 파일정보
		MultipartFile photo = vo.getPhoto();
		String filename = "no_file";
		
		//사진이 첨부파일에 담겨서 왔으면
		if( !photo.isEmpty() ) {
			filename = photo.getOriginalFilename();
			
			//저장할 파일의 경로
			File saveFile = new File(savePath, filename);
			if( !saveFile.exists() ) {
				saveFile.mkdirs();
			}else {
				//동일한 이름의 파일이 존재한다면 현재 업로드 시간을 붙여 중복 방지
				long time = System.currentTimeMillis();
				filename = String.format("%d_%s", time, filename);
				saveFile = new File(savePath, filename);
			}
			
			//파일을 절대경로에 생성
			try {
				photo.transferTo(saveFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		vo.setFilename(filename);
		
		int page = 1;
		
		
		//암호화
		String encodePwd = bcp.encryption( vo.getPwd() );
		vo.setPwd(encodePwd);
		
		
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		int res = visit_dao.insert(vo);
		
		return "redirect:visit_list.do?page="+page;
	}
	
	
	//방명록 삭제
	@RequestMapping(value="visit_del.do",
					//produces : json구조로 한글 데이터가 전달될 때 깨지는 것을 방지
					produces="application/json;charset=UTF-8")
	@ResponseBody //return 값을 포워딩이 아닌, 콜백으로 돌려보내느 어노테이션
	public String delete(VisitVO vo, int page, String c_pwd) {
		
		//비밀번호 비교
		boolean isValid = bcp.decryption(vo.getPwd(), c_pwd);
		
		int res = 0;
		
		if( isValid ) {
			res = visit_dao.delete(vo.getIdx());
		}else {
			String pwdCheck = "no_pwd";
			return String.format("[{'result':'%s'}]", pwdCheck);
		}
		
		String result="아니오";
		if(res != 0) {
			result="네";
			
			//절대경로에 있는 파일 삭제
			String webPath = "/resources/upload/"; //상대경로
			String savePath = application.getRealPath(webPath); //절대경로
			
			File deleteFile = new File(savePath, vo.getFilename());
			if( deleteFile.exists() ) {
				deleteFile.delete();
			}
			
		}
		
		String resultStr = String.format("[{'result':'%s'}]", result);
		return resultStr;
	}
	
	
	
	//방명록 수정 화면으로 전환
	@RequestMapping("update_form.do")
	public String updateForm(Model model, VisitVO vo, int page) {
		VisitVO upd_vo = visit_dao.selectOne(vo.getIdx());
		model.addAttribute("vo", upd_vo);
		return Common.Visit.VIEW_PATH + "update_form.jsp?page="+page;
	}
	
	//방명록 수정
	@RequestMapping("update.do")
	public String update(VisitVO vo, int page) {
		
		//바뀐 비밀번호 암호화
		String encodePwd = bcp.encryption(vo.getPwd());
		vo.setPwd(encodePwd);
		
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		//파일 수정
		String webPath = "/resources/upload/"; //상대경로
		String savePath = application.getRealPath(webPath); //절대경로
		
		MultipartFile photo = vo.getPhoto();
		String filename = "no_file";
		
		if( !photo.isEmpty() ) {
			//수정 시 기존 사진이 아닌 새 파일 선택했을 때
			filename = photo.getOriginalFilename();
			
			File saveFile = new File(savePath, filename);
			if( !saveFile.exists() ) {
				saveFile.mkdirs();
				
			}else {
				//이름 중복 확인
				long time = System.currentTimeMillis();
				filename = String.format("%d_%s", time, filename);
				saveFile = new File(savePath, filename);
			}
			
			try {
				photo.transferTo(saveFile);
				
				// 수정 시 원래 절대경로에 담겨있는 파일 삭제
				saveFile = new File(savePath, vo.getFilename());
				if( saveFile.exists() ) {
					saveFile.delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			filename = vo.getFilename();
		}
		
		vo.setFilename(filename);
		int res = visit_dao.update(vo);
		
		return "redirect:visit_list.do?page="+page;
	}
	
	//수정을 위한 비밀번호 체크
	@RequestMapping("modify_password_chk.do")
	@ResponseBody
	public String chkPwd( String pwd, String c_pwd ) {
		
		BCryptPwd bcp = new BCryptPwd();
		boolean isValid = bcp.decryption(pwd, c_pwd);
		
		String result = "no_pwd";
		if( isValid ) {
			result = "yes_pwd";
		}
		
		return result;
	}
	
	
	
}
