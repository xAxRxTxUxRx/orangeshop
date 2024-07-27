package com.artur.orange_backend.model;

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
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_id",
            sequenceName = "order_id",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "order_id",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    private LocalDateTime orderDate;

    private Float discount;

    private Boolean promoCode;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @Enumerated(EnumType.STRING)
    private ObtainingMethod obtainingMethod;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Embedded
    private Address address;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
    }
}
