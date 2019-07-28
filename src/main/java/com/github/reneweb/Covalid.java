package com.github.reneweb;

import com.github.reneweb.validators.StringValidators;

public interface Covalid<I, O> extends StringValidators {

  ValidationResult<O> validate(I input);
}
