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

/**
 * The {@link ErrorMessageController} handles basic error rendering of {@link ErrorAttributes}
 * as defined by {@link ErrorPayload}.
 *
 * @author  Jarlath Kelly
 * @see ErrorAttributes
 * @see ErrorPayload
 *
 */
@RestController
public class ErrorMessageController implements ErrorController {

  private static final String PATH = "/error";

  @Autowired
  private ErrorAttributes errorAttributes;

  /**
   * Provide access to the error attributes.
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ErrorPayload
   */
  @RequestMapping(value = PATH)
  ErrorPayload error(HttpServletRequest request, HttpServletResponse response) {
    return new ErrorPayload(response.getStatus(), getErrorAttributes(request, true));
  }

  /**
   * Provide access to the error attributes.
   *
   * @param request HttpServletRequest
   * @param includeStackTrace flag to indicate inclusion of stack trace
   * @return the error attributes
   */
  protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
    RequestAttributes requestAttributes = new ServletRequestAttributes(request);
    return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
  }

  /**
   * Get the path of the error resource.
   *
   * @return String
   */
  @Override
  public String getErrorPath() {
    return PATH;
  }


}
