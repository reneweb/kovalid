package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

public interface StringValidators {

  default ValidationResult<String> isNullOrEmpty(String value) {
    if (value != null && !value.isEmpty()) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, "Value is null or empty");
    }
  }

  default ValidationResult<String> isNullOrEmpty(String value, String invalidMessage) {
    if (value != null && !value.isEmpty()) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<String> hasLength(String value, int length) {
    if (value.length() >= length) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, "Value is smaller than " + length);
    }
  }

  default ValidationResult<String> hasLength(String value, int length, String invalidMessage) {
    if (value.length() >= length) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}
