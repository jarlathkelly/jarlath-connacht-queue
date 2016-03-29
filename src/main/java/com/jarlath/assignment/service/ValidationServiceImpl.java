package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidIdParameterException;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import com.jarlath.assignment.util.Statics;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@link ValidationServiceImpl} is the concrete implementation for the ValidationService
 * Interface. Provides Validation related services for use within the Work Order application.
 *
 * @author Jarlath Kelly
 * @see ValidationService
 */
@Service
public class ValidationServiceImpl implements ValidationService {

  public ValidationServiceImpl() {
  }

  /**
   * Performs validation on a given id. Id must be a non zero value
   * within the range 0-9223372036854775807.
   *
   * @param id unique identifier of a Work order
   * @return boolean is Id valid
   */
  public boolean isIdValid(Long id) throws InvalidIdParameterException {
    if (null == id || id == 0L) {
      throw new InvalidIdParameterException();
    }
    Pattern regexId = Pattern.compile(Statics.VALIDATE_ID);
    Matcher iDmatcher = regexId.matcher(id.toString());
    if (!iDmatcher.find()) {
      throw new InvalidIdParameterException(id.toString());
    }
    return true;
  }

  /**
   * Performs validation on a given createdTs. createdTs must be a valid Timestamp with
   * the format:ddMMyyyyHHmmss
   *
   * @param createdTs String date representation with the format ddMMyyyyHHmmss
   * @return boolean is createdTs valid
   */
  public boolean isCreatedTsValid(String createdTs) throws InvalidTimestampParameterException {
    if (null == createdTs) {
      throw new InvalidTimestampParameterException();
    }
    Pattern regexTs = Pattern.compile(Statics.VALIDATE_TS);
    Matcher tSmatcher = regexTs.matcher(createdTs);
    if (!tSmatcher.find()) {
      throw new InvalidTimestampParameterException(createdTs);
    }
    return true;
  }
}
