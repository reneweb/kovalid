package com.github.reneweb;

import com.github.reneweb.validators.IntValidators;
import com.github.reneweb.validators.ObjectValidators;
import com.github.reneweb.validators.StringValidators;

public interface Covalid<I, O> extends ObjectValidators, StringValidators, IntValidators {

  ValidationResult<O> validate(I input);
}
