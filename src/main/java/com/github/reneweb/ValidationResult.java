package com.github.reneweb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ValidationResult<T> {
  private final T value;
  private final boolean valid;
  private final String message;
  private final List<SubValidationResult> subValidationResults;

  private ValidationResult(final T value,
                           final boolean valid,
                           final String message,
                           final List<SubValidationResult> subValidationResults) {
    this.value = value;
    this.valid = valid;
    this.message = message;
    this.subValidationResults = subValidationResults;
  }

  public static <T> ValidationResult<T> success(final T value) {
    return new ValidationResult<>(value, true, null, new ArrayList<>());
  }

  public static <T> ValidationResult<T> failure(final T value, final String message) {
    return new ValidationResult<>(value, false, message, new ArrayList<>());
  }

  public static <T> Builder<T> from(ValidationResult<T> from) {
    Builder<T> builder = new Builder<>();
    builder.value = from.value;
    builder.valid = from.valid;
    builder.message = from.message;
    builder.subValidationResults = from.subValidationResults;
    return builder;
  }

  public T getValue() {
    return value;
  }

  public boolean isValid() {
    return valid;
  }

  public String getMessage() {
    return message;
  }

  public <K> ValidationResult<List> and(ValidationResult<K> otherResult) {
    if (subValidationResults.isEmpty()) {
      List<Object> values = new ArrayList<>();
      values.add(this.value);
      values.add(otherResult.value);

      List<SubValidationResult> validationResults = new ArrayList<>();
      validationResults.add(SubValidationResult.from(this));
      validationResults.add(SubValidationResult.from(otherResult));

      return new ValidationResult<>(values, this.valid && otherResult.valid, resolveMessage(otherResult), validationResults);
    } else {
      List<Object> values = new ArrayList<>(this.subValidationResults);
      values.add(otherResult.value);

      List<SubValidationResult> validationResults = new ArrayList<>(this.subValidationResults);
      validationResults.add(SubValidationResult.from(otherResult));

      return new ValidationResult<>(values, this.valid && otherResult.valid, resolveMessage(otherResult), validationResults);
    }
  }

  public List<SubValidationResult> getSubValidationResults() {
    return subValidationResults;
  }

  public List<SubValidationResult> getSuccessfulSubValidationResults() {
    return subValidationResults
        .stream()
        .filter(SubValidationResult::isValid)
        .collect(Collectors.toList());
  }

  public List<SubValidationResult> getFailedSubValidationResults() {
    return subValidationResults
        .stream()
        .filter(subValidationResult -> !subValidationResult.isValid())
        .collect(Collectors.toList());
  }

  public void throwIfFailed() {
    if (message != null) {
      throw new ValidationException(message, subValidationResults);
    } else {
      throw new ValidationException(subValidationResults);
    }
  }

  public CompletableFuture<T> asCompletableFuture() {
    CompletableFuture<T> future = new CompletableFuture<>();
    if (valid) {
      future.complete(value);
    } else {
      if (message != null) {
        future.completeExceptionally(new ValidationException(message, subValidationResults));
      } else {
        future.completeExceptionally(new ValidationException(subValidationResults));
      }
    }

    return future;
  }

  private <K> String resolveMessage(final ValidationResult<K> otherResult) {
    if (this.message != null && !this.valid) {
      return this.message;
    } else if(otherResult.message != null && !otherResult.valid) {
      return otherResult.message;
    } else {
      return otherResult.message;
    }
  }

  public static class Builder <T> {
    private T value;
    private boolean valid;
    private String message;
    private List<SubValidationResult> subValidationResults = new ArrayList<>();

    public <K> Builder<K> setValue(K value) {
      Builder<K> builder = new Builder<>();
      builder.value = value;
      builder.valid = valid;
      builder.message = message;
      builder.subValidationResults = subValidationResults;
      return builder;
    }

    public Builder<T> setValid(boolean valid) {
      this.valid = valid;
      return this;
    }

    public Builder<T> setMessage(String message) {
      this.message = message;
      return this;
    }

    public Builder<T> setSubValidationResults(List<SubValidationResult> subValidationResults) {
      this.subValidationResults = subValidationResults;
      return this;
    }

    public ValidationResult<T> build() {
      return new ValidationResult<>(value, valid, message, subValidationResults);
    }
  }
}
