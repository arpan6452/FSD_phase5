package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
	public List<Item> findByItemCatagoryAndItemCourseAndItemType(String itemCatagory, String itemCourse, String itemType);
	public List<Item> findByItemCatagoryAndItemCourse(String itemCatagory, String itemCourse);
}
