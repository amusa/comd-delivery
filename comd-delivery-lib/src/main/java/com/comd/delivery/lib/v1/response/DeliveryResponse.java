/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comd.delivery.lib.v1.response;

import com.comd.delivery.lib.v1.Delivery;
import java.util.List;

/**
 *
 * @author maliska
 */
public class DeliveryResponse {

    private List<Delivery> deliveries;

    public DeliveryResponse() {
    }

    public DeliveryResponse(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

}
