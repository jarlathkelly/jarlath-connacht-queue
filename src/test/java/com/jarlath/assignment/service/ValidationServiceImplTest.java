package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.IdOutOfRangeException;
import com.jarlath.assignment.exception.InvalidIdParameterException;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by jarlath.kelly on 29/03/2016.
 * <p>
 * Unit tests for the ValidationServiceImpl class
 */
public class ValidationServiceImplTest {

  ValidationServiceImpl validationService = new ValidationServiceImpl();

  @Test
  public void test_isIdValid1() {
    assertTrue(validationService.isIdValid("10"));
  }

  @Test
  public void test_isIdValid2() {
    assertTrue(validationService.isIdValid("9223372036854775807"));
  }

  @Test(expected = InvalidIdParameterException.class)
  public void test_isIdValid_InvalidIdParameterException1() {
    validationService.isIdValid(null);
  }

  @Test(expected = InvalidIdParameterException.class)
  public void test_isIdValid_InvalidIdParameterException2() {
    validationService.isIdValid("0");
  }

  @Test(expected = InvalidIdParameterException.class)
  public void test_isIdValid_InvalidIdParameterException3() {
    validationService.isIdValid("");
  }

  @Test(expected = InvalidIdParameterException.class)
  public void test_isIdValid_InvalidIdParameterException4() {
    validationService.isIdValid("9223372cccc036854775807");
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


  @Test
  public void test_isIdWithinRange() {
    assertTrue(validationService.isIdWithinRange("1"));
  }

  @Test
  public void test_isIdWithinRange2() {
    assertTrue(validationService.isIdWithinRange("9223372036854775807"));
  }

  @Test(expected = IdOutOfRangeException.class)
  public void test_isIdWithinRange2_IdOutOfRangeException() {
    validationService.isIdWithinRange("9223372036854775808");
  }

}
