package com.comd.delivery.api.rest.v1;

import com.comd.delivery.api.rest.v1.mappers.EmptyPayloadMapper;
import com.comd.delivery.api.rest.v1.mappers.GeneralMapper;
import com.comd.delivery.api.rest.v1.mappers.IdMismatchMapper;
import com.comd.delivery.api.rest.v1.mappers.JCoExceptionMapper;
import com.comd.delivery.api.rest.v1.mappers.ResourceNotFoundMapper;
import com.comd.delivery.api.rest.v1.mappers.UnauthorizedMapper;
import com.comd.delivery.api.rest.v1.resources.DeliveryResource;
import com.comd.delivery.api.rest.v1.resources.providers.JacksonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        classes.add(JacksonJsonProvider.class);

        classes.add(JacksonProvider.class);

        classes.add(DeliveryResource.class);

        classes.add(EmptyPayloadMapper.class);

        classes.add(ResourceNotFoundMapper.class);

        classes.add(JCoExceptionMapper.class);

        classes.add(IdMismatchMapper.class);

        classes.add(GeneralMapper.class);

        classes.add(UnauthorizedMapper.class);

        return classes;
    }

}
