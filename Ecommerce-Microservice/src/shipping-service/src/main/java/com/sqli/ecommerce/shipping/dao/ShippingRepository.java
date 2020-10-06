package com.sqli.ecommerce.shipping.dao;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sqli.ecommerce.shipping.entity.Shipment;

@Repository
public interface ShippingRepository extends PagingAndSortingRepository<Shipment,Long> {

}
