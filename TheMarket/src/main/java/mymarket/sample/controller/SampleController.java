package mymarket.sample.controller;

/*
 * import java.util.Map; import org.apache.log4j.Logger; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * @Controller public class SampleController { Logger log =
 * Logger.getLogger(this.getClass());
 * 
 * @RequestMapping(value = "/sample/openSampleList.do") public ModelAndView
 * openSampleList(Map<String, Object> commandMap) throws Exception {
 * ModelAndView mv = new ModelAndView(""); log.debug("인터셉터 테스트");
 * 
 * return mv; } }
 */

import java.util.*;
import java.util.Map.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mymarket.common.common.CommandMap;
import mymarket.sample.service.SampleService;

@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "sampleService")
	private SampleService sampleService;

	@RequestMapping(value = "/sample/testMapArgumentResolver.do")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("");
		if (commandMap.isEmpty() == false) {
			Iterator<Entry<String, Object>> iterator = commandMap.getMap().entrySet().iterator();
			Entry<String, Object> entry = null;
			while (iterator.hasNext()) {
				entry = iterator.next();
				log.debug("key : " + entry.getKey() + ", value : " + entry.getValue());
			}
		}
		return mv;
	}

	@RequestMapping(value = "sample/openBoardList.do")
	public ModelAndView openSampleBoardList(Map<String, Object> commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/boardList");
		// Eclipse에서 구동할 때는 임시 파일 문제로 복사가 안 되어서 안나올 수 도 있다.
		List<Map<String, Object>> list = sampleService.selectBoardList(commandMap);
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(value = "/sample/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardDetail");
		Map<String, Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		return mv;
	}

	@RequestMapping(value = "/sample/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		sampleService.insertBoard(commandMap.getMap(), request);
		return mv;
	}

	@RequestMapping(value = "/sample/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/boardWrite");
		return mv;
	}
	
	@RequestMapping(value = "/sample/index.do")
	public ModelAndView anotherindex(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/index");
		//Temp 2nd Index
		return mv;
	}
	
	@RequestMapping(value = "/sample/contact.do")
	public ModelAndView contactpage(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/contact");
		//contact
		return mv;
	}
	
	@RequestMapping(value = "/sample/checkouttest.do")
	public ModelAndView checkouttest(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/checkout");
		//page test - checkout
		return mv;
	}
	
	@RequestMapping(value = "/sample/checkout.do")
	public ModelAndView checkout(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/checkout");
		//service - selectcartinfo
		return mv;
	}
	
	@RequestMapping(value = "/sample/carttest.do")
	public ModelAndView carttest(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/cart");
		//page test - checkout
		return mv;
	}
	
	@RequestMapping(value = "/sample/cart.do")
	public ModelAndView cart(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/cart");
		//service - selectcartinfo
		return mv;
	}
	
	@RequestMapping(value = "/sample/cartsave.do")
	public ModelAndView cartsave(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/checkout");
		//service - updatecartinfo
		return mv;
	}
	
	@RequestMapping(value = "/sample/cleancart.do")
	public ModelAndView cleancart(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/checkout");
		//service - updatecartinfo
		return mv;
	}
	
	@RequestMapping(value = "/sample/categorytest.do")
	public ModelAndView categorytest(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/category");
		//page test - checkout
		return mv;
	}
	
	@RequestMapping(value = "/sample/category.do")
	public ModelAndView category(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/category");
		//service - selectproductlistwithcategory
		return mv;
	}
	
	
	@RequestMapping(value = "/sample/producttest.do")
	public ModelAndView producttest(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/product");
		//page test - checkout
		return mv;
	}
	
	@RequestMapping(value = "/sample/product.do")
	public ModelAndView product(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/product");
		//service - selectproduct
		return mv;
	}
	
	@RequestMapping(value = "/sample/addproduct.do")
	public ModelAndView addproduct(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("sample/product");
		//service - selectproduct
		return mv;
	}
	
	

	@RequestMapping(value = "/sample/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardUpdate");
		Map<String, Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		return mv;
	}

	@RequestMapping(value = "/sample/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail.do");
		sampleService.updateBoard(commandMap.getMap(), request);
		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}

	@RequestMapping(value = "/sample/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		sampleService.deleteBoard(commandMap.getMap());
		return mv;
	}

}
