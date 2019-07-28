package com.github.reneweb;

import com.github.reneweb.validators.ObjectValidators;
import com.github.reneweb.validators.StringValidators;

public interface Covalid<I, O> extends StringValidators, ObjectValidators {

  ValidationResult<O> validate(I input);
}
