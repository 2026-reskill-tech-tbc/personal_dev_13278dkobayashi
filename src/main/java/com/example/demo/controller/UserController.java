package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
	    this.userRepository= userRepository;

	}

	//アカウント新規登録
	@GetMapping("/user/add")
	public String newAccount() {
		return "userAdd";
	}
	
	@GetMapping("/guest")
	public String guest() {
		return "/items";
	}
	
	@PostMapping("/user/add")
	public String addAccount(
			@RequestParam String password,
			@RequestParam String name,
			@RequestParam String address,
			@RequestParam String email,
			Model model) {
		// 名前が空の場合にエラーとする
		if (name == null || name.length() == 0) {
			model.addAttribute("message", "名前を入力してください");
			model.addAttribute("password", password);
			model.addAttribute("address", address);
			model.addAttribute("email", email);
			return "userAdd";
		}
		// パスワードが空の場合にエラーとする
		if (password == null || password.length() == 0) {
			model.addAttribute("message", "パスワードを入力してください");
			model.addAttribute("name", name);
			model.addAttribute("address", address);
			model.addAttribute("email", email);
			return "userAdd";
		}
		// 住所が空の場合にエラーとする
		if (address == null || address.length() == 0) {
			model.addAttribute("message", "住所を入力してください");
			model.addAttribute("name", name);
			model.addAttribute("password", password);
			model.addAttribute("email", email);
			return "userAdd";
		}
		// メールアドレスが空の場合にエラーとする
		if (email == null || email.length() == 0) {
			model.addAttribute("message", "メールアドレスを入力してください");
			model.addAttribute("name", name);
			model.addAttribute("password", password);
			model.addAttribute("address", address);
			return "userAdd";
		}

		User user = new User(name, address, password, email);
		userRepository.save(user);

		model.addAttribute("user", user);
		return "redirect:/login";
	}
	//アカウント情報の表示と設定
	@GetMapping("/user/{id}")
	public String showAccount(
			@PathVariable Integer id,
			Model model) {
		User user = userRepository.findById(id).get();
		
		model.addAttribute("user", user);
		return "userDetail";
	}

	@PostMapping("/user/{id}/update")
	public String updateAccount(
			@PathVariable Integer id,
			@RequestParam String name,
			@RequestParam String address,
			@RequestParam String email,
			Model model) {
		User user = userRepository.findById(id).get();
		user.setName(name);
		user.setAddress(address);
		user.setEmail(email);
		userRepository.save(user);
		model.addAttribute("user", user);
		model.addAttribute("message", "アカウント情報が更新されました。");
		return "userDetail";
	}

	@PostMapping("/user/{id}/delete")
	public String deleteAccount(
			@PathVariable Integer id,
			Model model) {
		userRepository.deleteById(id);
		model.addAttribute("message", "アカウントを削除しました。");
		return "redirect:/login";
	}
}