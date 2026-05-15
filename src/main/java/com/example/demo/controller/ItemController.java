package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Genre;
import com.example.demo.entity.Item;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	private final GenreRepository genreRepository;
	private final ItemRepository itemRepository;
	
	public ItemController(
	        GenreRepository genreRepository,
	        ItemRepository itemRepository) {
	    this.genreRepository = genreRepository;
	    this.itemRepository = itemRepository;
	}

	// 商品一覧表示
	@GetMapping("/items")
	public String index(
			@RequestParam(defaultValue = "") Integer maxPrice,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "") Integer genreId,
			Model model) {
		//全ジャンル一覧を取得
		List<Genre> genreList = genreRepository.findAll();
		model.addAttribute("genres",genreList);
		// 商品一覧情報の取得
		List<Item> itemList = null;
		if(maxPrice != null && !(keyword.equals(""))){
			
			itemList = itemRepository.findByNameLikeAndPriceLessThanEqual(("%"+keyword+"%"),maxPrice);
			model.addAttribute("maxPrice", maxPrice);
			model.addAttribute("keyword", keyword);
		
		} else if (!(keyword.equals(""))) {

			itemList = itemRepository.findByNameLike(("%" + keyword + "%"));
			model.addAttribute("keyword", keyword);
		
		} else if (maxPrice!=null) {

			itemList = itemRepository.findByPriceLessThanEqual(maxPrice);
			model.addAttribute("maxPrice", maxPrice);
;
		} else if (genreId != null) {
			itemList = itemRepository.findByGenreId(genreId);
			
		}else {
			itemList = itemRepository.findAll();
		}
		for (int i = 0; i < itemList.size(); i++) {
			itemList.get(i).setImage("../image/" + itemList.get(i).getImage());
			
		}
		model.addAttribute("items", itemList);

		return "items";
	}
	@GetMapping("/items/{id}")
	public String item(
			@PathVariable Integer id,
			Model model) {
		
		Item item = itemRepository.findById(id).get();
		item.setImage("../image/" + item.getImage());
		model.addAttribute("item", item);
		
		return "item";
	}
}
