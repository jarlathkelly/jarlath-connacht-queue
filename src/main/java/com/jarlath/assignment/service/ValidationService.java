package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidIdParameterException;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;

/**
 * Created by jarlath.kelly on 29/03/2016.
 */
public interface ValidationService {

  boolean isIdValid(Long id) throws InvalidIdParameterException;
  boolean isCreatedTsValid(String createdTs) throws InvalidTimestampParameterException;
}
