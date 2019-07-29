package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LongValidatorsTest {
  @Test
  public void isEqual() {
    Long value = 1L;

    LongValidators dv = new LongValidators() {};
    ValidationResult<Long> resultSuccess = dv.isEqual(value, 1L);
    ValidationResult<Long> resultSuccessCustomMessage = dv.isEqual(value, 1L, "custom");

    ValidationResult<Long> resultFailure = dv.isEqual(value, 2L);
    ValidationResult<Long> resultFailureCustomMessage = dv.isEqual(value, 2L, "custom");

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
    Long value = 1L;

    LongValidators dv = new LongValidators() {};
    ValidationResult<Long> resultSuccess = dv.isGreaterThan(value, 0L);
    ValidationResult<Long> resultSuccessCustomMessage = dv.isGreaterThan(value, 0L, "custom");

    ValidationResult<Long> resultFailure = dv.isGreaterThan(value, 1L);
    ValidationResult<Long> resultFailureCustomMessage = dv.isGreaterThan(value, 1L, "custom");

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
    Long value = 1L;

    LongValidators dv = new LongValidators() {};
    ValidationResult<Long> resultSuccess = dv.isSmallerThan(value, 2L);
    ValidationResult<Long> resultSuccessCustomMessage = dv.isSmallerThan(value, 2L, "custom");

    ValidationResult<Long> resultFailure = dv.isSmallerThan(value, 1L);
    ValidationResult<Long> resultFailureCustomMessage = dv.isSmallerThan(value, 1L, "custom");

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
