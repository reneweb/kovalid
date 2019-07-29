package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

public interface FloatValidators {

  default ValidationResult<Float> isEqual(Float value, Float toEqual) {
    return isEqual(value, toEqual, "Value '" + value + "' is not equal " + toEqual);
  }

  default ValidationResult<Float> isEqual(Float value, Float toEqual, String invalidMessage) {
    if (value.equals(toEqual)) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<Float> isGreaterThan(Float value, Float other) {
    return isGreaterThan(value, other, "Value '" + value + "' is not greater than " + other);
  }

  default ValidationResult<Float> isGreaterThan(Float value, Float other,
                                                 String invalidMessage) {
    if (value > other) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<Float> isSmallerThan(Float value, Float other) {
    return isSmallerThan(value, other, "Value '" + value + "' is not smaller than " + other);
  }

  default ValidationResult<Float> isSmallerThan(Float value, Float other,
                                                 String invalidMessage) {
    if (value < other) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}
