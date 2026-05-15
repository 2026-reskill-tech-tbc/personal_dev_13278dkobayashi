package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.model.Cart;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class OrderController {

	private final Cart cart;
	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;
	
	
	public OrderController(
	        Cart cart,
	        UserRepository userRepository,
	        OrderRepository orderRepository,
	        OrderDetailRepository orderDetailRepository) {
	    this.cart = cart;
	    this.orderRepository = orderRepository;
	    this.orderDetailRepository = orderDetailRepository;
	}

	// 注文する
	@PostMapping("/order")
	public String doOrder(
			@RequestParam int userId,
			Model model) {
		
		if ( cart.getItems() == null) {
			model.addAttribute("message", "カートに何も入っていません");
			return "cart";
		}

		// 2. 注文情報をDBに格納する
		Order order = new Order(
				userId,
				cart.getTotalPrice());
		orderRepository.save(order);

		// 3. 注文詳細情報をDBに格納する
		List<Item> itemList = cart.getItems();
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (Item item : itemList) {
			orderDetails.add(
					new OrderDetail(
							order.getId(),
							item.getId(),
							item.getQuantity()));
		}
		orderDetailRepository.saveAll(orderDetails);

		// セッションスコープのカート情報をクリアする
		cart.clear();
		
		
		// 画面返却用注文番号を設定する
		model.addAttribute("orderNumber", order.getId());
		
		return "ordered";
	}
}
