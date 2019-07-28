public class ValidationResult<T> {
  private final T value;
  private final String message;

  private ValidationResult(final T value, final String message) {
    this.value = value;
    this.message = message;
  }

  public static <T> ValidationResult<T> of(final T value, final String message) {
    return new ValidationResult<>(value, message);
  }

  public T getValue() {
    return value;
  }

  public String getMessage() {
    return message;
  }
}
