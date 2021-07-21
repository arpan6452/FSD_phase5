package com.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Item;
import com.app.repository.ItemRepository;
import com.app.service.KitchenService;

@Service
public class KitchenServiceImpl implements KitchenService{
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item createItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item updateItem(int itemid, Item item) {
		Item updatedItem = itemRepository.findById(itemid).get();
		updatedItem.setItemName(item.getItemName());
		updatedItem.setItemCatagory(item.getItemCatagory());
		updatedItem.setItemCourse(item.getItemCourse());
		updatedItem.setItemType(item.getItemType());
		updatedItem.setPrice(item.getPrice());
		return itemRepository.save(updatedItem);
	}

	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll();		
	}

	@Override
	public List<Item> filterItem(String itemCatagory, String itemCourse, String itemType) {
		return itemRepository.findByItemCatagoryAndItemCourseAndItemType(itemCatagory, itemCourse, itemType);
	}

	@Override
	public void deleteItem(int id) {
		itemRepository.deleteById(id);
	}

	@Override
	public List<Item> filterItem(String itemCatagory, String itemCourse) {
		return itemRepository.findByItemCatagoryAndItemCourse(itemCatagory, itemCourse);
	}
	
}
