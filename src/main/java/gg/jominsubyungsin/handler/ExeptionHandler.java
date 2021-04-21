package gg.jominsubyungsin.handler;

import gg.jominsubyungsin.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ExeptionHandler {
  @ExceptionHandler(HttpServerErrorException.class)
  public ResponseEntity<Response> ServerErrorReturn (HttpServerErrorException e){
    Response data = new Response();
    data.setHttpStatus(e.getStatusCode());
    data.setMessage(e.getMessage());

    return new ResponseEntity<Response>(data,e.getStatusCode());
  }
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<Response> ClientErrorReturn (ResponseStatusException e){
    Response data = new Response();
    data.setHttpStatus(e.getStatus());
    data.setMessage(e.getMessage());

    return new ResponseEntity<Response>(data,e.getStatus());
  }
}