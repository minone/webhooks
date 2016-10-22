package br.com.minone.webhooks.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by felipe on 12/31/15.
 */
@Provider
public class ValidationExceptionMappter implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorMessage(getMessage(e)))
                .build();
    }

    private String getMessage(ConstraintViolationException e) {

        StringBuilder message = new StringBuilder();

        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            message.append(violation.getMessage() + "\n\r");
        }

        return message.toString();
    }
}
