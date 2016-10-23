package br.com.minone.webhooks.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException be) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(be.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }
}
