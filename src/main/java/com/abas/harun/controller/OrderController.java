package com.abas.harun.controller;

import com.abas.harun.dto.OrderDto;
import com.abas.harun.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/v1/abas")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    @Operation(summary = "Get All Orders")
    public List<OrderDto> getAllOrderList() {
        return orderService.getAllOrderList();
    }

    @PostMapping()
    @Operation(summary = "Create Order")
    public OrderDto createOrder(@Parameter(description = "Order DTO", required = true) @RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/total-price")
    @Operation(summary = "Üç siparişteki malların toplam tutarının çıktısını veren java kodu")
    public AtomicReference<Double> getTotalPrice() {
        return orderService.getTotalPrice();
    }

    @GetMapping("/total-price-avg")
    @Operation(summary = "Üç siparişteki bütün malların ortalama fiyatını bulan java kodu")
    public double getTotalPriceAVG() {
        return orderService.getTotalPriceAVG();
    }

    @GetMapping("/average-price-per-product")
    @Operation(summary = "Üç siparişteki bütün malların tek tek mal bazlı ortalama fiyatını bulan java kodu")
    public String averagePricePerProduct() {
        return orderService.averagePricePerProduct();
    }

    @GetMapping("/count-product-per-order")
    @Operation(summary = "Tek tek mal bazlı, malların hangi siparişlerde kaç adet olduğunun çıktısını veren java kodu.")
    public String countProductPerOrder() {
        return orderService.countProductPerOrder();
    }
}
