package com.xyx.pms.service;
import java.util.ArrayList;
import java.util.List;


import com.xyz.pms.entity.SupplierEntity;
import com.xyz.pms.entity.product;

import java.sql.*;

public class ProductServiceImpl implements ProductService<Object> {
    Connection con=null;
	PreparedStatement pstmt;
	public ProductServiceImpl() throws SQLException{
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pms","root","root");
	}
	public void addProduct(product product) {
        String sql = "INSERT INTO Product (sku, name, description, supplier_id, quantity, price) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, product.getSku());
            pstmt.setString(2, com.xyz.pms.entity.product.getName());
            pstmt.setString(3, product.getDescription());
            pstmt.setString(4, product.getSupplier());
            pstmt.setInt(5, product.getQuantity());
            pstmt.setDouble(6, com.xyz.pms.entity.product.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
	public void updateProduct(String sku, product updatedProduct) {
	    String sql = "UPDATE Product SET name=?, description=?, supplier=?, quantity=?, price=? WHERE sku=?";
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, product.getName());
	        pstmt.setString(2, updatedProduct.getDescription());
	        pstmt.setString(3, updatedProduct.getSupplier());
	        pstmt.setInt(4, updatedProduct.getQuantity());
	        pstmt.setDouble(5, product.getPrice());
	        pstmt.setString(6, sku);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public product searchProduct(String searchKey) {
	    String sql = "SELECT * FROM Product WHERE name LIKE ?";
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, "%" + searchKey + "%");
	        ResultSet resultSet = pstmt.executeQuery();
	        if (resultSet.next()) {
	            String sku = resultSet.getString("sku");
	            String name = resultSet.getString("name");
	            String description = resultSet.getString("description");
	            String supplier = resultSet.getString("supplier_id");
	            int quantity = resultSet.getInt("quantity");
	            double price = resultSet.getDouble("price");
	            return new product(sku, name, description, supplier, quantity, price);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null; 
	}


    public List<Object> sortProducts(String sortBy) {
        String sql = "SELECT * FROM Product ORDER BY " + sortBy;
        List<Object> sortedProducts = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String sku = resultSet.getString("sku");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String supplier = resultSet.getString("supplier_id");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                sortedProducts.add(new product(sku, name, description, supplier, quantity, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortedProducts;
    }
    public void addSupplier(SupplierEntity supplier) {
        String sql = "INSERT INTO Supplier (name, contact, address) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getContact());
            pstmt.setString(3, supplier.getAddress());
            pstmt.executeUpdate();

            
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedSupplierId = generatedKeys.getInt(1);
                supplier.setSupplierId(String.valueOf(generatedSupplierId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void addProduct(Object product) {
        // TODO Auto-generated method stub
    }

    public List<Object> getAllSuppliers() {
        List<Object> allSuppliers = new ArrayList<>();
        String sql = "SELECT * FROM Supplier";
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

    public List<Object> getAllProducts() {
        List<Object> allProducts = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String sku = resultSet.getString("sku");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String supplier = resultSet.getString("supplier_id");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                allProducts.add(new product(sku, name, description, supplier, quantity, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allProducts;
    }

public product getProductBySku(String sku) {
    String sql = "SELECT * FROM Product WHERE sku = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, sku);
        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String supplier = resultSet.getString("supplier_id");
            int quantity = resultSet.getInt("quantity");
            double price = resultSet.getDouble("price");
            return new product(sku, name, description, supplier, quantity, price);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
public SupplierEntity searchSupplier(String searchKey) {
    String sql = "SELECT * FROM Supplier WHERE LOWER(name) LIKE ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, "%" + searchKey.toLowerCase() + "%");
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
    return null; // Return null if supplier not found
}

// .
public void deleteProduct(String sku) {
	// TODO Auto-generated method stub
	
}
@Override
public product searchProduct1(String searchKey) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void sortProduct1(ProductServiceImpl productService) {
	// TODO Auto-generated method stub
	
}
@Override
public ProductService<?> getProductByName(String searchKey) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public SupplierEntity searchSupplier1(String searchKey) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void searchSupplier1(ProductServiceImpl productService) {
	// TODO Auto-generated method stub
	
}
}