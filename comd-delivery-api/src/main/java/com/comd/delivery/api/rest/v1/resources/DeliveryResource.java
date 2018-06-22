package com.comd.delivery.api.rest.v1.resources;

import com.comd.delivery.api.infrastructure.test.TestDeliveryService;
import com.comd.delivery.api.services.DeliveryService;
import com.comd.delivery.lib.v1.Delivery;
import com.comd.delivery.lib.v1.response.DeliveryResponse;
import com.sap.conn.jco.JCoException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.microprofile.faulttolerance.Fallback;
import com.comd.delivery.api.util.DeliveryLogger;

@Path("/delivery")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeliveryResource {

//    @DeliveryLogger
//    @Inject
//    Logger logger;
    private static final Logger logger = Logger.getLogger(DeliveryResource.class.getName());

    @Inject
    private DeliveryService deliveryService;

    //@Fallback(fallbackMethod = "fallbackDelivery")
    @GET
    public Response delivery(@QueryParam("bldate") String blDate,
            @QueryParam("vessel") String vesselId, @QueryParam("customer") String customerId) throws JCoException, ParseException {

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateBl;
//
//        dateBl = sdf.parse(blDate);
        List<Delivery> delivery = deliveryService.deliveriesOfCustomer(blDate, customerId);

        return Response.ok().entity(delivery)
                .header("X-Total-Count", 0).build();
    }

    public Response fallbackDelivery(@QueryParam("bldate") String blDate,
            @QueryParam("vessel") String vesselId, @QueryParam("customer") String customerId) throws ParseException {

        TestDeliveryService service = new TestDeliveryService();
        return Response.ok(service.deliveriesOfCustomer(blDate, customerId))
                .header("X-Total-Count", 0).build();
    }
}
