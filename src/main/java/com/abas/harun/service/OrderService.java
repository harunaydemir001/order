package com.abas.harun.service;

import com.abas.harun.dto.OrderDto;
import com.abas.harun.mapper.MapperGenerator;
import com.abas.harun.mapper.MapperGeneratorSingleton;
import com.abas.harun.model.Order;
import com.abas.harun.repository.OrderRepository;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> getAllOrderList() {
        List<Order> orders = orderRepository.findAll();
        return mapper.orderListToOrderDTOList(orders);
    }

    public AtomicReference<Double> getTotalPrice() {
        List<Order> orders = orderRepository.findAll();

        AtomicReference<Double> total = new AtomicReference<>(0.0);

        orders.forEach(x -> {
            double sum = x.getMiktar() * x.getBirimFiyat();
            total.updateAndGet(v -> v + sum);
        });

        return total;
    }

    public double getTotalPriceAVG() {
        List<Order> orders = orderRepository.findAll();
        int totalProductNumber = orders.stream().mapToInt(Order::getMiktar).sum();

        AtomicReference<Double> totalPrice = getTotalPrice();
        return totalPrice.get() / totalProductNumber;
    }

    public String averagePricePerProduct() {
        Map<Long, Double> averagePrices = averagePricePerProduct(orderRepository.findAll());

        StringBuilder output = new StringBuilder();

        averagePrices.forEach((malNumarasi, avgPrice) ->
                output.append("Mal Numarası: ").append(malNumarasi)
                        .append(", Ortalama Fiyat: ").append(avgPrice)
                        .append("\n")
        );

        return output.toString();
    }

    private Map<Long, Double> averagePricePerProduct(List<Order> orders) {
        Map<Long, Double> totalPriceMap = new HashMap<>();
        Map<Long, Integer> totalQuantityMap = new HashMap<>();

        for (Order order : orders) {
            totalPriceMap.merge(order.getMalNumarasi(), order.getBirimFiyat() * order.getMiktar(), Double::sum);
            totalQuantityMap.merge(order.getMalNumarasi(), order.getMiktar(), Integer::sum);
        }

        return totalPriceMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() / totalQuantityMap.get(entry.getKey())
                ));
    }

    public String countProductPerOrder() {
        Map<Long, Integer> productOrderMap = countProductPerOrder(orderRepository.findAll());

        StringBuilder output = new StringBuilder();

        productOrderMap.forEach((malNumarasi, quantity) ->
                output.append("Mal Numarası: ").append(malNumarasi)
                        .append(", Miktar: ").append(quantity)
                        .append("\n")
        );

        return output.toString();
    }

    private Map<Long, Integer> countProductPerOrder(List<Order> orders) {

        Map<Long, Integer> productOrderMap = new HashMap<>();

        for (Order order : orders) {
            productOrderMap.merge(order.getMalNumarasi(), order.getMiktar(), Integer::sum);
        }
        return productOrderMap;
    }

    public OrderDto createOrder(@Parameter(description = "Order DTO", required = true) @RequestBody OrderDto orderDto) {
        orderRepository.save(mapper.orderDTOToOrder(orderDto));
        return orderDto;
    }
}

