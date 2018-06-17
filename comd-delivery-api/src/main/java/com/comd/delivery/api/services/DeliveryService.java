/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comd.delivery.api.services;

import com.comd.delivery.lib.v1.Delivery;
import com.sap.conn.jco.JCoException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author maliska
 */
public interface DeliveryService {

    List<Delivery> deliveriesOfCustomer(String blDate, String customerId)throws JCoException;

    List<Delivery> deliveriesOfVessel(String blDate, String vesselId)throws JCoException;

}
