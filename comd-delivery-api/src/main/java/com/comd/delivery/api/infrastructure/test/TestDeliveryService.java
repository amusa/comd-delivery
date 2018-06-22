/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comd.delivery.api.infrastructure.test;

import com.comd.delivery.lib.v1.Delivery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author maliska
 */
public class TestDeliveryService {

    public List<Delivery> deliveriesOfCustomer(String blDate, String customerId) throws ParseException {
        List<Delivery> deliveryList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateBl = null;
        dateBl = sdf.parse(blDate);
        Delivery delivery = new Delivery();
        delivery.setBlDate(dateBl);
        delivery.setCrudeName("Bonny Light");
        delivery.setBudgetUnitPrice(38);
        delivery.setCustomer(customerId);
        delivery.setExchangeRateToNaira(360);
        delivery.setInvoiceNumber("0100000090");
        delivery.setOrderReason("FIRS");
        delivery.setProducer("Shell");
        delivery.setQuantity(45000);
        delivery.setVesselName("YOGA");
        delivery.setRemark("loading delay due to ship arrival delay");

        deliveryList.add(delivery);
        return deliveryList;
    }

}
