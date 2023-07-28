package com.xyx.pms.service;

import java.util.List;

import com.xyz.pms.entity.SupplierEntity;

public interface SupplierDao {
    void addSupplier(SupplierEntity supplier);
    List<SupplierEntity> getAllSuppliers();
    public List<SupplierEntity> UpdateSupplier();
    public void updateSupplier(String supplierId, SupplierEntity updatedSupplier);
    public void sortSupplier(String sortBy);
    public void deleteSupplier(String supplierId);
    public SupplierEntity searchSupplier(String searchKey);
    public List<SupplierEntity> updateSuppliers(SupplierEntity supplierEntity);
    public void searchSupplier1(ProductServiceImpl productService);
    public SupplierEntity searchSupplier1(String searchKey);
    
}

