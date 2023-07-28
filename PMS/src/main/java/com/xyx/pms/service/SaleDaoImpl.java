package com.xyx.pms.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xyz.pms.entity.product;
import com.xyz.pms.entity.Sales;

public class SaleDaoImpl implements SaleDao {
    private Connection con;

    public SaleDaoImpl(Connection con) {
        this.con = con;
    }

    public SaleDaoImpl(ProductServiceImpl productService, SaleDaoImpl saleDao) {
		// TODO Auto-generated constructor stub
	}

	public void addSale(Sales sale) {
        String sql = "INSERT INTO Sale (sales_id, date, product_sku, quantity, total_revenue) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, sale.getSalesId());
            pstmt.setDate(2, sale.getDate());
            pstmt.setString(3, sale.getProduct().getSku());
            pstmt.setInt(4, sale.getQuantity());
            pstmt.setDouble(5, sale.getTotalRevenue());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSale(String salesId, Sales updatedSale) {
        String sql = "UPDATE Sale SET date=?, product_sku=?, quantity=?, total_revenue=? WHERE sales_id=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDate(1, updatedSale.getDate());
            pstmt.setString(2, updatedSale.getProduct().getSku());
            pstmt.setInt(3, updatedSale.getQuantity());
            pstmt.setDouble(4, updatedSale.getTotalRevenue());
            pstmt.setString(5, salesId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Sales> getAllSales() {
        List<Sales> allSales = new ArrayList<>();
        String sql = "SELECT * FROM Sale";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String salesId = resultSet.getString("sales_id");
                Date date = resultSet.getDate("date");
                String productSku = resultSet.getString("product_sku");
                int quantity = resultSet.getInt("quantity");
                double totalRevenue = resultSet.getDouble("total_revenue");
               product product = getProductBySku(productSku);

                allSales.add(new Sales(salesId, date, product, quantity, totalRevenue));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSales;
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
	@Override
	public void deleteSale(String salesId) {
		// TODO Auto-generated method stub
		
	}

	public Sales searchSale(String salesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSale1(String salesId, Sales updatedSale) {
		// TODO Auto-generated method stub
		
	}

}