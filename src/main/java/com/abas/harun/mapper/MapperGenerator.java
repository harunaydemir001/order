package com.abas.harun.mapper;

import com.abas.harun.dto.OrderDto;
import com.abas.harun.model.Order;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MapperGenerator {

    List<OrderDto> orderListToOrderDTOList(List<Order> orders);

    OrderDto orderToOrderDTO(Order order);

    @Mapping(target = "id", ignore = true)
    Order orderDTOToOrder(OrderDto orderDto);
}