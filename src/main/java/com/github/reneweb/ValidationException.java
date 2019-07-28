package com.github.reneweb;

import java.util.List;

public class ValidationException extends RuntimeException {
  private final List<SubValidationResult> subValidationResults;

  public ValidationException(List<SubValidationResult> subValidationResults) {
    super();
    this.subValidationResults = subValidationResults;
  }

  public ValidationException(final String message, List<SubValidationResult> subValidationResults) {
    super(message);
    this.subValidationResults = subValidationResults;
  }

  private List<SubValidationResult> getSubValidationResults() {
    return subValidationResults;
  }
}
