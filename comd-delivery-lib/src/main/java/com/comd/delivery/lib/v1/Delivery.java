/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comd.delivery.lib.v1;

import java.util.Date;

/**
 *
 * @author maliska
 */
public class Delivery {

    private String deliveryNumber;
    private String orderReason;
    private String customer;
    private Date blDate;
    private Date dueDate;
    private String crudeName;
    private String producer;
    private String vesselName;
    private String invoiceNumber;
    private String invoiceRef;
    private String lcNum;
    private double quantity;
    private String uom;
    private double unitPrice;
    private double netValue;
    private double budgetUnitPrice;
    private double exchangeRateToNaira;
    private String remark;

    public Delivery() {
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getOrderReason() {
        return orderReason;
    }

    public void setOrderReason(String orderReason) {
        this.orderReason = orderReason;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getBlDate() {
        return blDate;
    }

    public void setBlDate(Date blDate) {
        this.blDate = blDate;
    }

    public String getCrudeName() {
        return crudeName;
    }

    public void setCrudeName(String crudeName) {
        this.crudeName = crudeName;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceRef() {
        return invoiceRef;
    }

    public void setInvoiceRef(String invoiceRef) {
        this.invoiceRef = invoiceRef;
    }
    
    
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getBudgetUnitPrice() {
        return budgetUnitPrice;
    }

    public void setBudgetUnitPrice(double budgetUnitPrice) {
        this.budgetUnitPrice = budgetUnitPrice;
    }

    public double getExchangeRateToNaira() {
        return exchangeRateToNaira;
    }

    public void setExchangeRateToNaira(double exchangeRateToNaira) {
        this.exchangeRateToNaira = exchangeRateToNaira;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getLcNum() {
        return lcNum;
    }

    public void setLcNum(String lcNum) {
        this.lcNum = lcNum;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public double getNetValue() {
        return netValue;
    }

    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }

}
