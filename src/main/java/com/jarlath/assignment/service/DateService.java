package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidTimestampParameterException;

import java.text.ParseException;

/**
 * Created by jarlath.kelly on 29/03/2016.
 */
public interface DateService {

  Long getSecondsOnQueue(String date) throws ParseException, InvalidTimestampParameterException;
  Long getSecondsOnQueueUntilSpecifiedTime(String date, String currentTime) throws ParseException, InvalidTimestampParameterException;
}
