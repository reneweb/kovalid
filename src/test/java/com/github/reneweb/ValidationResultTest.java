package com.github.reneweb;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ValidationResultTest {

  @Test
  void successShouldCreateASuccessfulValidationResult() {
    ValidationResult<String> result = ValidationResult.success("some value");
    assertThat(result.isValid()).isTrue();
    assertThat(result.getValue()).isEqualTo("some value");
  }

  @Test
  void failureShouldCreateAFailedValidationResult() {
    ValidationResult<String> result = ValidationResult.failure("some value", "message");
    assertThat(result.isValid()).isFalse();
    assertThat(result.getValue()).isEqualTo("some value");
    assertThat(result.getMessage()).isEqualTo("message");
  }

  @Test
  void andShouldCombineResults() {
    ValidationResult<String> resultFirst = ValidationResult.success("some value");
    ValidationResult<String> resultSecond = ValidationResult.failure("some other value", "failed");
    ValidationResult<Integer> resultThird = ValidationResult.success(5);

    ValidationResult<List> result = resultFirst.and(resultSecond).and(resultThird);
    assertThat(result.isValid()).isFalse();
    assertThat(result.getValue()).isEqualTo(Arrays.<Object>asList("some value", "some other value", 5));
    assertThat(result.getMessage()).isEqualTo("failed");
    assertThat(result.getSubValidationResults()).contains(
        SubValidationResult.from(resultFirst),
        SubValidationResult.from(resultSecond),
        SubValidationResult.from(resultThird));
  }

  @Test
  void getSuccessfulSubValidationResultsShouldFilterSuccessfulSubResults() {
    ValidationResult<String> resultFirst = ValidationResult.success("some value");
    ValidationResult<String> resultSecond = ValidationResult.failure("some other value", "failed");
    ValidationResult<Integer> resultThird = ValidationResult.success(5);

    ValidationResult<List> result = resultFirst.and(resultSecond).and(resultThird);
    assertThat(result.getSuccessfulSubValidationResults()).contains(
        SubValidationResult.from(resultFirst),
        SubValidationResult.from(resultThird));
  }

  @Test
  void getFailedSubValidationResultsShouldFilterFailedSubResults() {
    ValidationResult<String> resultFirst = ValidationResult.success("some value");
    ValidationResult<String> resultSecond = ValidationResult.failure("some other value", "failed");
    ValidationResult<Integer> resultThird = ValidationResult.success(5);

    ValidationResult<List> result = resultFirst.and(resultSecond).and(resultThird);
    assertThat(result.getFailedSubValidationResults()).contains(
        SubValidationResult.from(resultSecond));
  }

  @Test
  void throwIfFailedShouldThrowValidationExceptionOnFailedResult() {
    assertThrows(
        ValidationException.class,
        () -> ValidationResult.failure("some value", "message").throwIfFailed());
  }

  @Test
  void throwIfFailedShouldNotThrowValidationExceptionOnSuccessfulResult() {
    ValidationResult.success("some value").throwIfFailed();
  }

  @Test
  void asCompletableFutureShouldCreateSuccessfulCompletedFutureIfSuccessfulResult() {
    String value = ValidationResult.success("some value").asCompletableFuture().join();
    assertThat(value).isEqualTo("some value");
  }

  @Test
  void asCompletableFutureShouldCreateExceptionalCompletedFutureIfFailedResult() {
    CompletionException completionException = assertThrows(
        CompletionException.class,
        () -> ValidationResult.failure("some value", "message").asCompletableFuture().join());
    assertThat(completionException).hasCauseInstanceOf(ValidationException.class);
  }

  @Test
  void asOptionalShouldReturnPresentOptionalIfValidationSuccessful() {
    Optional<String> value = ValidationResult.success("some value").asOptional();
    assertThat(value).isPresent();
    assertThat(value).contains("some value");
  }

  @Test
  void asOptionalShouldReturnAbsentOptionalIfValidationFailed() {
    Optional<String> value = ValidationResult.failure("some value", "message").asOptional();
    assertThat(value).isNotPresent();
  }

  @Test
  void mapShouldMapToNewValue() {
    ValidationResult<Integer> value = ValidationResult.success("some value").map((v) -> 5);
    assertThat(value.isValid()).isTrue();
    assertThat(value.getValue()).isEqualTo(5);
  }

  @Test
  void toSuccessShouldTranslateResultToSuccessfulOne() {
    ValidationResult<String> value = ValidationResult.failure("some value", "message").toSuccess();
    assertThat(value.isValid()).isTrue();
    assertThat(value.getValue()).isEqualTo("some value");
  }

  @Test
  void toFailedShouldTranslateResultToFailureOne() {
    ValidationResult<String> value = ValidationResult.success("some value").toFailed("message");
    assertThat(value.isValid()).isFalse();
    assertThat(value.getValue()).isEqualTo("some value");
    assertThat(value.getMessage()).isEqualTo("message");
  }

  @Test
  void resolveShouldTranslateToNewType() {
    Optional<String> valueSuccess = ValidationResult
        .success("some value")
        .resolve(Optional::of, (v, msg) -> Optional.empty());

    Optional<String> valueFailed = ValidationResult
        .failure("some value", "message")
        .resolve(Optional::of, (v, msg) -> Optional.empty());

    assertThat(valueSuccess).isPresent();
    assertThat(valueSuccess).contains("some value");

    assertThat(valueFailed).isNotPresent();
  }
}
