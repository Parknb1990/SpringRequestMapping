package com.javalec.springEx;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.ex.member.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
//	src/main/webapp/WEB-INF/views/board
//	board���� �ȿ� �ִ� content.jsp
	@RequestMapping("/board/content")
	
//	Model��ü�� �̿��� id�� abcdef �Է�
	public String content(Model moder) {
		moder.addAttribute("id", "abcdef");
		return "board/content";
	}
	
//	board���� �ȿ� �ִ� reply.jsp
	@RequestMapping("/board/reply")
	public String reply() {
		return "board/reply";
	}
//	board���� �ȿ� �ִ� view.jsp
	@RequestMapping("/board/view")
	public String view() {
		return "board/view";
	}
	
//	board���� �ȿ� �ִ� confirmId ����
	@RequestMapping("board/confirmId")
	public String confirmId(HttpServletRequest httpServletRequest, Model model) {
		
//		get������� url���� �Է��� ���̵�� �н����带 �ҷ���
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		
//		�ҷ��� ���̵�� �н����� ����
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "board/confirmId";
	}
	
//	board���� �ȿ� �ִ� checkId ����
	@RequestMapping("board/checkId")

//	HttpServletRequest�ʹ� �ٸ� ����� RequestParam ����� �̿�
//	HttpServletRequest�� ������� ���� �������� getParameter�� ������� �ʴ´ٴ� ���� RequestParam����� �̿��ϰԵǸ� url�� ���� �־����� ���� ��,
//	������ ������ ���. <HttpServletRequest����� url�� ���� �Է����� �ʾƵ� �������� ���������� �۵�>
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
		
//		���̵�� �н����� ����
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "board/checkId";
	}
	
//	member������ �ִ� join ����
	@RequestMapping("member/join")

//	command��ü ���
//	com.javalec.ex.member ��Ű���� �ִ� Member class ���
	public String join(Member member) {
		return "member/join";
	}
}
