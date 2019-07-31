package com.github.reneweb.covalid.validators;

import com.github.reneweb.covalid.ValidationResult;

public interface DoubleValidators {

  default ValidationResult<Double> isEqual(Double value, Double toEqual) {
    return isEqual(value, toEqual, "Value '" + value + "' is not equal " + toEqual);
  }

  default ValidationResult<Double> isEqual(Double value, Double toEqual, String invalidMessage) {
    if (value.equals(toEqual)) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<Double> isGreaterThan(Double value, Double other) {
    return isGreaterThan(value, other, "Value '" + value + "' is not greater than " + other);
  }

  default ValidationResult<Double> isGreaterThan(Double value, Double other,
                                               String invalidMessage) {
    if (value > other) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<Double> isSmallerThan(Double value, Double other) {
    return isSmallerThan(value, other, "Value '" + value + "' is not smaller than " + other);
  }

  default ValidationResult<Double> isSmallerThan(Double value, Double other,
                                               String invalidMessage) {
    if (value < other) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}
