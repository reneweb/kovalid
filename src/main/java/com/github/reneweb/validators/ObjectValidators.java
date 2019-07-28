package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

public interface ObjectValidators {
  default ValidationResult<Object> isNotNull(Object value) {
    return isNotNull(value, "Value is null");
  }

  default ValidationResult<Object> isNotNull(Object value, String invalidMessage) {
    if (value != null) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<Object> isInstanceOf(Object value, Class c) {
    return isInstanceOf(value, c, "Value is not a instance of " + c);
  }

  default ValidationResult<Object> isInstanceOf(Object value, Class c, String invalidMessage) {
    if (c.isInstance(value)) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}
