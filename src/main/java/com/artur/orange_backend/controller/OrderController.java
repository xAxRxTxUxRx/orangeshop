package com.artur.orange_backend.controller;

import com.artur.orange_backend.dto.OrderCreationDTO;
import com.artur.orange_backend.dto.OrderViewDTO;
import com.artur.orange_backend.dto.mappers.OrderMapper;
import com.artur.orange_backend.model.Order;
import com.artur.orange_backend.service.OrderService;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/orders")
public class OrderController {
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderViewDTO>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        List<OrderViewDTO> orderViewDTOs = orders.stream().map(orderMapper::orderToOrderViewDTO).toList();
        return new ResponseEntity<>(orderViewDTOs, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderViewDTO> getOrderById(@PathVariable("orderId") Long orderId){
        Order order = orderService.getOrderById(orderId);
        OrderViewDTO orderViewDTO = orderMapper.orderToOrderViewDTO(order);
        return new ResponseEntity<>(orderViewDTO, HttpStatus.OK);
    }

    @PostMapping
    public void addOrder(@Valid @RequestBody OrderCreationDTO orderCreationDTO){
        orderService.addOrder(orderCreationDTO);
    }

    @PutMapping("/{orderId}")
    public void updateOrderById(@PathVariable("orderId") Long orderId,
                                @Valid @RequestBody OrderCreationDTO orderCreationDTO){
        orderService.updateOrderById(orderId, orderCreationDTO);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrderById(@PathVariable("orderId") Long orderId){
        orderService.deleteOrderById(orderId);
    }
}
