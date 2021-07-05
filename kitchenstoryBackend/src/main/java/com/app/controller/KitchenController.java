package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Item;
import com.app.service.KitchenService;

@RestController()
@CrossOrigin()
public class KitchenController{
	
	@Autowired
	private KitchenService kitchenService;

	@PostMapping("/item")
	public Item createItem(@RequestBody Item item) {
		return kitchenService.createItem(item);
	}

	@PutMapping("/item/{id}")
	public Item updateItem(@PathVariable int id, @RequestBody Item item) {
		return kitchenService.updateItem(id , item);
	}

	@GetMapping("/items")
	public List<Item> getAllItems() {
		return kitchenService.getAllItems();
	}

	@GetMapping("/items/filter")
	public List<Item> filterItem(@RequestParam String itemCatagory, @RequestParam String itemCourse, @RequestParam(required = false) String itemType) {
		if(itemType == null || itemType.equals("") )
			return kitchenService.filterItem(itemCatagory, itemCourse);
		else
			return kitchenService.filterItem(itemCatagory, itemCourse, itemType);
	}

	@DeleteMapping("/item/{id}")
	public void deleteItem(@PathVariable int id) {
		kitchenService.deleteItem(id);
	}
	
}
