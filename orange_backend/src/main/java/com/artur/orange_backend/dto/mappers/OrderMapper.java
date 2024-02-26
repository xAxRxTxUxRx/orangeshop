package com.artur.orange_backend.dto.mappers;

import com.artur.orange_backend.dto.OrderViewDTO;
import com.artur.orange_backend.model.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    OrderViewDTO orderToOrderViewDTO(Order order);
}
