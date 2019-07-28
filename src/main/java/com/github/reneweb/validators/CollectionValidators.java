package com.github.reneweb.validators;

import com.github.reneweb.ValidationResult;

import java.util.Collection;

public interface CollectionValidators {

  default <E, T extends Collection<E>> ValidationResult<T> hasAtLeastElements(T value, int count) {
    return hasAtLeastElements(value, count, String.format("Collection has less than %s elements", count));
  }

  default <E, T extends Collection<E>> ValidationResult<T> hasAtLeastElements(T value, int count, String invalidMessage) {
    if (value.size() >= count) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }

  default <E, T extends Collection<E>> ValidationResult<T> containsElement(T value, E element) {
    return hasAtLeastElements(value, element, "Collection does not contain element");
  }

  default <E, T extends Collection<E>> ValidationResult<T> hasAtLeastElements(T value, E element, String invalidMessage) {
    if (value.contains(element)) {
      return ValidationResult.success(value);
    } else {
      return ValidationResult.failure(value, invalidMessage);
    }
  }
}
