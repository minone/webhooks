package br.com.minone.web;

import org.apache.logging.log4j.LogManager;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class Logger {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class.getName());

    public String getMessage() {
        return "Internal error occured. Contact the system administrator.";
    }

    public void error(Exception e) {
        logger.error("ERROR:", e);
    }

    public Response toResponse(Exception e) {

        error(e);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }

}
