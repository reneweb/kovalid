package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

public interface ObjectValidators {
  default ValidationResult<Object> isNotNull(Object value) {
    if (value != null) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, "Value is null");
    }
  }

  default ValidationResult<Object> isNotNull(Object value, String invalidMessage) {
    if (value != null) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}
