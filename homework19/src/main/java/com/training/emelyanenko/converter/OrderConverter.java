package com.training.emelyanenko.converter;

import com.training.emelyanenko.domain.Order;
import com.training.emelyanenko.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends AbstractConverter<Order, OrderDto> {

    @Override
    Class<OrderDto> getDomainClass() {
        return OrderDto.class;
    }

    @Override
    Class<Order> getEntityClass() {
        return Order.class;
    }
}
