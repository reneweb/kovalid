import java.util.Optional;

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
}
