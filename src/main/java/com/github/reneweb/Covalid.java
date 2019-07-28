package com.github.reneweb;

import com.github.reneweb.validators.DoubleValidators;
import com.github.reneweb.validators.FloatValidators;
import com.github.reneweb.validators.IntValidators;
import com.github.reneweb.validators.LongValidators;
import com.github.reneweb.validators.ObjectValidators;
import com.github.reneweb.validators.StringValidators;

public interface Covalid<I, O> extends ObjectValidators, StringValidators, IntValidators,
                                       LongValidators, DoubleValidators, FloatValidators {

  ValidationResult<O> validate(I input);
}
