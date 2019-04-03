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
//	board폴더 안에 있는 content.jsp
	@RequestMapping("/board/content")
	
//	Model객체를 이용해 id에 abcdef 입력
	public String content(Model moder) {
		moder.addAttribute("id", "abcdef");
		return "board/content";
	}
	
//	board폴더 안에 있는 reply.jsp
	@RequestMapping("/board/reply")
	public String reply() {
		return "board/reply";
	}
//	board폴더 안에 있는 view.jsp
	@RequestMapping("/board/view")
	public String view() {
		return "board/view";
	}
	
//	board폴더 안에 있는 confirmId 맵핑
	@RequestMapping("board/confirmId")
	public String confirmId(HttpServletRequest httpServletRequest, Model model) {
		
//		get방식으로 url에서 입력한 아이디와 패스워드를 불러옴
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		
//		불러온 아이디와 패스워드 저장
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "board/confirmId";
	}
	
//	board폴더 안에 있는 checkId 맵핑
	@RequestMapping("board/checkId")

//	HttpServletRequest와는 다른 방식인 RequestParam 방식을 이용
//	HttpServletRequest를 사용했을 때와 차이점을 getParameter를 사용하지 않는다는 점과 RequestParam방식을 이용하게되면 url에 값을 넣어주지 않을 때,
//	페이지 에러가 뜬다. <HttpServletRequest방식은 url에 값을 입력하지 않아도 페이지는 정상적으로 작동>
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
		
//		아이디와 패스워드 저장
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "board/checkId";
	}
	
//	member폴더에 있는 join 맵핑
	@RequestMapping("member/join")

//	command객체 사용
//	com.javalec.ex.member 패키지에 있는 Member class 사용
	public String join(Member member) {
		return "member/join";
	}
}
