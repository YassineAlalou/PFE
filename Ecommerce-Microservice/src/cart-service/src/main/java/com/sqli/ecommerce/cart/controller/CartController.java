package com.sqli.ecommerce.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.ecommerce.cart.dto.CartDto;
import com.sqli.ecommerce.cart.model.OrderItem;
import com.sqli.ecommerce.cart.service.CartService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/carts")
@Slf4j
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping(params = {"customer"}) @ApiOperation(value="Create new product orderItem using customer id.")
    public ResponseEntity<Void> addOrderItem(@RequestBody OrderItem orderItem, @RequestParam(name = "customer") String customerId){
        int effectNum = cartService.addOrderItem(customerId, orderItem);
        if (effectNum<=0){
            throw new RuntimeException("Failed to create orderItem.");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(params = {"customer"}) @ApiOperation(value="Get cart by customer id.")
    public CartDto getCartList(@RequestParam(name = "customer") String customerId){
        return cartService.getCart(customerId);
    }
	

    @PutMapping(value = "/products/{productId}", params = {"customer","quantity"}) @ApiOperation(value="Change quantity of a product using customer id.")
    public ResponseEntity<Void> updateCartNum(@PathVariable int productId,
                                              @RequestParam(name = "customer") String customerId,
                                              @RequestParam(name = "quantity") int quantity){
        int effectNum = cartService.updateOrderItemQuantity(customerId, productId, quantity);
        if (effectNum <=0){
            throw new RuntimeException("Failed to update products quantity.");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @DeleteMapping(value = "/products/{productId}", params = {"customer"}) @ApiOperation(value="Delete product of cart using customer id.")
    public ResponseEntity<Void> delCartProduct(@PathVariable int productId, @RequestParam(name = "customer") String customerId){
        int effectNum = cartService.delCartProduct(customerId, productId);
        if (effectNum <=0){
            throw new RuntimeException("Failed to delete product from cart.");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(params = {"customer"}) @ApiOperation(value="Delete cart using customer id.")
    public ResponseEntity<Void> delCart(@RequestParam(name = "customer") String customerId){

        int effectNum = cartService.delCart(customerId);
        if (effectNum <=0){
            throw new RuntimeException("Failed to delete cart.");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
