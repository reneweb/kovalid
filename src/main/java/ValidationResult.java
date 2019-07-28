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

  public static <T> ValidationResult<T> success(final T value) {
    return new ValidationResult<>(value, true, Optional.empty(), new ArrayList<>());
  }

  public static <T> ValidationResult<T> failure(final T value, final String message) {
    return new ValidationResult<>(value, false, Optional.of(message), new ArrayList<>());
  }

  public static <T> Builder<T> from(ValidationResult<T> from) {
    Builder<T> builder = new Builder<>();
    builder.value = from.value;
    builder.valid = from.valid;
    builder.message = from.message.orElse(null);
    builder.subValidationResults = from.subValidationResults;
    return builder;
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

  public static class Builder <T> {
    private T value;
    private boolean valid;
    private String message;
    private List<ValidationResult> subValidationResults = new ArrayList<>();

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

    public Builder<T> setSubValidationResults(List<ValidationResult> subValidationResults) {
      this.subValidationResults = subValidationResults;
      return this;
    }

    public ValidationResult<T> build() {
      return new ValidationResult<>(value, valid, Optional.ofNullable(message), subValidationResults);
    }
  }
}
