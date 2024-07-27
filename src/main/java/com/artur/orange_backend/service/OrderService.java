package com.artur.orange_backend.service;

import com.artur.orange_backend.dto.OrderCreationDTO;
import com.artur.orange_backend.exception.EntityNotFoundByIdException;
import com.artur.orange_backend.model.Order;
import com.artur.orange_backend.model.Product;
import com.artur.orange_backend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private ProductService productService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        return orderOptional.orElseThrow(() -> new EntityNotFoundByIdException("Order", orderId));
    }

    public void addOrder(OrderCreationDTO orderCreationDTO) {
        Order order = new Order();

        order.setOrderDate(LocalDateTime.now());
        order.setDiscount(orderCreationDTO.getDiscount());
        order.setPromoCode(orderCreationDTO.getPromoCode());
        order.setCustomerType(orderCreationDTO.getCustomerType());
        order.setObtainingMethod(orderCreationDTO.getObtainingMethod());
        order.setPaymentMethod(orderCreationDTO.getPaymentMethod());
        order.setOrderState(orderCreationDTO.getOrderState());
        order.setAddress(orderCreationDTO.getAddress());

        for (Long productId : orderCreationDTO.getProducts()) {
            Product product = productService.getProductById(productId);
            order.addProduct(product);
        }

        orderRepository.save(order);
    }

    public void updateOrderById(Long orderId, OrderCreationDTO orderCreationDTO) {
        Order orderToUpdate = getOrderById(orderId);

        orderToUpdate.setDiscount(orderCreationDTO.getDiscount());
        orderToUpdate.setPromoCode(orderCreationDTO.getPromoCode());
        orderToUpdate.setCustomerType(orderCreationDTO.getCustomerType());
        orderToUpdate.setObtainingMethod(orderCreationDTO.getObtainingMethod());
        orderToUpdate.setPaymentMethod(orderCreationDTO.getPaymentMethod());
        orderToUpdate.setOrderState(orderCreationDTO.getOrderState());
        orderToUpdate.setAddress(orderCreationDTO.getAddress());

        for (Long productId : orderCreationDTO.getProducts()) {
            Product product = productService.getProductById(productId);
            orderToUpdate.addProduct(product);
        }

        orderRepository.save(orderToUpdate);
    }

    public void deleteOrderById(Long orderId) {
        if (!orderRepository.existsById(orderId)){
            throw new EntityNotFoundByIdException("Order", orderId);
        }

        orderRepository.deleteById(orderId);
    }
}
