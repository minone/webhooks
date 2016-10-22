package br.com.minone.webhooks.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SpringDataAccessException implements ExceptionMapper<DataAccessException> {

    @Override
    public Response toResponse(DataAccessException e) {

        if (e instanceof DuplicateKeyException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessage(WebhooksConstant.DUPLICATE_KEY))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        if (e instanceof EmptyResultDataAccessException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorMessage(WebhooksConstant.RECORD_NOT_FOUND))
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorMessage(e.getMessage()))
                .build();
    }
}
