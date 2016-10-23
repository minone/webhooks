package br.com.minone.webhooks.exception;

import org.apache.log4j.Logger;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    static Logger logger = Logger.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {

        logger.error(e.getMessage(), e);

        if (e instanceof ClientErrorException) {
            return ((ClientErrorException) e).getResponse();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorMessage(e.getMessage()))
                    .build();
        }
    }
}
