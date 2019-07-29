package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionValidatorsTest {

  @Test
  public void hasAtLeastElements() {
    List<String> list = Arrays.asList("first", "second");

    CollectionValidators cv = new CollectionValidators() {};
    ValidationResult<List<String>> resultSuccess = cv.hasAtLeastElements(list, 2);
    ValidationResult<List<String>> resultSuccessCustomMessage = cv.hasAtLeastElements(list, 2, "custom");

    ValidationResult<List<String>> resultFailure = cv.hasAtLeastElements(list, 3);
    ValidationResult<List<String>> resultFailureCustomMessage = cv.hasAtLeastElements(list, 3, "custom");

    assertThat(resultSuccess.isValid()).isTrue();
    assertThat(resultSuccess.getValue()).isEqualTo(list);
    assertThat(resultSuccessCustomMessage.isValid()).isTrue();
    assertThat(resultSuccessCustomMessage.getMessage()).isNull();
    assertThat(resultSuccessCustomMessage.getValue()).isEqualTo(list);

    assertThat(resultFailure.isValid()).isFalse();
    assertThat(resultFailure.getValue()).isEqualTo(list);
    assertThat(resultFailureCustomMessage.isValid()).isFalse();
    assertThat(resultFailureCustomMessage.getMessage()).isEqualTo("custom");
    assertThat(resultFailureCustomMessage.getValue()).isEqualTo(list);
  }

  @Test
  public void containsElement() {
    List<String> list = Arrays.asList("first", "second");

    CollectionValidators cv = new CollectionValidators() {};
    ValidationResult<List<String>> resultSuccess = cv.containsElement(list, "second");
    ValidationResult<List<String>> resultSuccessCustomMessage = cv.containsElement(list, "second", "custom");

    ValidationResult<List<String>> resultFailure = cv.containsElement(list, "non-existent");
    ValidationResult<List<String>> resultFailureCustomMessage = cv.containsElement(list, "non-existent", "custom");

    assertThat(resultSuccess.isValid()).isTrue();
    assertThat(resultSuccess.getValue()).isEqualTo(list);
    assertThat(resultSuccessCustomMessage.isValid()).isTrue();
    assertThat(resultSuccessCustomMessage.getMessage()).isNull();
    assertThat(resultSuccessCustomMessage.getValue()).isEqualTo(list);

    assertThat(resultFailure.isValid()).isFalse();
    assertThat(resultFailure.getValue()).isEqualTo(list);
    assertThat(resultFailureCustomMessage.isValid()).isFalse();
    assertThat(resultFailureCustomMessage.getMessage()).isEqualTo("custom");
    assertThat(resultFailureCustomMessage.getValue()).isEqualTo(list);
  }
}
