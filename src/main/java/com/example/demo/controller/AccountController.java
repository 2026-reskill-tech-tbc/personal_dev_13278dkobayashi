package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

@Controller
public class AccountController {

	private final HttpSession session;
	private final Account account;
	private final UserRepository userRepository;
	
	public AccountController(HttpSession session, Account account, UserRepository userRepository) {
	    this.session = session;
	    this.account = account;
	    this.userRepository= userRepository;
	}

	// ログイン画面を表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index(
		@RequestParam(defaultValue = "") String message
	) {
		// セッション情報を全てクリアする
		session.invalidate();

		return "login";
	}

	// ログインを実行
	@PostMapping("/login")
	public String login(
		@RequestParam String password,
			@RequestParam String name,
			Model model) {


		List<User> userList = userRepository.findByNameAndPassword(name, password);
		if (userList.isEmpty()) {
			model.addAttribute("message", "名前またはパスワードが間違っています");
			model.addAttribute("name", name);
			model.addAttribute("password", password);
			return "login";
		}
		
		// セッション管理されたアカウント情報に名前をセット
		account.setId(userList.get(0).getId());
		account.setName(name);

		// 「/items」へのリダイレクト
		return "redirect:/items";
	}


	// 注文内容確認とお客様情報を表示
	@GetMapping("/order")
	public String index(Model model) {

		if(account.getId()==null) {
			model.addAttribute("message", "ログインしてください");
			return "login";
		}
		User LoginUser = userRepository.findById(account.getId()).get();
		model.addAttribute("user", LoginUser);
		return "customerForm";
	}
}
