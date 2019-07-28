import java.util.Optional;

public class ValidationResult<T> {
  private final T value;
  private final Optional<String> message;

  public ValidationResult(final T value, final Optional<String> message) {
    this.value = value;
    this.message = message;
  }

  public T getValue() {
    return value;
  }

  public Optional<String> getMessage() {
    return message;
  }
}
