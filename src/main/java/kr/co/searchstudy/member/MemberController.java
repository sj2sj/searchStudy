package kr.co.searchstudy.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	
	@ResponseBody
	@GetMapping("/oauth/kakao")
	// 카카오 로그인 시 callback 주소
	public void kakaoLogin(@RequestParam String code) throws Exception {
		System.out.println(code);
		String accessToken = memberService.getKaKaoAccessToken(code);
		memberService.createKakaoUser(accessToken);
	}
	
	@ResponseBody
	@GetMapping("/oauth/kakao/logout")
	public void kakaoLogout() throws Exception {
		memberService.setKaKaoLogout("bkR8BG32zlKaaelRG0QImu1lkN1ykLd8RRKWouTFCj11WwAAAYJD8zbI");
	}
	
}
