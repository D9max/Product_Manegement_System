package com.bitlabs.PMS.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xyx.pms.service.ProductServiceImpl;
import com.xyx.pms.service.SupplierDaoImpl;
import com.xyz.pms.entity.SupplierEntity;

public class Supplier {
    private Connection con;

	public Supplier(ProductServiceImpl productService) throws SQLException {
        DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "root");
    }
	public void addSupplier(SupplierEntity supplierEntity) {
	    String sql = "INSERT INTO Supplier (supplier_id, name, contact, address) VALUES (?, ?, ?, ?)";
	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "root");
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setString(1, supplierEntity.getSupplierId());
	        pstmt.setString(2, supplierEntity.getName());
	        pstmt.setString(3, supplierEntity.getContact());
	        pstmt.setString(4, supplierEntity.getAddress());
	        pstmt.executeUpdate();
	        System.out.println("Supplier added successfully!");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void updateSupplier(String supplierId, SupplierEntity updatedSupplier) {
	    String sql = "UPDATE Supplier SET name=?, contact=?, address=? WHERE supplier_id=?";
	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "root");
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setString(1, updatedSupplier.getName());
	        pstmt.setString(2, updatedSupplier.getContact());
	        pstmt.setString(3, updatedSupplier.getAddress());
	        pstmt.setString(4, supplierId);
	        pstmt.executeUpdate();
	        System.out.println("Supplier updated successfully!");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void sortSupplier(String sortBy) {
	    try {
	        SupplierDaoImpl supplierDao = new SupplierDaoImpl(con);
	        List<SupplierEntity> sortedSuppliers = supplierDao.sortSuppliers(sortBy);
	        for (SupplierEntity supplier : sortedSuppliers) {
	            System.out.println(supplier);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void deleteSupplier(String supplierId) {
	    try {
	        SupplierDaoImpl supplierDao = new SupplierDaoImpl(con);
	        SupplierEntity deletedSupplier = supplierDao.getSupplierById(supplierId);
	        if (deletedSupplier != null) {
	            supplierDao.deleteSupplier(supplierId);
	            System.out.println("Supplier with ID: " + supplierId + " deleted successfully!");
	        } else {
	            System.out.println("Supplier with ID: " + supplierId + " not found. Deletion failed!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


public SupplierEntity searchSupplier(String searchKey) {
    String sql = "SELECT * FROM Supplier WHERE name LIKE ? OR contact LIKE ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, "%" + searchKey + "%");
        pstmt.setString(2, "%" + searchKey + "%");
        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            String supplierId = resultSet.getString("supplier_id");
            String name = resultSet.getString("name");
            String contact = resultSet.getString("contact");
            String address = resultSet.getString("address");
            return new SupplierEntity(supplierId, name, contact, address);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; 
}
public List<SupplierEntity> updateSuppliers(SupplierEntity supplierEntity) {
    List<SupplierEntity> allSuppliers = new ArrayList<>();
    String sql = "SELECT supplier_id, name, contact, address FROM suppliers"; 
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        ResultSet resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            String supplierId = resultSet.getString("supplier_id");
            String name = resultSet.getString("name");
            String contact = resultSet.getString("contact");
            String address = resultSet.getString("address");
            allSuppliers.add(new SupplierEntity(supplierId, name, contact, address));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return allSuppliers;

}

}
