package com.vitaly.onlineStore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class DeliveriesPK implements Serializable {
    private Integer productId;
    private LocalDate deliveryDate;
}
