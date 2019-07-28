package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

public interface StringValidators {

  default ValidationResult<String> isNotNullOrEmpty(String value) {
    return isNotNullOrEmpty(value, "Value " + value + "  is null or empty");
  }

  default ValidationResult<String> isNotNullOrEmpty(String value, String invalidMessage) {
    if (value != null && !value.isEmpty()) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<String> hasLength(String value, int length) {
    return hasLength(value, length, "Value " + value + "  is smaller than " + length);
  }

  default ValidationResult<String> hasLength(String value, int length, String invalidMessage) {
    if (value.length() >= length) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<String> contains(String value, String contains) {
    return contains(value, contains, "Value " + value + " does not contain " + contains);
  }

  default ValidationResult<String> contains(String value, String contains, String invalidMessage) {
    if (value.contains(contains)) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<String> startsWith(String value, String start) {
    return startsWith(value, start, "Value " + value + " does not start with " + start);
  }

  default ValidationResult<String> startsWith(String value, String start, String invalidMessage) {
    if (value.startsWith(start)) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default ValidationResult<String> endsWith(String value, String end) {
    return startsWith(value, end, "Value " + value + " does not end with " + end);
  }

  default ValidationResult<String> endsWith(String value, String end, String invalidMessage) {
    if (value.endsWith(end)) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}
