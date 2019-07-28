import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ValidationResult<T> {
  private final T value;
  private final boolean valid;
  private final Optional<String> message;
  private final List<ValidationResult> subValidationResults;

  private ValidationResult(final T value, final boolean valid, final Optional<String> message, List<ValidationResult> subValidationResults) {
    this.value = value;
    this.valid = valid;
    this.message = message;
    this.subValidationResults = subValidationResults;
  }

  public static <T> ValidationResult<T> of(final T value, final boolean valid) {
    return new ValidationResult<>(value, valid, Optional.empty(), new ArrayList<>());
  }

  public static <T> ValidationResult<T> of(final T value, final boolean valid, final String message) {
    return new ValidationResult<>(value, valid, Optional.of(message), new ArrayList<>());
  }

  public interface ValidationResultFromBuilder<K> {
    ValidationResult<K> of(final K value, final boolean valid, final String message);
  }

  public static <T> ValidationResultFromBuilder<T> from(ValidationResult<T> from) {
    return (value, valid, message) -> new ValidationResult<T>(value, valid, Optional.of(message), from.subValidationResults);
  }

  public T getValue() {
    return value;
  }

  public boolean isValid() {
    return valid;
  }

  public Optional<String> getMessage() {
    return message;
  }

  public <K> ValidationResult<Void> and(ValidationResult<K> otherResult) {
    if (subValidationResults.isEmpty()) {
      List<ValidationResult> validationResults = new ArrayList<>();
      validationResults.add(this);
      validationResults.add(otherResult);
      return new ValidationResult<>(null, this.valid && otherResult.valid, Optional.empty(), validationResults);
    } else {
      List<ValidationResult> validationResults = new ArrayList<>(this.subValidationResults);
      validationResults.add(otherResult);
      return new ValidationResult<>(null, this.valid && otherResult.valid, Optional.empty(), validationResults);
    }
  }

  public void addSubValidationResults(ValidationResult... validationResults) {
    subValidationResults.addAll(Arrays.asList(validationResults));
  }

  public void throwIfFailed() {
    if (message.isPresent()) {
      throw new ValidationException(message.get());
    } else {
      throw new ValidationException();
    }
  }

  public CompletableFuture<T> asCompletableFuture() {
    CompletableFuture<T> future = new CompletableFuture<>();
    if (valid) {
      future.complete(value);
    } else {
      if (message.isPresent()) {
        future.completeExceptionally(new ValidationException(message.get()));
      } else {
        future.completeExceptionally(new ValidationException());
      }
    }

    return future;
  }
}
