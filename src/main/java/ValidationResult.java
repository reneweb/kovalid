import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ValidationResult<T> {
  private final T value;
  private final boolean result;
  private final Optional<String> message;

  private ValidationResult(final T value, final boolean result, final Optional<String> message) {
    this.value = value;
    this.result = result;
    this.message = message;
  }

  public static <T> ValidationResult<T> of(final T value, final boolean result, final Optional<String> message) {
    return new ValidationResult<>(value, result, message);
  }

  public T getValue() {
    return value;
  }

  public boolean getResult() {
    return result;
  }

  public Optional<String> getMessage() {
    return message;
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
    if (result) {
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
