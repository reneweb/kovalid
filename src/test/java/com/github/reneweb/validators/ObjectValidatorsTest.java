package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectValidatorsTest {
  @Test
  public void isNotNull() {
    String value = "value";

    ObjectValidators ov = new ObjectValidators() {};
    ValidationResult<String> resultSuccess = ov.isNotNull(value);
    ValidationResult<String> resultSuccessCustomMessage = ov.isNotNull(value, "custom");

    ValidationResult<String> resultFailure = ov.isNotNull(null);
    ValidationResult<String> resultFailureCustomMessage = ov.isNotNull(null, "custom");

    assertThat(resultSuccess.isValid()).isTrue();
    assertThat(resultSuccess.getValue()).isEqualTo(value);
    assertThat(resultSuccessCustomMessage.isValid()).isTrue();
    assertThat(resultSuccessCustomMessage.getMessage()).isNull();
    assertThat(resultSuccessCustomMessage.getValue()).isEqualTo(value);

    assertThat(resultFailure.isValid()).isFalse();
    assertThat(resultFailure.getValue()).isNull();
    assertThat(resultFailureCustomMessage.isValid()).isFalse();
    assertThat(resultFailureCustomMessage.getMessage()).isEqualTo("custom");
    assertThat(resultFailureCustomMessage.getValue()).isNull();
  }

  @Test
  public void isInstanceOf() {
    String value = "value";

    ObjectValidators ov = new ObjectValidators() {};
    ValidationResult<String> resultSuccess = ov.isInstanceOf(value, String.class);
    ValidationResult<String> resultSuccessCustomMessage = ov.isInstanceOf(value, String.class, "custom");

    ValidationResult<String> resultFailure = ov.isInstanceOf(value, Integer.class);
    ValidationResult<String> resultFailureCustomMessage = ov.isInstanceOf(value, Integer.class, "custom");

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
