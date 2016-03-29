package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidIdParameterException;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;
import com.jarlath.assignment.util.Statics;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jarlath.kelly on 27/03/2016.
 */
@Service
public class ValidationService {

  public ValidationService() {
  }

  public boolean isIdValid(Long id) throws InvalidIdParameterException {
    if(null == id){
      throw new InvalidIdParameterException();
    }
    Pattern regexId = Pattern.compile(Statics.VALIDATE_ID);
    Matcher iDmatcher = regexId.matcher(id.toString());
    if (!iDmatcher.find()) {
      throw new InvalidIdParameterException(id.toString());
    }
    return true;
  }

  public boolean isCreatedTsValid(String createdTs) throws InvalidTimestampParameterException {
    if(null == createdTs){
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
