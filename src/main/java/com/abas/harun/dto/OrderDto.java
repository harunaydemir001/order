package com.abas.harun.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.abas.harun.model.Order}
 */
@Value
public class OrderDto implements Serializable {
    @NotNull
    Long siparisNo;
    @NotNull
    Long malNumarasi;
    int miktar;
    double birimFiyat;
}