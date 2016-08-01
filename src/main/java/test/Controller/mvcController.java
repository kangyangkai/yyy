package test.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class mvcController {
	@RequestMapping("/hello")
	public String hello(){
		return "hello";
	}
	
	@RequestMapping("bye")
	public String  bye(){
		return "bye";
	}
}
