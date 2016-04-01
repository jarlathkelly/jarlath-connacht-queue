package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.IdOutOfRangeException;
import com.jarlath.assignment.exception.InvalidIdParameterException;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import com.jarlath.assignment.util.Statics;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
  public boolean isIdValid(final String id) throws InvalidIdParameterException {
    if (null == id || id.equals("") || id.equals("0")) {
      throw new InvalidIdParameterException();
    }
    Pattern regexId = Pattern.compile(Statics.VALIDATE_ID);
    Matcher iDmatcher = regexId.matcher(id);
    if (!iDmatcher.find()) {
      throw new InvalidIdParameterException(id);
    }
    return true;
  }

  /**
   * Performs validation on a given createdTs. createdTs must be a valid Timestamp with
   * the format:ddMMyyyyHHmmss. A parse into a valid Joda time object is tested
   *
   * @param createdTs String date representation with the format ddMMyyyyHHmmss
   * @return boolean is createdTs valid
   */
  public boolean isCreatedTsValid(final String createdTs) throws InvalidTimestampParameterException {
    if (null == createdTs) {
      throw new InvalidTimestampParameterException();
    }
    try {
      DateTimeFormatter dtf = DateTimeFormat.forPattern(Statics.TIMESTAMP_FORMAT);
      dtf.parseDateTime(createdTs);
    } catch (java.lang.IllegalArgumentException e) {
      throw new InvalidTimestampParameterException();
    }

    return true;
  }

  public boolean isIdWithinRange(final String id) throws IdOutOfRangeException {
    try {
      Long.parseLong(id);
    } catch (NumberFormatException nfe) {
      throw new IdOutOfRangeException(id);
    }
    return true;
  }
}
