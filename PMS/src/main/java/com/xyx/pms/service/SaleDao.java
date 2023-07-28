package com.xyx.pms.service;

import java.util.List;

import com.xyz.pms.entity.product;
import com.xyz.pms.entity.Sales;

public interface SaleDao {
    void addSale(Sales sale);
    void updateSale(String salesId, Sales updatedSale);
    List<Sales> getAllSales();
    product getProductBySku(String sku);
    public void deleteSale(String salesId);
	void updateSale1(String salesId, Sales updatedSale);
}
