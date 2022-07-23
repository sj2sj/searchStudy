package kr.co.searchstudy.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

	
	@ResponseBody
	@GetMapping("/oauth/kakao")
	public void kakaoLogin(@RequestParam String code) throws Exception {
		System.out.println(code);
	}
	
}
