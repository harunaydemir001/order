package com.abas.harun.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Builder(setterPrefix = "with")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Order", description = "All details about the Order Object")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private Long siparisNo;
    @NotNull
    private Long malNumarasi;
    @NotNull
    private int miktar;
    @NotNull
    private double birimFiyat;


}
