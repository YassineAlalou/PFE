package com.sqli.checkoutservice.facades;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqli.checkoutservice.dtos.CheckoutDTO;
import com.sqli.checkoutservice.dtos.NotificationDTO;
import com.sqli.checkoutservice.dtos.ShippingDTO;
import com.sqli.checkoutservice.dtos.cart_orders.CartOrderDTO;
import com.sqli.checkoutservice.services.NotificationSenderService;
import com.sqli.checkoutservice.services.PaymentPreOrderService;
import com.sqli.checkoutservice.services.PlaceOrderService;
import com.sqli.checkoutservice.services.ShippingCreaterService;

@Component
@Transactional
public class CheckoutFacade {
    private final ShippingCreaterService shippingCreaterService;
    private final PaymentPreOrderService paymentPreOrderService;
    private final PlaceOrderService placeOrderService;
    private final NotificationSenderService notificationSenderService;

    @Inject
    public CheckoutFacade(ShippingCreaterService shippingCreaterService, PaymentPreOrderService paymentPreOrderService, PlaceOrderService placeOrderService, NotificationSenderService notificationSenderService) {
        this.shippingCreaterService = shippingCreaterService;
        this.paymentPreOrderService = paymentPreOrderService;
        this.placeOrderService = placeOrderService;
        this.notificationSenderService = notificationSenderService;
    }

    public Boolean checkout(CheckoutDTO checkoutRequest) {
        boolean success = false;
        checkoutRequest.getPayment().setAmount(checkoutRequest.getCart().CalculeTotal());
        success = paymentPreOrderService.PayOrderCart(checkoutRequest.getPayment());
        if (success) {
            CartOrderDTO cartOrderDTO = placeOrderService.PlaceOrderCart(checkoutRequest.getCart());
            ShippingDTO shippingDTO = ShippingDTO.builder().address(checkoutRequest.getAddress())
                .email(checkoutRequest.getPayment().getCustomerInfo().getEmail())
                .orderId(cartOrderDTO.getOrderId()).build();
            shippingCreaterService.createShipping(shippingDTO);
            notificationSenderService.SendNotification(new NotificationDTO(shippingDTO.getEmail(), shippingDTO.getOrderId()));
        }
            return success;
    }

}
