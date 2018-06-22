/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comd.delivery.api.infrastructure.jco;

import com.comd.delivery.api.services.DeliveryService;
import com.comd.delivery.lib.v1.Delivery;
import com.comd.delivery.api.services.exceptions.EmptyPayloadException;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.comd.delivery.api.util.DeliveryLogger;

/**
 *
 * @author maliska
 */
@ApplicationScoped
public class JcoDeliveryService implements DeliveryService {

//    @DeliveryLogger
//    @Inject
//    private Logger logger;
    
    private static final Logger logger = Logger.getLogger(JcoDeliveryService.class.getName());

    @Inject
    @ConfigProperty(name = "SAP_RFC_DESTINATION")
    private String sapRfcDestination;

    @Inject
    @ConfigProperty(name = "JCO_ASHOST")
    private String jcoHost;

    @Inject
    @ConfigProperty(name = "JCO_SYSNR")
    private String jcoSysNr;

    @Inject
    @ConfigProperty(name = "JCO_CLIENT")
    private String jcoClient;

    @Inject
    @ConfigProperty(name = "JCO_USER")
    private String jcoUser;

    @Inject
    @ConfigProperty(name = "JCO_PASSWD")
    private String jcoPassword;

    @Inject
    @ConfigProperty(name = "JCO_LANG")
    private String jcoLang;

    @Inject
    @ConfigProperty(name = "JCO_POOL_CAPACITY")
    private String jcoPoolCapacity;

    @Inject
    @ConfigProperty(name = "JCO_PEAK_LIMIT")
    private String jcoPeakLimit;

    @PostConstruct
    public void init() {
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, jcoHost);
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, jcoSysNr);
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, jcoClient);
        connectProperties.setProperty(DestinationDataProvider.JCO_USER, jcoUser);
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, jcoPassword);
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, jcoLang);
        connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, jcoPoolCapacity);
        connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, jcoPeakLimit);
        createDestinationDataFile(sapRfcDestination, connectProperties);
        logger.info("Jco service initialized...");
    }

    private void createDestinationDataFile(String destinationName, Properties connectProperties) {
        File destCfg = new File(destinationName + ".jcoDestination");

        try {
            FileOutputStream fos = new FileOutputStream(destCfg, false);
            connectProperties.store(fos, "SAP jco destination config");
            fos.close();
        } catch (IOException e) {
            logger.warning("Unable to create the destination files");
            throw new RuntimeException("Unable to create the destination files", e);
        }
    }

    @Override
    public List<Delivery> deliveriesOfCustomer(String blDate, String customerId) throws JCoException {
        List<Delivery> deliveries = new ArrayList<>();

        JCoDestination destination = JCoDestinationManager.getDestination(sapRfcDestination);
        JCoFunction function = destination.getRepository().getFunction("ZDELIVERY_GETLIST");
        if (function == null) {
            logger.log(Level.SEVERE, "ZDELIVERY_GETLIST not found in SAP.");
            throw new RuntimeException("ZDELIVERY_GETLIST not found in SAP.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        function.getImportParameterList().setValue("KUNAG", customerId);
        try {
            function.getImportParameterList().setValue("FBUDA", sdf.parse(blDate));
        } catch (ParseException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

//        if (customerId != null) {
//            new JCoTableSelectOption(function, "IT_KUNNR")
//                    .withField("CUSTOMER_VENDOR_LOW")
//                    .withValue(customerId)
//                    .withSign("I")
//                    .withOption("EQ")
//                    .build();
//        }
//
//        if (blDate != null) {
//            new JCoTableSelectOption(function, "IT_WADAT")
//                    .withField("CGI_DATE_LOW")
//                    .withValue(blDate)
//                    .withSign("I")
//                    .withOption("EQ")
//                    .build();
//        }
        try {
            function.execute(destination);
        } catch (AbapException e) {
            logger.log(Level.SEVERE, null, e);
            return null; //TODO:fix
        }

//        JCoTable returnTable = function.getTableParameterList().getTable("RETURN");
//
//        for (int i = 0; i < returnTable.getNumRows(); i++) {
//            if (!(returnTable.getString("TYPE").equals("") || returnTable.getString("TYPE").equals("S"))) {
//                throw new RuntimeException(returnTable.getString("MESSAGE"));
//            }
//        }
        JCoTable deliveryList = function.getChangingParameterList().getTable("IT_DELIVERY");

        if (deliveryList.isEmpty()) {
            logger.log(Level.WARNING, "No data returned from server");
            throw new EmptyPayloadException("No data returned from server");
        }

        for (int i = 0; i < deliveryList.getNumRows(); i++, deliveryList.nextRow()) {
            logger.log(Level.INFO, deliveryList.getString("VBELN") + '\t'
                    + deliveryList.getString("KUNAG") + '\t'
                    + deliveryList.getString("ZZVESSEL") + '\t'
                    + deliveryList.getString("FKIMG") + '\t'
                    + deliveryList.getString("NETWR") + '\t'
                    + deliveryList.getString("FBUDA") + '\t'
                    + deliveryList.getString("ARKTX"));

            Delivery delivery = new Delivery();
            delivery.setInvoiceNumber(deliveryList.getString("VBELN"));
            delivery.setCustomer(deliveryList.getString("KUNAG"));
            delivery.setVesselName(deliveryList.getString("ZZVESSEL"));
            delivery.setLcNum(deliveryList.getString("LCNUM"));
            delivery.setQuantity(Double.parseDouble(deliveryList.getString("FKIMG")));
            delivery.setNetValue(Double.parseDouble(deliveryList.getString("NETWR")));
            delivery.setUom(deliveryList.getString("VRKME"));
            delivery.setCrudeName(deliveryList.getString("ARKTX"));
            try {
                delivery.setBlDate(sdf.parse(deliveryList.getString("FBUDA")));
            } catch (ParseException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
            delivery.setOrderReason(deliveryList.getString("AUGRU_AUFT"));

            deliveries.add(delivery);
        }//for

        return deliveries;
    }

    @Override
    public List<Delivery> deliveriesOfVessel(String blDate, String vesselId) throws JCoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
