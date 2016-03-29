package com.jarlath.assignment.controller;

/**
 * Created by jarlath.kelly on 28/03/2016.
 */

import com.jarlath.assignment.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class WorkOrderControllerTest {

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
  public void enqueueWorkOrder_200() throws Exception {
    mockMvc.perform(post("/workorders?id=1&createdTs=20032016234509")
        .contentType(contentType))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void enqueueWorkOrder_400_noID() throws Exception {
    mockMvc.perform(post("/workorders?createdTs=20032016234509")
        .contentType(contentType))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void enqueueWorkOrder_400_NoCreatedTs() throws Exception {
    mockMvc.perform(post("/workorders?id=1")
        .contentType(contentType))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void enqueueWorkOrder_405_Put() throws Exception {
    mockMvc.perform(put("/workorders?id=1&createdTs=20032016234509")
        .contentType(contentType))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void enqueueWorkOrder_405_Get() throws Exception {
    mockMvc.perform(get("/workorders?id=1&createdTs=20032016234509")
        .contentType(contentType))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void dequeueWorkOrder_200() throws Exception {
    mockMvc.perform(delete("/workorders")
        .contentType(contentType))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void retrieveAllWorkOrderIds_200() throws Exception {
    mockMvc.perform(get("/workorders/ids")
        .contentType(contentType))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void dequeueWorkOrderId_200() throws Exception {
    mockMvc.perform(delete("/workorders/ids?id=1")
        .contentType(contentType))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void dequeueWorkOrderId_400_NoId() throws Exception {
    mockMvc.perform(delete("/workorders/ids")
        .contentType(contentType))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void dequeueWorkOrderId_405_Get() throws Exception {
    mockMvc.perform(put("/workorders/ids")
        .contentType(contentType))
        .andExpect(status().is4xxClientError()
        );
  }

  @Test
  public void dequeueWorkOrderId_405_Post() throws Exception {
    mockMvc.perform(post("/workorders/ids")
        .contentType(contentType))
        .andExpect(status().is4xxClientError()
        );
  }

  @Test
  public void getWorkOrderQueuePosition_405() throws Exception {
    mockMvc.perform(delete("/workorders/ids/positions?id=1")
        .contentType(contentType))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void getWorkOrderQueuePosition_400_NoId() throws Exception {
    mockMvc.perform(get("/workorders/ids/positions")
        .contentType(contentType))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getWorkOrderQueuePosition_405_Post() throws Exception {
    mockMvc.perform(post("/workorders/ids/positions?id=1")
        .contentType(contentType))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void getWorkOrderQueuePosition_405_Put() throws Exception {
    mockMvc.perform(put("/workorders/ids/positions?id=1")
        .contentType(contentType))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void retrieveMeanWaitTime_200() throws Exception {
    mockMvc.perform(get("/workorders/waittimes?createdTs=20032016234509")
        .contentType(contentType))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void retrieveMeanWaitTime_400_NoCreatedTs() throws Exception {
    mockMvc.perform(get("/workorders/waittimes")
        .contentType(contentType))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void retrieveMeanWaitTime_405_put() throws Exception {
    mockMvc.perform(put("/workorders/waittimes")
        .contentType(contentType))
        .andExpect(status().is4xxClientError());
    
  }

  @Test
  public void retrieveMeanWaitTime_405_post() throws Exception {
    mockMvc.perform(post("/workorders/waittimes")
        .contentType(contentType))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void retrieveMeanWaitTime_405_delete() throws Exception {
    mockMvc.perform(delete("/workorders/waittimes")
        .contentType(contentType))
        .andExpect(status().is4xxClientError());
  }


}
