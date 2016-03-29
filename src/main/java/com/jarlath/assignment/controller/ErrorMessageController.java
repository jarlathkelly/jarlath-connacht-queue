package com.jarlath.assignment.controller;

import java.util.Map;

import com.jarlath.assignment.dto.ErrorPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ErrorMessageController implements ErrorController {

  private static final String PATH = "/error";
  public final static String ERROR_STRING = "There has been a problem with your request";


  @Autowired
  private ErrorAttributes errorAttributes;

  @RequestMapping(value = PATH)
  ErrorPayload error(HttpServletRequest request, HttpServletResponse response) {
    return new ErrorPayload(response.getStatus(), getErrorAttributes(request, true));
  }

  private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
    RequestAttributes requestAttributes = new ServletRequestAttributes(request);
    return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
  }

  @Override
  public String getErrorPath() {
    return PATH;
  }


}
