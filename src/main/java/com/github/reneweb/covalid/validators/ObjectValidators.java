package com.github.reneweb.covalid.validators;

import com.github.reneweb.covalid.ValidationResult;

public interface ObjectValidators {
  default <T> ValidationResult<T> isNotNull(T value) {
    return isNotNull(value, "Value is null");
  }

  default <T> ValidationResult<T> isNotNull(T value, String invalidMessage) {
    if (value != null) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default <T> ValidationResult<T> isInstanceOf(T value, Class c) {
    return isInstanceOf(value, c, "Value is not a instance of " + c);
  }

  default <T> ValidationResult<T> isInstanceOf(T value, Class c, String invalidMessage) {
    if (c.isInstance(value)) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}
