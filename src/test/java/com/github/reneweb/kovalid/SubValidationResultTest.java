package com.github.reneweb.kovalid;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubValidationResultTest {

  @Test
  public void shouldCreateFromValidationResult() {
    ValidationResult<String> validationResult = ValidationResult.success("something");
    SubValidationResult subValidationResult = SubValidationResult.from(validationResult);
    assertThat(subValidationResult.isValid()).isEqualTo(validationResult.isValid());
    assertThat(subValidationResult.getMessage()).isEqualTo(validationResult.getMessage());
  }
}
