package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import com.jarlath.assignment.util.Statics;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

/**
 * The {@link DateServiceImpl} is the concrete implementation for the DateService
 * Interface. Provides date related services for use within the Work Order application.
 *
 * @author Jarlath Kelly
 * @see DateService
 */
@Service
public class DateServiceImpl implements DateService {

  public DateServiceImpl() {

  }

  /**
   * Calculates the length of time in seconds between a supplied date String
   * and the current time. Used to calculate the number of seconds a Work
   * Order has spent on the queue leverages Joda Time.
   *
   * @param date String representation of a date
   * @return Long no of Seconds on the queue
   * @throws InvalidTimestampParameterException when supplied Timestamp has issues parsing
   */
  public Long getSecondsOnQueue(final String date) throws InvalidTimestampParameterException {
    if (null == date) {
      throw new InvalidTimestampParameterException();
    }
    Long result = 0L;
    try {
      DateTimeFormatter formatter = DateTimeFormat.forPattern(Statics.TIMESTAMP_FORMAT);
      DateTime incoming = formatter.parseDateTime(date);
      DateTime now = DateTime.now();
      Seconds seconds = Seconds.secondsBetween(incoming, now);
      result = new Long(seconds.getSeconds());
    } catch(IllegalArgumentException e){
      throw new InvalidTimestampParameterException(date);
    }
    return result;
  }

  /**
   * Calculates the length of time in seconds between 2 supplied date Strings
   * Used to calculate the number of seconds a Work Order has spent on the queue.
   * leverages Joda Time
   *
   * @param date String representation of a date
   * @return Long no of Seconds on the queue
   * @throws InvalidTimestampParameterException when supplied Timestamp has issues parsing
   */
  public Long getSecondsOnQueueUntilSpecifiedTime(final String date, final String currentTime) throws InvalidTimestampParameterException {
    if (null == date || null == currentTime) {
      throw new InvalidTimestampParameterException();
    }

    Long result = 0L;
    try {
      DateTimeFormatter formatter = DateTimeFormat.forPattern(Statics.TIMESTAMP_FORMAT);
      DateTime incoming = formatter.parseDateTime(date);
      DateTime curr = formatter.parseDateTime(currentTime);
      Seconds seconds = Seconds.secondsBetween(incoming, curr);
      result = new Long(seconds.getSeconds());
    } catch(IllegalArgumentException e){
      throw new InvalidTimestampParameterException();
    }
    return result;

  }
}
