package com.test.lotte.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.lotte.model.UserModel;
import com.test.lotte.service.UserService;


@Controller
@RequestMapping({"","/"})
/**
 * 
 * 
 * <pre>
 * com.test.lotte.controller
 * UserController.java
 * </pre>
 * ----------------------
 * @Auth  : 손성준
 * @Since : 2018. 6. 7. 
 * @Desc  : controller
 *
 */
public class UserController {

	@Autowired
	private UserService userService;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7. 
		 * @Method : index
		 * @Desc   : url '/' 로 접속 시 
		 * ----------------------------------- 
		 * @param request
		 * @param response
		 * @param model
		 * @return
		 * @throws Exception : String
		 *
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		model.addAttribute("userList",userService.search());
		return "index";
	}
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : search
		 * @Desc   : 사용자 리스트 조회
		 * ----------------------------------- 
		 * @param request
		 * @param response
		 * @param model
		 * @return
		 * @throws Exception : String
		 *
	 */
	@RequestMapping(value="/user/search", method = RequestMethod.GET)
	public String search(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        model.addAttribute("userList",userService.search());
        return "index";
	}
	
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : insert
		 * @Desc   : 사용자 insert
		 * ----------------------------------- 
		 * @param userModel
		 * @param redirectAttribute
		 * @param model
		 * @return
		 * @throws Exception : String
		 *
	 */
	@RequestMapping(value="/user/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute UserModel userModel, final RedirectAttributes redirectAttribute, Model model) throws Exception{
		userService.insert(userModel);
		model.addAttribute("userList",userService.search());
		
		return "index";
	}
	
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : fileInsert
		 * @Desc   : 파일 업로드 후 사용자 인서트
		 * ----------------------------------- 
		 * @param request
		 * @param files
		 * @return
		 * @throws Exception : String
		 *
	 */
	@RequestMapping(value="/file/insert", method = RequestMethod.POST)
	public String fileInsert(HttpServletRequest request, @RequestPart MultipartFile files,Model model) throws Exception{
		logger.info("up");

		List<UserModel> userList= userService.insertFile(request,files);
		
		for(UserModel user : userList){
			userService.insert(user);
		}
        model.addAttribute("userList",userService.search());
        return "index";
	}
	
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : fileDown
		 * @Desc   : 파일 다운로드
		 * ----------------------------------- 
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception : String
		 *
	 */
	@RequestMapping(value="/file/down", method = RequestMethod.POST)
	public String fileDown(HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("down");
		List<UserModel> userList = userService.downFile();
		userService.writeFile(userList);
        return "index";
	}
}
 