package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jarlath.kelly on 27/03/2016.
 */
@Service
public class DateServiceImpl implements DateService {

  public DateServiceImpl(){

  }

  protected Date convertStringToDate(String dateIn) throws ParseException, InvalidTimestampParameterException {
    if(null == dateIn){
      throw new InvalidTimestampParameterException();
    }
    SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
    return format.parse(dateIn);
  }

  public Long getSecondsOnQueue(String date) throws ParseException, InvalidTimestampParameterException {
    if(null == date){
      throw new InvalidTimestampParameterException();
    }
    Date incoming = convertStringToDate(date);
    Date current = new Date();
    return (current.getTime() - incoming.getTime()) / 1000;
  }

  public Long getSecondsOnQueueUntilSpecifiedTime(String date, String currentTime) throws ParseException, InvalidTimestampParameterException {
    if(null == date || null == currentTime){
      throw new InvalidTimestampParameterException();
    }
      Date incoming = convertStringToDate(date);
      Date current = convertStringToDate(currentTime);
      return (current.getTime() - incoming.getTime()) / 1000;

  }
}
