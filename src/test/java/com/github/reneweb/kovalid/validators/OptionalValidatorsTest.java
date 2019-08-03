package com.github.reneweb.kovalid.validators;

import com.github.reneweb.kovalid.ValidationResult;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OptionalValidatorsTest {
  @Test
  public void isPresent() {
    Optional<String> presentValue = Optional.of("value");
    Optional<String> absentValue = Optional.empty();

    OptionalValidators ov = new OptionalValidators() {};
    ValidationResult<Optional<String>> resultSuccess = ov.isPresent(presentValue);
    ValidationResult<Optional<String>> resultSuccessCustomMessage = ov.isPresent(presentValue, "custom");

    ValidationResult<Optional<String>> resultFailure = ov.isPresent(absentValue);
    ValidationResult<Optional<String>> resultFailureCustomMessage = ov.isPresent(absentValue, "custom");

    assertThat(resultSuccess.isValid()).isTrue();
    assertThat(resultSuccess.getValue()).isEqualTo(presentValue);
    assertThat(resultSuccessCustomMessage.isValid()).isTrue();
    assertThat(resultSuccessCustomMessage.getMessage()).isNull();
    assertThat(resultSuccessCustomMessage.getValue()).isEqualTo(presentValue);

    assertThat(resultFailure.isValid()).isFalse();
    assertThat(resultFailure.getValue()).isEqualTo(absentValue);
    assertThat(resultFailureCustomMessage.isValid()).isFalse();
    assertThat(resultFailureCustomMessage.getMessage()).isEqualTo("custom");
    assertThat(resultFailureCustomMessage.getValue()).isEqualTo(absentValue);
  }

  @Test
  public void containsInstanceOf() {
    Optional<String> value = Optional.of("value");

    OptionalValidators ov = new OptionalValidators() {};
    ValidationResult<Optional<String>> resultSuccess = ov.containsInstanceOf(value, String.class);
    ValidationResult<Optional<String>> resultSuccessCustomMessage = ov.containsInstanceOf(value, String.class, "custom");

    ValidationResult<Optional<String>> resultFailure = ov.containsInstanceOf(value, Integer.class);
    ValidationResult<Optional<String>> resultFailureCustomMessage = ov.containsInstanceOf(value, Integer.class, "custom");

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
  public void contains() {
    Optional<String> value = Optional.of("value");

    OptionalValidators ov = new OptionalValidators() {};
    ValidationResult<Optional<String>> resultSuccess = ov.contains(value, "value");
    ValidationResult<Optional<String>> resultSuccessCustomMessage = ov.contains(value, "value", "custom");

    ValidationResult<Optional<String>> resultFailure = ov.contains(value, "non-existent");
    ValidationResult<Optional<String>> resultFailureCustomMessage = ov.contains(value, "non-existent", "custom");

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
