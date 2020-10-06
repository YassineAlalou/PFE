package com.sqli.ecommerce.shipping.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="shipment")
@AllArgsConstructor
@NoArgsConstructor
public class Shipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private  String email;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

}
