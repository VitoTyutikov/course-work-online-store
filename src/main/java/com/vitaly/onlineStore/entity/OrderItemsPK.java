package com.vitaly.onlineStore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsPK implements Serializable {
    private Integer orderId;
    private Integer productId;
}
