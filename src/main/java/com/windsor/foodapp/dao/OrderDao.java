package com.windsor.foodapp.dao;

import com.windsor.foodapp.enums.ORDER_STATUS_ENUM;
import com.windsor.foodapp.model.CustomerOrder;
import com.windsor.foodapp.model.OrderDetail;
import com.windsor.foodapp.model.OrderItem;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Component
public class OrderDao {

    @Resource
    JdbcTemplate jdbcTemplate;


    public int createCustomerOrder(CustomerOrder customerOrder) {
        String sql = "Insert into customer_order (user_id, user_name, total_cost, order_time, foodcourt_id, foodcourt_name, order_status, prep_time)" +
                "values (" + customerOrder.getUserId() + ",'" + customerOrder.getUserName() + "',"
                + customerOrder.getTotalCost() + ",'" + new Date() + "'," + customerOrder.getFoodCourtId() + ",'"
                + customerOrder.getFoodCourtName() + "','" + ORDER_STATUS_ENUM.ACTIVE.getValue() + "'," + customerOrder.getPrepTime() + ")";
        jdbcTemplate.update(sql);
        //get id to return
        sql = "select id from customer_order " +
                " where user_id =? order by order_time desc limit 1";
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql, customerOrder.getUserId());
        return Integer.parseInt(stringObjectMap.get("id").toString());
    }

    public void createOrderItems(List<OrderItem> orderItems) {
        String sql = "insert into ordered_items (name, order_id, restaurant_id, restaurant_name, item_cost, quantity)" +
                "values (?, ?, ?, ?, ? , ?)";


        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                OrderItem item = orderItems.get(i);
                ps.setString(1, item.getName());
                ps.setInt(2, item.getOrderId());
                ps.setInt(3, item.getRestaurantId());
                ps.setString(4, item.getRestaurantName());
                ps.setDouble(5, item.getItemCost());
                ps.setInt(6, item.getQuantity());
            }

            @Override
            public int getBatchSize() {
                return orderItems.size();
            }
        });


    }

    public List<OrderDetail> getOrdersForParameters(String email, Integer restaurantId) {
        String sql;
        List<Map<String, Object>> maps;
        if (restaurantId == null){
            sql = "select co.*,ap.email_id,it.id as order_item_id,it.name, it.order_id,it.restaurant_id,it.item_cost, it.quantity,it.restaurant_name " +
                    "from appuser ap  inner join  customer_order co on ap.id=co.user_id " +
                    "inner join ordered_items it on it.order_id = co.id where ap.email_id = ?";
            maps = jdbcTemplate.queryForList(sql, email);
        }
        else {
            sql = "select co.*,ap.email_id,it.id as order_item_id,it.name, it.order_id,it.restaurant_id,it.item_cost, it.quantity,it.restaurant_name" +
                    " from appuser ap  inner join  customer_order co on ap.id=co.user_id " +
                    "inner join ordered_items it on it.order_id = co.id where it.restaurant_id = ?";
            maps = jdbcTemplate.queryForList(sql, restaurantId);
        }
        Map<Integer, OrderDetail> mapOfIdToResult = new HashMap<>();
        for (Map<String, Object> map : maps) {
            int id = Integer.parseInt(map.get("id").toString());
            OrderItem orderItem = new OrderItem(Integer.parseInt(map.get("order_item_id").toString()),
                    map.get("name").toString(), Integer.parseInt(map.get("order_id").toString()),
                    Integer.parseInt(map.get("restaurant_id").toString()), map.get("restaurant_name").toString(),
                    Double.parseDouble(map.get("item_cost").toString()), Integer.parseInt(map.get("quantity").toString()));
            if (mapOfIdToResult.containsKey(id)) {
                OrderDetail orderDetail = mapOfIdToResult.get(id);
                List<OrderItem> orderItemList = orderDetail.getOrderItemList();
                orderItemList.add(orderItem);
                orderDetail.setOrderItemList(orderItemList);
            } else {
                //create new order detail
                CustomerOrder order = new CustomerOrder(id, Integer.parseInt(map.get("user_id").toString()), map.get("user_name").toString(),
                        Double.parseDouble(map.get("total_cost").toString()),new Date(Timestamp.valueOf(map.get("order_time").toString()).getTime()),
                        Integer.parseInt(map.get("foodcourt_id").toString()), map.get("foodcourt_name").toString(),
                        ORDER_STATUS_ENUM.getByValue(Integer.parseInt(map.get("order_status").toString())),Integer.parseInt(map.get("prep_time").toString()));
                List<OrderItem> orderItems = new ArrayList<>();
                orderItems.add(orderItem);
                OrderDetail orderDetail = new OrderDetail(order, orderItems);
                mapOfIdToResult.put(id, orderDetail);
            }
        }
        return converMapToList(mapOfIdToResult);
    }

    private List<OrderDetail> converMapToList(Map<Integer, OrderDetail> mapOfIdToResult) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(Map.Entry<Integer, OrderDetail> entry : mapOfIdToResult.entrySet()) {
            orderDetailList.add(entry.getValue());
        }
        return orderDetailList;
    }
}