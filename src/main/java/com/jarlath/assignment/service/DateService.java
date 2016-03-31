package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidTimestampParameterException;

/**
 * The {@link DateService} class defines the Interface for the DateService
 * implementations
 *
 * @author Jarlath Kelly
 * @see DateServiceImpl
 */
public interface DateService {

  Long getSecondsOnQueue(final String date) throws InvalidTimestampParameterException;

  Long getSecondsOnQueueUntilSpecifiedTime(final String date, final String currentTime) throws InvalidTimestampParameterException;
}
