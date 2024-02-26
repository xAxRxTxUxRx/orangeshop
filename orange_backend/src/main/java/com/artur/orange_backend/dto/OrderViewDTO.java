package com.artur.orange_backend.dto;

import com.artur.orange_backend.model.Product;
import com.artur.orange_backend.model.utils.order.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderViewDTO {
    private Long id;

    private LocalDateTime orderDate;

    private Float discount;

    private Boolean promoCode;

    private CustomerType customerType;

    private ObtainingMethod obtainingMethod;

    private PaymentMethod paymentMethod;

    private OrderState orderState;

    @Embedded
    private Address address;

    private List<Product> products = new ArrayList<>();
}
