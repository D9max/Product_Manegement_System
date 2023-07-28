package com.xyz.pms.entity;

import java.sql.Date;

public class Sales {
	private String salesId;
	private Date date;
	private product product;
	private int quantity;
	private double totalRevenue;
	public Sales(String salesId2, Date date2, com.xyz.pms.entity.product product2, int quantity2,
			double totalRevenue2) {
		// TODO Auto-generated constructor stub
	}
	public void sales(String salesId,Date date,product product,int quantity,double totalRevenue ) {
		this.setSalesId(salesId);
		this.setDate(date);
		this.setProduct(product);
		this.setQuantity(quantity);
		this.setTotalRevenue(totalRevenue);
	}
	public String getSalesId() {
		return salesId;
	}
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public product getProduct() {
		return product;
	}
	public void setProduct(product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public void addSale(Sales newSale) {
		// TODO Auto-generated method stub
		
	}
	public static void updateSale(String salesId2, Sales updatedSale) {
		// TODO Auto-generated method stub
		
	}
	public Sales searchSale(String salesId2) {
		// TODO Auto-generated method stub
		return null;
	}
	public String toString() {
	    return "Sales ID: " + salesId + "\n" +
	           "Date: " + date + "\n" +
	           "Product: " + product + "\n" +
	           "Quantity: " + quantity + "\n"+
	           "Total Revenue:"+totalRevenue;
	}
}
