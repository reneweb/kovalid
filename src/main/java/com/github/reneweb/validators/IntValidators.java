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
}
