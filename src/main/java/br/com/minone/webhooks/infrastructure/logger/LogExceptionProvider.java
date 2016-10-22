package br.com.minone.webhooks.infrastructure.logger;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Provider utilizado para efetuar o log de exceções e enviar no body do
 * response uma mensagem genérica informando que houve um erro interno no
 * sistema.
 *
 * O Response vai com o status de INTERNAL SERVER ERROR(500).
 *
 * @author Manoel Menezes (manoel.menezes.jr@gmail.com)
 */
@Provider
public class LogExceptionProvider extends Logger implements ExceptionMapper<Exception> {

}
