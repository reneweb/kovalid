package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

public interface IntValidators {

  default ValidationResult<Integer> isEqual(Integer value, Integer toEqual) {
    return isEqual(value, toEqual, "Value '" + value + "' is not equal " + toEqual);
  }

  default ValidationResult<Integer> isEqual(Integer value, Integer toEqual, String invalidMessage) {
    if (value.equals(toEqual)) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<Integer> isGreaterThan(Integer value, Integer other) {
    return isGreaterThan(value, other, "Value '" + value + "' is not greater than " + other);
  }

  default ValidationResult<Integer> isGreaterThan(Integer value, Integer other, String invalidMessage) {
    if (value > other) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<Integer> isSmallerThan(Integer value, Integer other) {
    return isSmallerThan(value, other, "Value '" + value + "' is not smaller than " + other);
  }

  default ValidationResult<Integer> isSmallerThan(Integer value, Integer other, String invalidMessage) {
    if (value < other) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}
