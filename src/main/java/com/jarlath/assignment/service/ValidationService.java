package com.jarlath.assignment.service;

import com.jarlath.assignment.exception.InvalidIdParameterException;
import com.jarlath.assignment.exception.InvalidTimestampParameterException;

/**
 * The {@link ValidationService} class defines the Interface for the ValidationService
 * implementations
 *
 * @author Jarlath Kelly
 * @see ValidationServiceImpl
 */
public interface ValidationService {

  boolean isIdValid(final String id) throws InvalidIdParameterException;

  boolean isCreatedTsValid(final String createdTs) throws InvalidTimestampParameterException;
}
