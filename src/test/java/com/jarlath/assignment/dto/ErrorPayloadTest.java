package com.jarlath.assignment.dto;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by jarlath.kelly on 29/03/2016.
 * <p>
 * Unit tests for the ErrorPayload class
 */
public class ErrorPayloadTest {

  @Test
  public void test_errorPayload() throws Exception {
    Map<String, Object> details = new HashMap<String, Object>();
    details.put("error", "Payload Error");
    details.put("message", "Payload Message");
    details.put("timestamp", "16041982163015");
    details.put("trace", "Payload Trace");

    ErrorPayload payload = new ErrorPayload(1, details);
    assertTrue(payload.getStatus() == 1);
    assertTrue(payload.getError() == "Payload Error");
    assertTrue(payload.getMessage() == "Payload Message");
    assertTrue(payload.getTimeStamp() == "16041982163015");
    assertTrue(payload.getTrace() == "Payload Trace");
  }
}
