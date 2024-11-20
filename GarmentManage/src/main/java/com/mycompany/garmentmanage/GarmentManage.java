package com.mycompany.garmentmanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Garment {
    public String id;
    public String name;
    public String description;
    public String size;
    public String color;
    public double price;
    private int stockQuantity; 

    public Garment(String id, String name, String description, String size, String color, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void adjustStock(int quantity) {
        stockQuantity += quantity;
    }

    public double getDiscountedPrice(double discountPercentage) {
        return price * (1 - discountPercentage / 100);
    }

    public String showDetails() {
        return "Garment ID: " + id + ", Name: " + name + ", Price: " + price + ", Available Stock: " + stockQuantity;
    }
}

class Fabric {
    public String id;
    public String type;
    public String color;
    public double pricePerMeter;

    public Fabric(String id, String type, String color, double pricePerMeter) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.pricePerMeter = pricePerMeter;
    }

    public double computeCost(double meters) {
        return pricePerMeter * meters;
    }
}

class Supplier {
    public String id;
    public String name;
    public String contactInfo;
    public List<Fabric> suppliedFabrics;

    public Supplier(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.suppliedFabrics = new ArrayList<>();
    }

    public void addSuppliedFabric(Fabric fabric) {
        suppliedFabrics.add(fabric);
    }

    public List<Fabric> listSuppliedFabrics() {
        return suppliedFabrics;
    }
}

class Order {
    public String orderId;
    public Date orderDate;
    public List<Garment> garments;
    private double totalAmount; 

    public Order(String orderId) {
        this.orderId = orderId;
        this.orderDate = new Date();
        this.garments = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void includeGarment(Garment garment) {
        garments.add(garment);
        totalAmount += garment.getDiscountedPrice(12); 
    }

    public double computeTotalAmount() {
        return totalAmount;
    }

    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderId + ", Date: " + orderDate);
        garments.forEach(g -> System.out.println(g.showDetails()));
        System.out.println("Total Order Amount: " + totalAmount);
    }
}

class Customer {
    public String customerId;
    public String name;
    public String email;
    public String phone;
    public List<Order> orders;

    public Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.orders = new ArrayList<>();
    }

    public void createOrder(Order order) {
        orders.add(order);
    }

    public List<Order> viewAllOrders() {
        return orders;
    }
}

public class GarmentManage {
    public static void main(String[] args) {
        Garment g1 = new Garment("G1", "Jacket", "Warm winter jacket", "XL", "Gray", 1500, 10);
        Garment g2 = new Garment("G2", "Sweater", "Cozy wool sweater", "M", "Green", 900, 20);

        Fabric f1 = new Fabric("F1", "Wool", "White", 350);
        Supplier supplier = new Supplier("S1", "Rahman Fabrics", "01712345678");
        supplier.addSuppliedFabric(f1);

        Customer customer = new Customer("C1", "Hasan", "hasan@example.com", "01698765432");

        Order order = new Order("O1");
        order.includeGarment(g1);
        order.includeGarment(g2);

        customer.createOrder(order);

        order.displayOrderDetails();
    }
}
