package mymarket.sample.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;



@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/sample/openSampleList.do")
	public ModelAndView openSampleList(Map<String, Object> commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("");
		log.debug("인터셉터 테스트");
		return mv;
	}
}
