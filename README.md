# Kovalid
A simple, zero-dependency validation library for Java.

## Usage

### Maven dependency
```xml
<dependency>
    <groupId>com.github.reneweb</groupId>
    <artifactId>kovalid</artifactId>
    <version>0.1.2</version>
    <type>pom</type>
</dependency>
```

### Basic usage
```java
class MyValidator implements Kovalid<ToValidate, Integer> {
  public ValidationResult<ToValidate> validate(ToValidate input) {
    return isNotNullOrEmpty(input.aString)
        .and(isGreaterThan(input.aInteger, 0))
        .and(isPresent(input.aOptional, "This is not aOptional"))
        .map((v) -> input);
  }
}
```
There are a range of different built in validators some of which are used in this example.

They are combined using the 'and' operator which will result in a new validation result.
This new result will contain an untyped list of all the values from the different combined validations.
It will be marked as successful if all the combined results are successful, false otherwise.
The message of the result will be that of the first failed result of the combined ones and null if all of them succeeded.
The sub validation results list will contain all the combined validation results.

In the last step the value of the result is mapped to the original input, which makes it later on accessible from the validation result.

### Creating a custom result object
```java
public class MyValidator implements Kovalid<ToValidate, ToValidate> {
  public ValidationResult<ToValidate> validate(ToValidate input) {
    if (input.isComplexValidationTrue()) {
      return ValidationResult.success(input);
    } else {
      return ValidationResult.failed(input, "This did not work");
    }
  }
}
```
In this case a new validation result is created for cases where the built in validation are not viable.

### Working with validation results
```java
public static void main(String[] args) {
  MyValidator mv = new MyValidator();
  ValidationResult<String> result = mv.validate(input)
    .map(v -> "new value") // Map over the value
    .toFailed("failed message") // Change it to a failed validation with the given error message
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
