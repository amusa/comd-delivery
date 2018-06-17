package com.comd.delivery.api.rest.v1.resources;

import com.comd.delivery.api.services.DeliveryService;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.microprofile.faulttolerance.Fallback;

@Path("/delivery")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeliveryResource {

    @Inject
    private DeliveryService deliveryService;

    @Fallback(fallbackMethod = "fallbackDelivery")
    @GET
    public Response delivery(@QueryParam("bldate") String blDate,
            @QueryParam("vessel") String vesselId, @QueryParam("customer") String customerId) throws JCoException, ParseException {

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateBl;
//
//        dateBl = sdf.parse(blDate);
        return Response.ok(deliveryService.deliveriesOfCustomer(blDate, customerId))
                .header("X-Total-Count", 0).build();
    }

    public Response fallbackDelivery(@QueryParam("bldate") String blDate,
            @QueryParam("vessel") String vesselId, @QueryParam("customer") String customerId) {
        return Response.ok()
                .header("X-Total-Count", 0).build();
    }
}
