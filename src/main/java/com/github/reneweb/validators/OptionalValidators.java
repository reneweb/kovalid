package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

import java.util.Optional;

public interface OptionalValidators {

  default <T> ValidationResult<Optional<T>> isPresent(Optional<T> value) {
    return isPresent(value, "Value is not present");
  }

  default <T> ValidationResult<Optional<T>> isPresent(Optional<T> value, String invalidMessage) {
    if (value.isPresent()) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default <T> ValidationResult<Optional<T>> containsInstanceOf(Optional<T> value, Class c) {
    return containsInstanceOf(value, "Value is not a instance of " + c);
  }

  default <T> ValidationResult<Optional<T>> containsInstanceOf(Optional<T> value, String invalidMessage) {
    if (value.isPresent() && getClass().isInstance(value.get())) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}