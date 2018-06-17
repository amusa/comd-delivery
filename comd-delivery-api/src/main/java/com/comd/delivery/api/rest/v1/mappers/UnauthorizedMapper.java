package com.comd.delivery.api.rest.v1.mappers;



import com.comd.delivery.api.services.exceptions.UnauthorizedException;
import com.comd.delivery.lib.v1.common.ApiError;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.UUID;

public class UnauthorizedMapper implements ExceptionMapper<UnauthorizedException> {

    @Override
    public Response toResponse(UnauthorizedException exception) {

        ApiError error = new ApiError();
        error.setRef(UUID.randomUUID());
        error.setStatus(401);
        error.setCode("unauthorized");

        return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
    }
}
