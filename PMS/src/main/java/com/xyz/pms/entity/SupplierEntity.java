package com.xyz.pms.entity;
public class SupplierEntity {
	private String supplierId;
	private String name;
	private String contact;
	private String address;
	public SupplierEntity(String supplierId,String name,String contact, String address) {
		this.supplierId = supplierId;
        this.name = name;
        this.contact = contact;
        this.address = address;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String toString() {
	    return "Supplier ID: " + supplierId + "\n" +
	           "Name: " + name + "\n" +
	           "Contact: " + contact + "\n" +
	           "Address: " + address;
	}
}

