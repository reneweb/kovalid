package com.github.reneweb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubValidationResultTest {

  @Test
  public void shouldCreateFromValidationResult() {
    ValidationResult<String> validationResult = ValidationResult.success("something");
    SubValidationResult subValidationResult = SubValidationResult.from(validationResult);
    assertEquals(subValidationResult.isValid(), validationResult.isValid());
    assertEquals(subValidationResult.getMessage(), validationResult.getMessage());
  }
}
