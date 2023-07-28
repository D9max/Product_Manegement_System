package com.xyx.pms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xyz.pms.entity.SupplierEntity;

public class SupplierDaoImpl {
    private static final Connection con = null;
	private Connection connection;

    public SupplierDaoImpl(Connection connection) {
        this.setConnection(connection);
    }

   
    public void addSupplier(SupplierEntity supplier) {
        String sql = "INSERT INTO Supplier (name, contact, address) VALUES (?, ?, ?)";
        Connection con = null;
		try (@SuppressWarnings("null")
		PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getContact());
            pstmt.setString(3, supplier.getAddress());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<SupplierEntity> UpdateSupplier() {
        List<SupplierEntity> allSuppliers = new ArrayList<>();
        String sql = "UPDATE Sale SET supplier_id=?, name=?, contact=?, address=?";;
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

    public List<SupplierEntity> getAllSuppliers() {
        List<SupplierEntity> allSuppliers = new ArrayList<>();
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


	public List<SupplierEntity> sortSuppliers(String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}


	public SupplierEntity getSupplierById(String supplierId) {
		// TODO Auto-generated method stub
		return null;
	}


	public void deleteSupplier(String supplierId) {
		// TODO Auto-generated method stub
		
	}


	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
