package com.github.reneweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
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

  /**
   * Creates a new successful {@link ValidationResult} for the given object
   * @param value the object that has been successfully validated
   * @param <T> The type of the validated object
   * @return The created {@link ValidationResult}
   */
  public static <T> ValidationResult<T> success(final T value) {
    return new ValidationResult<>(value, true, null, new ArrayList<>());
  }

  /**
   * Creates a new failed {@link ValidationResult} for the given object
   * @param value the object that has been unsuccessfully validated
   * @param <T> The type of the validated object
   * @return The created {@link ValidationResult}
   */
  public static <T> ValidationResult<T> failure(final T value, final String message) {
    return new ValidationResult<>(value, false, message, new ArrayList<>());
  }

  /**
   * Returns the value for the {@link ValidationResult}. Can be null.
   * @return the value
   */
  public T getValue() {
    return value;
  }

  /**
   * Returns true if the validation result is successful. False otherwise
   * @return the validation result as a boolean
   */
  public boolean isValid() {
    return valid;
  }

  /**
   * The message that has been set for the validation result. Can be null.
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Combines the current with the given {@link ValidationResult}.
   * The returned {@link ValidationResult} will have a list of all the combined values - this list is untyped as the values can be of different types.
   * The valid boolean will be true if all the combined validations are successful, false otherwise.
   * The message will contain the message of the first failed combined validation result. If all of them are successful it will be null.
   * The current validation result and the given one will be added to the sub validation results list.
   *
   * @param otherResult The validation result to combine with this one.
   * @param <K> The type of the other validation result
   * @return A new validation results which contains the current one and the given one in the sub validation results list.
   */
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
      @SuppressWarnings("unchecked")
      List<Object> values = new ArrayList<>((List<Object>)this.value);
      values.add(otherResult.value);

      List<SubValidationResult> validationResults = new ArrayList<>(this.subValidationResults);
      validationResults.add(SubValidationResult.from(otherResult));

      return new ValidationResult<>(values, this.valid && otherResult.valid, resolveMessage(otherResult), validationResults);
    }
  }

  /**
   * Returns the {@link SubValidationResult} list
   * @return the {@link SubValidationResult} list
   */
  public List<SubValidationResult> getSubValidationResults() {
    return subValidationResults;
  }

  /**
   * Returns the {@link SubValidationResult} list only containing successful validation results
   * @return the {@link SubValidationResult} list
   */
  public List<SubValidationResult> getSuccessfulSubValidationResults() {
    return subValidationResults
        .stream()
        .filter(SubValidationResult::isValid)
        .collect(Collectors.toList());
  }

  /**
   * Returns the {@link SubValidationResult} list only containing failed validation results
   * @return the {@link SubValidationResult} list
   */
  public List<SubValidationResult> getFailedSubValidationResults() {
    return subValidationResults
        .stream()
        .filter(subValidationResult -> !subValidationResult.isValid())
        .collect(Collectors.toList());
  }

  /**
   * Throws a {@link ValidationException} if the validation result is unsuccessful
   */
  public void throwIfFailed() {
    if (!valid && message != null) {
      throw new ValidationException(message, subValidationResults);
    } else if (!valid) {
      throw new ValidationException(subValidationResults);
    }
  }

  /**
   * Returns a {@link CompletableFuture} containing the value of the validation result.
   * If the validation result is unsuccessful the {@link CompletableFuture} will be exceptional with a {@link ValidationException};
   * @return the resulting {@link CompletableFuture}
   */
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

  /**
   * Returns a {@link Optional<T>} of the result is valid, otherwise an empty {@link Optional<T>}
   * @return the validation result as an optional
   */
  public Optional<T> asOptional() {
    if (valid) {
      return Optional.of(value);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Maps the value of the validation result to a new value
   * @param mapper The function to map to the new value
   * @param <K> The new type of the value
   * @return The validation result with the new mapped value
   */
  public <K> ValidationResult<K> map(Function<T, K> mapper) {
    return new ValidationResult<>(
        mapper.apply(this.value), this.valid, this.message, this.subValidationResults);
  }

  /**
   * Translates this {@link ValidationResult} to a successful validation. Has not effect if the validation is already successful.
   * @return The new successful {@link ValidationResult}
   */
  public ValidationResult<T> toSuccess() {
    return new ValidationResult<>(this.value, true, this.message, this.subValidationResults);
  }

  /**
   * Translates this {@link ValidationResult} to a failed validation. If the validation result is already failed only the message will change.
   * @return The new failed {@link ValidationResult}
   */
  public ValidationResult<T> toFailure(String message) {
    return new ValidationResult<>(this.value, false, message, this.subValidationResults);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ValidationResult<?> that = (ValidationResult<?>) o;
    return valid == that.valid &&
           Objects.equals(value, that.value) &&
           Objects.equals(message, that.message) &&
           Objects.equals(subValidationResults, that.subValidationResults);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, valid, message, subValidationResults);
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
}
