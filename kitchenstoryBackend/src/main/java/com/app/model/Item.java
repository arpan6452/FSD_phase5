package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemid;
	private String itemName;
	private String itemCatagory;
	private String itemCourse;
	private String itemType;
	private double price;	
	
	/*
	 * public Item(int itemid, String itemName, String itemCatagory, String
	 * itemCourse, String itemType, double price) { this.itemid = itemid;
	 * this.itemName = itemName; this.itemCatagory = itemCatagory; this.itemCourse =
	 * itemCourse; this.itemType = itemType; this.price = price; }
	 */
	
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCatagory() {
		return itemCatagory;
	}
	public void setItemCatagory(String itemCatagory) {
		this.itemCatagory = itemCatagory;
	}
	public String getItemCourse() {
		return itemCourse;
	}
	public void setItemCourse(String itemCourse) {
		this.itemCourse = itemCourse;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Menu [itemid=" + itemid + ", itemName=" + itemName + ", itemCatagory=" + itemCatagory + ", itemCourse="
				+ itemCourse + ", itemType=" + itemType + ", price=" + price + "]";
	}
	
	
}
