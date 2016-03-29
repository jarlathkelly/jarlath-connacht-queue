package com.jarlath.assignment.controller;

import com.jarlath.assignment.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.test.SpringApplicationConfiguration;
import mockit.Mock;
import mockit.MockUp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by jarlath.kelly on 29/03/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ErrorMessageControllerTest {

  @Autowired
  private ErrorAttributes errorAttributes;

  private static final String PATH = "/error";

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void test_error_200() throws Exception {
    mockMvc.perform(get(PATH)
        .contentType(contentType))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void test_getErrorPath() throws Exception {
    ErrorMessageController errController = new ErrorMessageController();
    assertTrue(errController.getErrorPath() == PATH);
  }

  //@Test
  public void test_getErrorAttributes() throws Exception {

    new MockUp<ErrorAttributes>() {
      @Mock (invocations = 1)
      Map<String, Object> getErrorAttributes(RequestAttributes var1, boolean var2) {
        Map<String, Object> result =  new HashMap<String, Object>();
        result.put("error","");
        result.put("message","");
        result.put("timestamp","");
        result.put("trace","");
        return result;
      }
    };
    HttpServletRequest request = new MockHttpServletRequest().getMockInstance();
    ErrorMessageController errController = new ErrorMessageController();
    Map<String, Object> result = errController.getErrorAttributes(request, true);
    assertTrue(result.get("error") == "");
    assertTrue(result.get("message") == "");
    assertTrue(result.get("timestamp") == "");
    assertTrue(result.get("trace") == "");
  }


  class MockHttpSession extends MockUp<HttpSession> {
  }

  class MockHttpServletRequest extends MockUp<HttpServletRequest> {
    @Mock
    HttpSession getSession() {
      return new MockHttpSession().getMockInstance();
    }
  }

}
