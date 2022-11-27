package com.my.multiweb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.model.UserVO;
import com.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@GetMapping("/join")
	public String joinForm() {
		
		return "/member/join";
	}
	
	@PostMapping("/join")
	public String joinEnd(Model model, @ModelAttribute("user") UserVO user) {
		
		log.info("user === "+user);
		if(user.getName() == null || user.getUserid() == null || user.getPwd() == null ||
				user.getName().isBlank() || user.getUserid().isBlank() || user.getPwd().isBlank()) {
			
			return "redirect:join";
		}
		
		int n = userService.createUser(user);
		String str = (n>0) ? "회원가입 완료" : "가입실패";
		String loc = (n>0) ? "admin/userList" : "javascript:history.back()";
		model.addAttribute("message", str);
		model.addAttribute("loc", loc);
		
		return "msg";
	}
	
	
	// 아이디 중복체크 ajax처리-----------------
	@GetMapping(value="/idcheck", produces = "application/json")
	@ResponseBody
	public Map<String, String> idCheck(@RequestParam("userid") String userid){
		log.info("userid === "+userid);
		boolean isUse = userService.idCheck(userid);
		String result = (isUse) ? "ok":"no";
		
		Map<String,String> map = new HashMap<>();
		map.put("result", result);
		return map;
		
	}
	
	@GetMapping("/admin/userList")
	public String userList(Model model) {
		List<UserVO> userArr = userService.listUser(null);
		
		model.addAttribute("userArr", userArr);
		return "/member/list";
	}
	
	@PostMapping("/admin/userDel")
	public String userDelete(@RequestParam(defaultValue = "0") int idx) {
		log.info("idx === "+idx);
		if(idx==0) {
			return "redirect:userList";
		}
		int n = userService.deleteUser(idx);
		return "redirect:userList";
	}

}
