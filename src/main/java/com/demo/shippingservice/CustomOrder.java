package com.demo.shippingservice;

public class CustomOrder {
    String customerName;
    String itemName;
    int quantity;
    String shippingProvider;

    @Override
    public String toString() {
        return "Order{" +
                ", customerName='" + customerName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", shippingProvider='" + shippingProvider + '\'' +
                '}';
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShippingProvider() {
        return shippingProvider;
    }

    public void setShippingProvider(String shippingProvider) {
        this.shippingProvider = shippingProvider;
    }
}
