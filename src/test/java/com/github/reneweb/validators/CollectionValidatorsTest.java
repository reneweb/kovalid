package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionValidatorsTest {

  @Test
  public void hasAtLeastElements() {
    List<String> list = Arrays.asList("first", "second");

    CollectionValidators cv = new CollectionValidators() {};
    ValidationResult<List<String>> resultSuccess = cv.hasAtLeastElements(list, 2);
    ValidationResult<List<String>> resultSuccessCustomMessage = cv.hasAtLeastElements(list, 2, "custom");

    ValidationResult<List<String>> resultFailure = cv.hasAtLeastElements(list, 3);
    ValidationResult<List<String>> resultFailureCustomMessage = cv.hasAtLeastElements(list, 3, "custom");

    assertEquals(resultSuccess.isValid(), true);
    assertEquals(resultSuccess.getValue(), list);
    assertEquals(resultSuccessCustomMessage.isValid(), true);
    assertEquals(resultSuccessCustomMessage.getMessage(), null);
    assertEquals(resultSuccessCustomMessage.getValue(), list);

    assertEquals(resultFailure.isValid(), false);
    assertEquals(resultFailure.getValue(), list);
    assertEquals(resultFailureCustomMessage.isValid(), false);
    assertEquals(resultFailureCustomMessage.getMessage(), "custom");
    assertEquals(resultFailureCustomMessage.getValue(), list);
  }
}
