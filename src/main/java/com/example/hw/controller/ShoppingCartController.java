package com.example.hw.controller;

import com.example.hw.entity.ProductsEntity;
import com.example.hw.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private List<CartItem> items = new ArrayList<>();
    private String stateCode;

    @Autowired
    private TaxService taxService;

    @PostMapping("/receipt")
    public String generateReceipt(@RequestBody ShoppingCartRequest request) {
        String stateCode = request.getStateCode();
        List<ProductRequest> products = request.getProducts();

        items.clear();

        String receipt = printReceipt(stateCode, products);
        return receipt;
    }

    private String printReceipt(String stateCode, List<ProductRequest> products) {
        double subtotal = 0;
        double totalTax = 0;

        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("%-20s %10s %5s\n", "item", "price", "qty"));

        for (ProductRequest product : products) {
            ProductsEntity productEntity = taxService.getProductByName(product.getProductName());
            if (productEntity != null) {
                double tax = taxService.calculateTax(stateCode, productEntity.getCategory(), productEntity.getName(), product.getQuantity());
                items.add(new CartItem(productEntity.getName(), productEntity.getPrice(), product.getQuantity(), tax));
            }
        }

        for (CartItem item : items) {
            receipt.append(String.format("%-20s %10.2f %5d\n", item.name, item.price, item.quantity));
            subtotal += item.price * item.quantity;
            totalTax += item.tax;
        }

        double total = subtotal + totalTax;

        receipt.append(String.format("%-20s %10s %5s\n", "", "", ""));
        receipt.append(String.format("%-20s %20s\n", "subtotal:", String.format("$%.2f", subtotal)));
        receipt.append(String.format("%-20s %20s\n", "tax:", String.format("$%.2f", totalTax)));
        receipt.append(String.format("%-20s %20s\n", "total:", String.format("$%.2f", total)));

        return receipt.toString();
    }

    public static class ShoppingCartRequest {
        private String stateCode;
        private List<ProductRequest> products;

        public String getStateCode() {
            return stateCode;
        }

        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }

        public List<ProductRequest> getProducts() {
            return products;
        }

        public void setProducts(List<ProductRequest> products) {
            this.products = products;
        }
    }

    public static class ProductRequest {
        private String productName;
        private int quantity;

        public ProductRequest(String productName, int quantity) {
            this.productName = productName;
            this.quantity = quantity;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    private static class CartItem {
        String name;
        double price;
        int quantity;
        double tax;

        CartItem(String name, double price, int quantity, double tax) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.tax = tax;
        }
    }
}
