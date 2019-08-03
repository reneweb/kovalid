package com.github.reneweb.kovalid;

import java.util.Objects;

public class SubValidationResult {
  private final boolean valid;
  private final String message;

  private SubValidationResult(final boolean valid,
                              final String message) {
    this.valid = valid;
    this.message = message;
  }

  static <T> SubValidationResult from(ValidationResult<T> i) {
    return new SubValidationResult(i.isValid(), i.getMessage());
  }

  public boolean isValid() {
    return valid;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SubValidationResult that = (SubValidationResult) o;
    return valid == that.valid &&
           Objects.equals(message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(valid, message);
  }
}
