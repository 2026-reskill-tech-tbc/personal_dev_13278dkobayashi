package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
	@GetMapping("/cart")
	public String index() {
		return "cart";
	}
	
	@PostMapping("/cart/add")
	public String addCart(
		@RequestParam Integer itemId,
		@RequestParam Integer quantity,
		Model model) {
	
	return "redirect:/item";
		
	
	}
	
	@PostMapping("/cart/delete")
	public String deleteCart(
			@RequestParam Integer itemId,
			Model model) {
		
		return "redirect:/cart";
	}
	
}
