package com.windsor.foodapp.model;

import java.util.List;

public class OrderDetail {

    private CustomerOrder customerOrder;
    private List<OrderItem> orderItemList;

    public OrderDetail(CustomerOrder customerOrder, List<OrderItem> orderItemList) {
        this.customerOrder = customerOrder;
        this.orderItemList = orderItemList;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
