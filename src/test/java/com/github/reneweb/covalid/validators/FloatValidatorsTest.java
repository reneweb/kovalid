package com.github.reneweb.covalid.validators;

import com.github.reneweb.covalid.ValidationResult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FloatValidatorsTest {
  @Test
  public void isEqual() {
    Float value = 1.2F;

    FloatValidators dv = new FloatValidators() {};
    ValidationResult<Float> resultSuccess = dv.isEqual(value, 1.2F);
    ValidationResult<Float> resultSuccessCustomMessage = dv.isEqual(value, 1.2F, "custom");

    ValidationResult<Float> resultFailure = dv.isEqual(value, 3.1F);
    ValidationResult<Float> resultFailureCustomMessage = dv.isEqual(value, 3.1F, "custom");

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
    Float value = 1.2F;

    FloatValidators dv = new FloatValidators() {};
    ValidationResult<Float> resultSuccess = dv.isGreaterThan(value, 1.1F);
    ValidationResult<Float> resultSuccessCustomMessage = dv.isGreaterThan(value, 1.1F, "custom");

    ValidationResult<Float> resultFailure = dv.isGreaterThan(value, 1.2F);
    ValidationResult<Float> resultFailureCustomMessage = dv.isGreaterThan(value, 1.2F, "custom");

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
    Float value = 1.2F;

    FloatValidators dv = new FloatValidators() {};
    ValidationResult<Float> resultSuccess = dv.isSmallerThan(value, 1.3F);
    ValidationResult<Float> resultSuccessCustomMessage = dv.isSmallerThan(value, 1.3F, "custom");

    ValidationResult<Float> resultFailure = dv.isSmallerThan(value, 1.2F);
    ValidationResult<Float> resultFailureCustomMessage = dv.isSmallerThan(value, 1.2F, "custom");

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
