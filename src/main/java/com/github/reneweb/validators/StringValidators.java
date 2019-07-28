package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

public interface StringValidators {

  default ValidationResult<String> isNotNullOrEmpty(String value) {
    return isNotNullOrEmpty(value, "Value is null or empty");
  }

  default ValidationResult<String> isNotNullOrEmpty(String value, String invalidMessage) {
    if (value != null && !value.isEmpty()) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<String> hasLength(String value, int length) {
    return hasLength(value, length, "Value is smaller than " + length);
  }

  default ValidationResult<String> hasLength(String value, int length, String invalidMessage) {
    if (value.length() >= length) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}