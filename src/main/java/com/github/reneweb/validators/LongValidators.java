package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

public interface LongValidators {

  default ValidationResult<Long> isEqual(Long value, Long toEqual) {
    return isEqual(value, toEqual, "Value '" + value + "' is not equal " + toEqual);
  }

  default ValidationResult<Long> isEqual(Long value, Long toEqual, String invalidMessage) {
    if (value.equals(toEqual)) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<Long> isGreaterThan(Long value, Long other) {
    return isEqual(value, other, "Value '" + value + "' is not greater than " + other);
  }

  default ValidationResult<Long> isGreaterThan(Long value, Long other,
                                                  String invalidMessage) {
    if (value > other) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<Long> isSmallerThan(Long value, Long other) {
    return isEqual(value, other, "Value '" + value + "' is not smaller than " + other);
  }

  default ValidationResult<Long> isSmallerThan(Long value, Long other,
                                                  String invalidMessage) {
    if (value < other) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}
