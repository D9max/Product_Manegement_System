package com.xyx.pms.service;

import java.util.List;

import com.xyz.pms.entity.SupplierEntity;
import com.xyz.pms.entity.product;
public interface ProductService<Product> {
    void addProduct(Product product);
    void updateProduct(String sku, product updatedProduct);
    product searchProduct(String searchKey);
    product searchProduct1(String searchKey);
    List<Product> sortProducts(String sortBy);
    public void sortProduct1(ProductServiceImpl productService);
    public void addSupplier(SupplierEntity supplier);
    public List<Object> getAllSuppliers();
    public List<Object> getAllProducts();
    public product getProductBySku(String sku);
	ProductService<?> getProductByName(String searchKey);
	public SupplierEntity searchSupplier1(String searchKey);
	public void searchSupplier1(ProductServiceImpl productService);
}
