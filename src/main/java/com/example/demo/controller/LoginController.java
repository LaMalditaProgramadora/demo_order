package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

@Controller
@SessionAttributes("userSession")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/")
	public String goLogin(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute User user,
			BindingResult binRes, HttpServletRequest request,
			Model model) {
		User userAux = userService.findByUsername(user.getUsername());
		model.addAttribute("userSession", userAux);
		
		if(orderService.listOrdersByUserId(userAux.getId()).isEmpty()) {
			Order orderAux = new Order();
			orderAux.setPaid(false);
			orderAux.setUser(userAux);
			orderService.createFirstOrder(orderAux);
			System.out.print("Llegó a crear");
		}
				
		return "redirect:/product/listProducts";
	}
}
