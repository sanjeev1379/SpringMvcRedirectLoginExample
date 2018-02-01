package com.nareshit.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.nareshit.domain.Login;
import com.nareshit.service.UserService;
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String showLoginPage(){
		return "/WEB-INF/pages/loginForm.jsp";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
public ModelAndView login(HttpServletRequest request)  {
ModelAndView modelAndView=new ModelAndView();
String status="Login Failure!Please Try Again";
String targetView="login";//this  is acting as URL
	modelAndView.setViewName(targetView);
	modelAndView.addObject("status",status);
String userName=request.getParameter("userName");
String password=request.getParameter("password");
if(userName!=null && userName.trim().length()>0 
&& password!=null && password.trim().length()>0 ){
Login login=new Login();
login.setUserName(userName);
login.setPassword(password);
	login=userService.login(login);
	if(login!=null && login.getUserId()!=null && 
login.getUserId()>0 && login.getUserRole()!=null){
		
	HttpSession session=request.getSession();
	session.setAttribute("userId",login.getUserId());
	session.setAttribute("userRole",login.getUserRole());
	
 if(login.getUserRole().equals("admin")){		
status="welcome ,"+login.getUserName();	
session.setAttribute("status",status);
targetView="adminHome";		//this  is acting as URL
modelAndView=new ModelAndView(new RedirectView(targetView));
 }
else if(login.getUserRole().equals("customer")){
status="welcome ,"+login.getUserName();
targetView="customerHome";//this  is acting as URL
session.setAttribute("status",status);
modelAndView=new ModelAndView(new RedirectView(targetView));

}
	}
}else{
status="UserName and Password can nnot be empty";
String targetViewName="login";//this  is acting as URL
modelAndView=new ModelAndView(targetViewName);
modelAndView.addObject("status",status);
}
return modelAndView;
	}
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
	String targetViewName="/WEB-INF/pages/loginForm.jsp";
	String status="you are Loggedout successfully";
	HttpSession session=request.getSession(false);
	if(session!=null){
	session.invalidate();
	
	}
		return new ModelAndView(targetViewName,"status",status);
	}


}
