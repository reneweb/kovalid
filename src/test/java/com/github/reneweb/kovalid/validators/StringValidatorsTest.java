package com.github.reneweb.kovalid.validators;

import com.github.reneweb.kovalid.ValidationResult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringValidatorsTest {
  @Test
  public void isNotNullOrEmpty() {
    String value = "value";

    StringValidators sv = new StringValidators() {};
    ValidationResult<String> resultSuccess = sv.isNotNullOrEmpty(value);
    ValidationResult<String> resultSuccessCustomMessage = sv.isNotNullOrEmpty(value, "custom");

    ValidationResult<String> resultFailure = sv.isNotNullOrEmpty(null);
    ValidationResult<String> resultFailureCustomMessage = sv.isNotNullOrEmpty(null, "custom");

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
  public void hasLength() {
    String value = "value";

    StringValidators sv = new StringValidators() {};
    ValidationResult<String> resultSuccess = sv.hasLength(value, 5);
    ValidationResult<String> resultSuccessCustomMessage = sv.hasLength(value, 5, "custom");

    ValidationResult<String> resultFailure = sv.hasLength(value, 4);
    ValidationResult<String> resultFailureCustomMessage = sv.hasLength(value, 4, "custom");

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
    String value = "value";

    StringValidators sv = new StringValidators() {};
    ValidationResult<String> resultSuccess = sv.contains(value, "alu");
    ValidationResult<String> resultSuccessCustomMessage = sv.contains(value, "alu", "custom");

    ValidationResult<String> resultFailure = sv.contains(value, "alur");
    ValidationResult<String> resultFailureCustomMessage = sv.contains(value, "alur", "custom");

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
  public void startsWith() {
    String value = "value";

    StringValidators sv = new StringValidators() {};
    ValidationResult<String> resultSuccess = sv.startsWith(value, "valu");
    ValidationResult<String> resultSuccessCustomMessage = sv.startsWith(value, "valu", "custom");

    ValidationResult<String> resultFailure = sv.startsWith(value, "alu");
    ValidationResult<String> resultFailureCustomMessage = sv.startsWith(value, "alu", "custom");

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
  public void endsWith() {
    String value = "value";

    StringValidators sv = new StringValidators() {};
    ValidationResult<String> resultSuccess = sv.endsWith(value, "alue");
    ValidationResult<String> resultSuccessCustomMessage = sv.endsWith(value, "alue", "custom");

    ValidationResult<String> resultFailure = sv.endsWith(value, "alu");
    ValidationResult<String> resultFailureCustomMessage = sv.endsWith(value, "alu", "custom");

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
  public void equals() {
    String value = "value";

    StringValidators sv = new StringValidators() {};
    ValidationResult<String> resultSuccess = sv.equals(value, "value");
    ValidationResult<String> resultSuccessCustomMessage = sv.equals(value, "value", "custom");

    ValidationResult<String> resultFailure = sv.equals(value, "valu");
    ValidationResult<String> resultFailureCustomMessage = sv.equals(value, "valu", "custom");

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
  public void matchesRegex() {
    String value = "value";

    StringValidators sv = new StringValidators() {};
    ValidationResult<String> resultSuccess = sv.matchesRegex(value, ".*");
    ValidationResult<String> resultSuccessCustomMessage = sv.matchesRegex(value, ".*", "custom");

    ValidationResult<String> resultFailure = sv.matchesRegex(value, "[abc]");
    ValidationResult<String> resultFailureCustomMessage = sv.matchesRegex(value, "[abc]", "custom");

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
