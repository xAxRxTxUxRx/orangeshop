package com.artur.orange_backend.dto;

import com.artur.orange_backend.model.utils.order.*;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderCreationDTO {
    @PositiveOrZero(message = "Discount must be a positive number (or = 0)")
    private Float discount;

    @NotNull(message = "Specify promo code status")
    private Boolean promoCode;

    @NotNull(message = "Customer type is mandatory")
    private CustomerType customerType;

    @NotNull(message = "Obtaining method is mandatory")
    private ObtainingMethod obtainingMethod;

    @NotNull(message = "Payment method is mandatory")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Order state is mandatory")
    private OrderState orderState;

    @Embedded
    private Address address;

    private List<Long> products;
}
