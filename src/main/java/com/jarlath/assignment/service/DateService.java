package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidTimestampParameterException;

import java.text.ParseException;

/**
 * The {@link DateService} class defines the Interface for the DateService
 * implementations
 *
 * @author Jarlath Kelly
 * @see DateServiceImpl
 */
public interface DateService {

  Long getSecondsOnQueue(String date) throws ParseException, InvalidTimestampParameterException;

  Long getSecondsOnQueueUntilSpecifiedTime(String date, String currentTime) throws ParseException, InvalidTimestampParameterException;
}
