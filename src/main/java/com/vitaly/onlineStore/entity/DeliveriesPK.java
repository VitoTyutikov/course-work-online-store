package com.vitaly.onlineStore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliveriesPK implements Serializable {
    private Integer productId;
    private LocalDate deliveryDate;
}
