package com.github.reneweb;

import java.util.List;
import java.util.Optional;

public class SubValidationResult {
  private final boolean valid;
  private final Optional<String> message;

  private SubValidationResult(final boolean valid,
                              final Optional<String> message) {
    this.valid = valid;
    this.message = message;
  }

  static <T> SubValidationResult from(ValidationResult<T> i) {
    return new SubValidationResult(i.isValid(), i.getMessage());
  }

  public boolean isValid() {
    return valid;
  }

  public Optional<String> getMessage() {
    return message;
  }
}
