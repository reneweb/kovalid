package com.github.reneweb;

public interface Covalid<I, O> {

  ValidationResult<O> validate(I input);
}