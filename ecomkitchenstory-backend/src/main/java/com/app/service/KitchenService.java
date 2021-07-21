package com.app.service;

import java.util.List;

import com.app.entity.Item;

public interface KitchenService {
	public Item createItem(Item item);
	public Item updateItem(int itemid, Item item);	
	public List<Item> getAllItems();
	public List<Item> filterItem(String itemCatagory, String itemCourse, String itemType);	
	public List<Item> filterItem(String itemCatagory, String itemCourse);	
	public void deleteItem(int id);
}
