package com.artur.orange_backend.model.utils.order;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Address {
    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Post index is mandatory")
    private String postIndex;

    @NotBlank(message = "Street is mandatory")
    private String street;

    @NotBlank(message = "House is mandatory")
    private String house;

    @NotBlank(message = "Apartment is mandatory")
    private String apartment;
}
