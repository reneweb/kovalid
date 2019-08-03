package com.github.reneweb.kovalid.validators;

import com.github.reneweb.kovalid.ValidationResult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntValidatorsTest {
  @Test
  public void isEqual() {
    Integer value = 1;

    IntValidators dv = new IntValidators() {};
    ValidationResult<Integer> resultSuccess = dv.isEqual(value, 1);
    ValidationResult<Integer> resultSuccessCustomMessage = dv.isEqual(value, 1, "custom");

    ValidationResult<Integer> resultFailure = dv.isEqual(value, 2);
    ValidationResult<Integer> resultFailureCustomMessage = dv.isEqual(value, 2, "custom");

    assertThat(resultSuccess.isValid()).isTrue();
    assertThat(resultSuccess.getValue()).isEqualTo(value);
    assertThat(resultSuccessCustomMessage.isValid()).isTrue();
    assertThat(resultSuccessCustomMessage.getMessage()).isNull();
    assertThat(resultSuccessCustomMessage.getValue()).isEqualTo(value);

    assertThat(resultFailure.isValid()).isFalse();
    assertThat(resultFailure.getValue()).isEqualTo(value);
    assertThat(resultFailureCustomMessage.isValid()).isFalse();
    assertThat(resultFailureCustomMessage.getMessage()).isEqualTo("custom");
    assertThat(resultFailureCustomMessage.getValue()).isEqualTo(value);
  }

  @Test
  public void isGreaterThan() {
    Integer value = 1;

    IntValidators dv = new IntValidators() {};
    ValidationResult<Integer> resultSuccess = dv.isGreaterThan(value, 0);
    ValidationResult<Integer> resultSuccessCustomMessage = dv.isGreaterThan(value, 0, "custom");

    ValidationResult<Integer> resultFailure = dv.isGreaterThan(value, 1);
    ValidationResult<Integer> resultFailureCustomMessage = dv.isGreaterThan(value, 1, "custom");

    assertThat(resultSuccess.isValid()).isTrue();
    assertThat(resultSuccess.getValue()).isEqualTo(value);
    assertThat(resultSuccessCustomMessage.isValid()).isTrue();
    assertThat(resultSuccessCustomMessage.getMessage()).isNull();
    assertThat(resultSuccessCustomMessage.getValue()).isEqualTo(value);

    assertThat(resultFailure.isValid()).isFalse();
    assertThat(resultFailure.getValue()).isEqualTo(value);
    assertThat(resultFailureCustomMessage.isValid()).isFalse();
    assertThat(resultFailureCustomMessage.getMessage()).isEqualTo("custom");
    assertThat(resultFailureCustomMessage.getValue()).isEqualTo(value);
  }

  @Test
  public void isSmallerThan() {
    Integer value = 1;

    IntValidators dv = new IntValidators() {};
    ValidationResult<Integer> resultSuccess = dv.isSmallerThan(value, 2);
    ValidationResult<Integer> resultSuccessCustomMessage = dv.isSmallerThan(value, 2, "custom");

    ValidationResult<Integer> resultFailure = dv.isSmallerThan(value, 1);
    ValidationResult<Integer> resultFailureCustomMessage = dv.isSmallerThan(value, 1, "custom");

    assertThat(resultSuccess.isValid()).isTrue();
    assertThat(resultSuccess.getValue()).isEqualTo(value);
    assertThat(resultSuccessCustomMessage.isValid()).isTrue();
    assertThat(resultSuccessCustomMessage.getMessage()).isNull();
    assertThat(resultSuccessCustomMessage.getValue()).isEqualTo(value);

    assertThat(resultFailure.isValid()).isFalse();
    assertThat(resultFailure.getValue()).isEqualTo(value);
    assertThat(resultFailureCustomMessage.isValid()).isFalse();
    assertThat(resultFailureCustomMessage.getMessage()).isEqualTo("custom");
    assertThat(resultFailureCustomMessage.getValue()).isEqualTo(value);
  }
}
