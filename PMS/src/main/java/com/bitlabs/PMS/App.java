package com.bitlabs.PMS;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

import com.bitlabs.PMS.Supplier.Supplier;
import com.xyx.pms.service.ProductServiceImpl;
import com.xyx.pms.service.SaleDaoImpl;
import com.xyx.pms.service.SupplierDaoImpl;
import com.xyz.pms.entity.Sales;
import com.xyz.pms.entity.SupplierEntity;
import com.xyz.pms.entity.product;
public class App {
    private static Scanner sc;

	public static void main(String[] args) {
     sc=new Scanner(System.in);
        try {
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "root");

            ProductServiceImpl productService = new ProductServiceImpl();
            new SupplierDaoImpl(con);
            SaleDaoImpl saleDao = new SaleDaoImpl(con);
            Supplier supplier = new Supplier(productService);
            SaleDaoImpl sales = new SaleDaoImpl(productService, saleDao);

            boolean exit = false;

            while (!exit) {
                System.out.println("Select role:");
                System.out.println("1. Admin");
                System.out.println("2. Supplier");
                System.out.println("3. User");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {
                    case 1:
                    	adminRole(productService, supplier, sales);
                        break;

                    case 2:
                        supplierRole(productService, supplier);
                        break;

                    case 3:
                        userRole(productService);
                        break;

                    case 4:
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                System.out.println();
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void adminRole(ProductServiceImpl productService, Supplier supplier, SaleDaoImpl sales) {
			boolean exit = false;
			while (!exit) {
			    System.out.println("Admin Role:");
			    System.out.println("1. Add Supplier");
			    System.out.println("2. Update Supplier");
			    System.out.println("3. Delete Supplier");
			    System.out.println("4. Search Supplier");
			    System.out.println("5. Add Sale Info");
			    System.out.println("6. Update Sale Info");
			    System.out.println("7. Search Sale Info");
			    System.out.println("8. Delete Sale Info");
			    System.out.println("9. Search Product");
			    System.out.println("10. Sort Product");
			    System.out.println("11. Search Supplier");
			    System.out.println("12. Exit");
			    System.out.print("Enter your choice: ");
			    int adminChoice = sc.nextInt();
			    sc.nextLine();
			    switch (adminChoice) {
			        case 1:
			            addSupplier(supplier);
			            break;

			        case 2:
			            updateSupplier(supplier);
			            break;

			        case 3:
			            deleteSupplier(supplier);
			            break;

			        case 4:
			            searchSupplier(supplier);
			            break;

			        case 5:
			            addSaleInfo(sales);
			            break;

			        case 6:
			            updateSaleInfo(sales);
			            break;

			        case 7:
			            searchSaleInfo(sales);
			            break;

			        case 8:
			            deleteSaleInfo(sales);
			            break;

			        case 9:
			            searchProduct(productService);
			            break;

			        case 10:
			            sortProduct(productService);
			            break;

			        case 11:
			            searchSupplier(supplier);
			            break;

			        case 12:
			            exit = true;
			            break;

			        default:
			            System.out.println("Invalid choice. Please try again.");
			            break;
			    }
			}
		}

    public static void supplierRole(ProductServiceImpl productService, Supplier supplier) {
			boolean exit = false;
			while (!exit) {
			    System.out.println("Supplier Role:");
			    System.out.println("1. Add Product");
			    System.out.println("2. Update Product");
			    System.out.println("3. Delete Product");
			    System.out.println("4. Search Product");
			    System.out.println("5. Sort Product");
			    System.out.println("6. Search Supplier");
			    System.out.println("7. Exit");
			    System.out.print("Enter your choice: ");
			    int supplierChoice = sc.nextInt();
			    sc.nextLine(); 
			    switch (supplierChoice) {
			        case 1:
			            addProduct(productService);
			            break;

			        case 2:
			            updateProduct(productService);
			            break;

			        case 3:
			            deleteProduct(productService);
			            break;

			        case 4:
			            searchProduct1(productService);
			            break;

			        case 5:
			            sortProduct1(productService);
			            break;

			        case 6:
			            searchSupplier(supplier);
			            break;

			        case 7:
			            exit = true;
			            break;

			        default:
			            System.out.println("Invalid choice. Please try again.");
			            break;
			    }
			}
		}

    public static void userRole(ProductServiceImpl productService) {
			boolean exit = false;
			while (!exit) {
			    System.out.println("User Role:");
			    System.out.println("1. Search Product");
			    System.out.println("2. Sort Product");
			    System.out.println("3. Search Supplier");
			    System.out.println("4. Exit");
			    System.out.print("Enter your choice: ");
			    int userChoice = sc.nextInt();
			    sc.nextLine(); 
			    switch (userChoice) {
			        case 1:
			            searchProduct1(productService);
			            break;

			        case 2:
			            sortProduct1(productService);
			            break;

			        case 3:
			            searchSupplier(productService);
			            break;

			        case 4:
			            exit = true;
			            break;

			        default:
			            System.out.println("Invalid choice. Please try again.");
			            break;
			    }
			}
		}

    private static void searchSupplier(ProductServiceImpl productService) {
		// TODO Auto-generated method stub
            System.out.print("Enter search key for supplier: ");
            String searchKey = sc.nextLine();
            SupplierEntity searchedSupplier = productService.searchSupplier(searchKey);

            if (searchedSupplier != null) {
                System.out.println("Supplier found:");
                System.out.println(searchedSupplier);
            } else {
                System.out.println("Supplier not found.");
            }
        }
		
	// Admin Role Methods
    public static void addSupplier(Supplier supplier) {
        System.out.print("Enter supplier name: ");
        String name = sc.nextLine();
        System.out.print("Enter supplier contact: ");
        String contact = sc.nextLine();
        System.out.print("Enter supplier address: ");
        String address = sc.nextLine();
        SupplierEntity supplierEntity = new SupplierEntity(null, name, contact, address);

        supplier.addSupplier(supplierEntity);
    }
    public static void updateSupplier(Supplier supplier) {
			System.out.print("Enter supplier ID to update: ");
			String supplierId = sc.nextLine();
			System.out.print("Enter supplier name: ");
			String name = sc.nextLine();
			System.out.print("Enter supplier contact: ");
			String contact = sc.nextLine();
			System.out.print("Enter supplier address: ");
			String address = sc.nextLine();

			SupplierEntity supplierEntity = new SupplierEntity(supplierId, name, contact, address);
			supplier.updateSuppliers(supplierEntity);
		}

    public static void deleteSupplier(Supplier supplier) {
        System.out.print("Enter supplier ID to delete: ");
        String supplierId = sc.nextLine();
        supplier.deleteSupplier(supplierId);
    }

    public static void searchSupplier(Supplier supplier) {
        System.out.print("Enter supplier ID to search: ");
        String supplierId = sc.nextLine();
        SupplierEntity searchedSupplier = supplier.searchSupplier(supplierId);

        if (searchedSupplier != null) {
            System.out.println("Supplier found:");
            System.out.println(searchedSupplier);
        } else {
            System.out.println("Supplier not found.");
        }
    }

    public static void addSaleInfo(SaleDaoImpl sales) {
        try {
            System.out.print("Enter sales ID: ");
            String salesId = sc.nextLine();
            System.out.print("Enter sales date (yyyy-MM-dd): ");
            String dateString = sc.nextLine();
            System.out.print("Enter product SKU: ");
            String productSku = sc.nextLine();
            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();
            System.out.print("Enter total revenue: ");
            double totalRevenue = sc.nextDouble();
            sc.nextLine();

            Date date = Date.valueOf(dateString);
            product product = new product(productSku, "", "", "", 0, 0.0); 

            Sales newSale = new Sales(salesId, date, product, quantity, totalRevenue);
            sales.addSale(newSale);
            System.out.println("Sale added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to add sale. Please try again.");
        }
    }

    public static void updateSaleInfo(SaleDaoImpl sales) {
        try {
            System.out.print("Enter sales ID to update: ");
            String salesId = sc.nextLine();
            System.out.print("Enter new sales date (yyyy-MM-dd): ");
            String dateString = sc.nextLine();
            System.out.print("Enter new product SKU: ");
            String productSku = sc.nextLine();
            System.out.print("Enter new quantity: ");
            int quantity = sc.nextInt();
            System.out.print("Enter new total revenue: ");
            double totalRevenue = sc.nextDouble();
            sc.nextLine(); // Consume newline character

            Date date = Date.valueOf(dateString);
            product product = new product(productSku, "", "", "", 0, 0.0); // Product object with placeholder values

            Sales updatedSale = new Sales(salesId, date, product, quantity, totalRevenue);
            Sales.updateSale(salesId, updatedSale);
            System.out.println("Sale updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to update sale. Please try again.");
        }
    }
    public static void searchSaleInfo(SaleDaoImpl sales) {
        System.out.print("Enter sales ID to search: ");
        String salesId = sc.nextLine();
        Sales searchedSale = sales.searchSale(salesId);

        if (searchedSale != null) {
            System.out.println("Sale found:");
            System.out.println("Sales ID: " + searchedSale.getSalesId());
            System.out.println("Date: " + searchedSale.getDate());
            System.out.println("Product SKU: " + searchedSale.getProduct().getSku());
            System.out.println("Quantity: " + searchedSale.getQuantity());
            System.out.println("Total Revenue: " + searchedSale.getTotalRevenue());
        } else {
            System.out.println("Sale not found.");
        }
    }

    public static void deleteSaleInfo(SaleDaoImpl sales) {
        System.out.print("Enter sales ID to delete: ");
        String salesId = sc.nextLine();
        sales.deleteSale(salesId);
        System.out.println("Sale deleted successfully!");
    }

    public static void searchProduct(ProductServiceImpl productService) {
        System.out.print("Enter search key: ");
        String searchKey = sc.nextLine();
        product searchedProduct = productService.searchProduct(searchKey);
        if (searchedProduct != null) {
            System.out.println("Product found:");
            System.out.println(searchedProduct);
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void sortProduct(ProductServiceImpl productService) {
        System.out.println("Sort Product:");
        System.out.println("1. Sort by SKU");
        System.out.println("2. Sort by Name");
        System.out.println("3. Sort by Price");
        System.out.println("4. Sort by Quantity");
        System.out.print("Enter your choice: ");
        int sortChoice = sc.nextInt();
        sc.nextLine(); 

        List<Object> sortedProducts = null;
        switch (sortChoice) {
            case 1:
                sortedProducts = productService.sortProducts("sku");
                break;

            case 2:
                sortedProducts = productService.sortProducts("name");
                break;

            case 3:
                sortedProducts = productService.sortProducts("price");
                break;

            case 4:
                sortedProducts = productService.sortProducts("quantity");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        if (sortedProducts != null && !sortedProducts.isEmpty()) {
            System.out.println("Sorted Products:");
            for (Object obj : sortedProducts) {
                System.out.println(obj);
            }
        } else {
            System.out.println("No products found.");
        }
    }
    

    // Supplier Role Methods
    public static void addProduct(ProductServiceImpl productService) {
        System.out.print("Enter product SKU: ");
        String sku = sc.nextLine();
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter product description: ");
        String description = sc.nextLine();
        System.out.print("Enter product supplier: ");
        String supplier = sc.nextLine();
        System.out.print("Enter product quantity: ");
        int quantity = sc.nextInt();
        System.out.print("Enter product price: ");
        double price = sc.nextDouble();

        product newProduct = new product(sku, name, description, supplier, quantity, price);
        productService.addProduct(newProduct);
        
    }

    public static void updateProduct(ProductServiceImpl productService) {
        System.out.print("Enter product SKU to update: ");
        String sku = sc.nextLine();
        product searchedProduct = productService.getProductBySku(sku);

        if (searchedProduct != null) {
            System.out.print("Enter product name: ");
            String name = sc.nextLine();
            System.out.print("Enter product description: ");
            String description = sc.nextLine();
            System.out.print("Enter product supplier: ");
            String supplier = sc.nextLine();
            System.out.print("Enter product quantity: ");
            int quantity = sc.nextInt();
            System.out.print("Enter product price: ");
            double price = sc.nextDouble();

            product updatedProduct = new product(sku, name, description, supplier, quantity, price);
            productService.updateProduct(sku, updatedProduct);
        } else {
            System.out.println("Product with SKU: " + sku + " not found.");
        }
    }

    public static void deleteProduct(ProductServiceImpl productService) {
        System.out.print("Enter product SKU to delete: ");
        String sku = sc.nextLine();
        productService.deleteProduct(sku);
    }

    public static void searchProduct1(ProductServiceImpl productService) {
        System.out.print("Enter search key: ");
        String searchKey = sc.nextLine();
        product searchedProduct = productService.searchProduct(searchKey); 
        if (searchedProduct != null) {
            System.out.println("Product found:");
            System.out.println(searchedProduct);
        } else {
            System.out.println("Product not found.");
        }
    }

    @SuppressWarnings("unused")
	public static void sortProduct1(ProductServiceImpl productService) {
        System.out.println("Sort Product:");
        System.out.println("1. Sort by SKU");
        System.out.println("2. Sort by Name");
        System.out.println("3. Sort by Price");
        System.out.println("4. Sort by Quantity");
        System.out.print("Enter your choice: ");
        int sortChoice = sc.nextInt();
        sc.nextLine();

        List<Object> sortedProducts = null;
        switch (sortChoice) {
            case 1:
                sortedProducts = productService.sortProducts("sku");
                break;

            case 2:
                sortedProducts = productService.sortProducts("name");
                break;

            case 3:
                sortedProducts = productService.sortProducts("price");
                break;

            case 4:
                sortedProducts = productService.sortProducts("quantity");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        if (sortedProducts != null && !sortedProducts.isEmpty()) {
            System.out.println("Sorted Products:");
            for (Object obj : sortedProducts) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }
                System.out.println();
         
        } else {
            System.out.println("No products found.");
        }
    }
}