# Covalid
A simple, zero-dependency validation library for Java.

## Usage

### Basic usage
```java
class MyValidator implements Covalid<ToValidate, Integer> {
  public ValidationResult<Integer> validate(ToValidate input) {
    return isNotNullOrEmpty(input.aString)
        .and(isGreaterThan(input.aInteger, 0))
        .and(isPresent(input.aOptional, "This is not aOptional"))
        .map(List::size);
  }
}
```

### Creating a custom result object
```java
public class MyValidator implements Covalid<ToValidate, ToValidate> {
  public ValidationResult<ToValidate> validate(ToValidate input) {
    if (input.isComplexValidationTrue()) {
      return ValidationResult.success(input);
    } else {
      return ValidationResult.failed(input, "This did not work");
    }
  }
}
```

### Working with validation results
```java
public static void main(String[] args) {
  MyValidator mv = new MyValidator();
  ValidationResult<String> result = mv.validate(input)
    .map(v -> "new value") // Map over the value
    .toFailed("failed message") // Change it to a failed validation, with the given error message
    .toSuccess(); // Turn back into a successful one

  // Turn into a optional
  Optional<String> resultOptional = result.asOptional();

  // Turn into a completable future. If the validation failed the future will be exceptionally.
  CompletableFuture<String> resultFuture = result.asCompletableFuture();

  // Resolve to a different type (in this case Optional)
  Optional<String> resolved = result.resolve(Optional::of, (value, message) -> Optional.empty());

  // Throw a ValidationException if validation failed
  result.throwIfFailed();

  // Get sub validation results (populated when combining results using the 'and' method)
  List<SubValidationResult> subValidationResults = result.getSubValidationResults();

  // Get successful sub validation results
  List<SubValidationResult> successfulSubValidationResults = result.getSuccessfulSubValidationResults();

  // Get failed sub validation results
  List<SubValidationResult> failedSubValidationResults = result.getFailedSubValidationResults();
}
```
