package com.artur.orange_backend.model.utils.product;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ProductPrice {
    @Positive(message = "Fixed price must be a positive number")
    private Float fixedPrice;

    @PositiveOrZero(message = "Discount must be a positive number (or = 0)")
    private Float discount;

    @Positive(message = "Delivery price must be a positive number")
    private Float deliveryPrice;

    @Positive(message = "NDS must be a positive number")
    private Float nds;
}
