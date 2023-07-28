package com.xyz.pms.entity;

public class product {
	private String sku;
	private static String name;
	private String Description;
	private String supplier;
	private int quantity;
	private static double price;
	public product(String sku,String name,String Description,String supplier,int quantity,double price ) {
		this.setSku(sku);
		this.setName(name);
		this.setDescription(Description);
		this.setSupplier(supplier);
		this.setQuantity(quantity);
		this.setPrice(price);
	}
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public static String getName() {
		return name;
	}
	public void setName(String name) {
		product.name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public static double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		product.price = price;
	}
	public String getContact() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	public String toString() {
        return "Product [sku=" + sku + ", name=" + name + ", Description=" + Description +
               ", supplier=" + supplier + ", quantity=" + quantity + ", price=" + price + "]";
    }
}
