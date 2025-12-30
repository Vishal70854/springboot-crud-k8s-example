package com.vishal.service;

import com.vishal.entity.Order;
import com.vishal.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    // add Order in DB
    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    // get list of all Orders
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    // get order by id
    public Order getOrderById(int id){
        Optional<Order> orderById = orderRepository.findById(id);
        if(orderById.isPresent()){
            return orderById.get();
        }else{
            throw new RuntimeException("Order not found !!!");
        }
    }
}
