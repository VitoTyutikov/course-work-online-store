package com.vitaly.onlineStore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemsPK implements Serializable {
    private Integer orderId;
    private Integer productId;
}
