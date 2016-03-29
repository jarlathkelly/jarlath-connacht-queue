package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidIdParameterException;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import com.jarlath.assignment.util.Statics;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 27/03/2016.
 */
public class ValidationServiceTest {

  ValidationService validationService = new ValidationService();

  @Test
  public void test_isIdValid1() {
    Long id = 0L;
    assertTrue(validationService.isIdValid(id));
  }

  @Test
  public void test_isIdValid2() {
    Long id = 9223372036854775807L;
    assertTrue(validationService.isIdValid(id));
  }

  @Test(expected = InvalidIdParameterException.class)
  public void test_isIdValid_InvalidIdParameterException() {
    validationService.isIdValid(null);
  }

  @Test
  public void test_isCreatedTsValid() {
    String createdTs = "16032016093023";
    assertTrue(validationService.isCreatedTsValid(createdTs));
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_isCreatedTsValid_InvalidTimestampParameterException1() {
    String createdTs = "1603201609302311111";
    validationService.isCreatedTsValid(createdTs);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_isCreatedTsValid_InvalidTimestampParameterException2() {
    String createdTs = "1603xxxx9302311111";
    validationService.isCreatedTsValid(createdTs);
  }

  @Test(expected = InvalidTimestampParameterException.class)
  public void test_isCreatedTsValid_InvalidTimestampParameterException3() {
    validationService.isCreatedTsValid(null);
  }

}
