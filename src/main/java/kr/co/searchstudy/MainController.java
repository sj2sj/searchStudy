package kr.co.searchstudy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

	@RequestMapping("/main")
	public static String goMain() {
		// TODO Auto-generated method stub
		return "contents/main";
	}
	

}
