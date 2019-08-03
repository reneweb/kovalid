package com.github.reneweb.kovalid.validators;

import com.github.reneweb.kovalid.ValidationResult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoubleValidatorsTest {

  @Test
  public void isEqual() {
    Double value = 1.2;

    DoubleValidators dv = new DoubleValidators() {};
    ValidationResult<Double> resultSuccess = dv.isEqual(value, 1.2);
    ValidationResult<Double> resultSuccessCustomMessage = dv.isEqual(value, 1.2, "custom");

    ValidationResult<Double> resultFailure = dv.isEqual(value, 3.1);
    ValidationResult<Double> resultFailureCustomMessage = dv.isEqual(value, 3.1, "custom");

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
    Double value = 1.2;

    DoubleValidators dv = new DoubleValidators() {};
    ValidationResult<Double> resultSuccess = dv.isGreaterThan(value, 1.1);
    ValidationResult<Double> resultSuccessCustomMessage = dv.isGreaterThan(value, 1.1, "custom");

    ValidationResult<Double> resultFailure = dv.isGreaterThan(value, 1.2);
    ValidationResult<Double> resultFailureCustomMessage = dv.isGreaterThan(value, 1.2, "custom");

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
    Double value = 1.2;

    DoubleValidators dv = new DoubleValidators() {};
    ValidationResult<Double> resultSuccess = dv.isSmallerThan(value, 1.3);
    ValidationResult<Double> resultSuccessCustomMessage = dv.isSmallerThan(value, 1.3, "custom");

    ValidationResult<Double> resultFailure = dv.isSmallerThan(value, 1.2);
    ValidationResult<Double> resultFailureCustomMessage = dv.isSmallerThan(value, 1.2, "custom");

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
