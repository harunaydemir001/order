package com.abas.harun.configuration;

import com.abas.harun.model.Order;
import com.abas.harun.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Long firstOrder = 1000L;
    private static final Long secondOrder = 1001L;
    private static final Long thirdOrder = 1002L;


    private Order createOrder(Long siparisNo, Long malNumarasi, int miktar, double birimFiyat) {
        return Order.builder().withSiparisNo(siparisNo)
                .withMalNumarasi(malNumarasi)
                .withMiktar(miktar)
                .withBirimFiyat(birimFiyat)
                .build();
    }

    @Bean
    CommandLineRunner initDatabase(OrderRepository orderRepository) {


        return args -> {
            orderRepository.save(createOrder(firstOrder, 2000L, 12, 100.51));
            orderRepository.save(createOrder(firstOrder, 2001L, 31, 200));
            orderRepository.save(createOrder(firstOrder, 2002L, 22, 150.86));
            orderRepository.save(createOrder(firstOrder, 2003L, 41, 250));
            orderRepository.save(createOrder(firstOrder, 2004L, 55, 244));

            orderRepository.save(createOrder(secondOrder, 2001L, 88, 44.531));
            orderRepository.save(createOrder(secondOrder, 2002L, 121, 88.11));
            orderRepository.save(createOrder(secondOrder, 2004L, 74, 211));
            orderRepository.save(createOrder(secondOrder, 2002L, 14, 88.11));

            orderRepository.save(createOrder(thirdOrder, 2003L, 2, 12.1));
            orderRepository.save(createOrder(thirdOrder, 2004L, 3, 22.3));
            orderRepository.save(createOrder(thirdOrder, 2003L, 8, 12.1));
            orderRepository.save(createOrder(thirdOrder, 2002L, 16, 94));
            orderRepository.save(createOrder(thirdOrder, 2005L, 9, 44.1));
            orderRepository.save(createOrder(thirdOrder, 2006L, 19, 90));
        };
    }
}