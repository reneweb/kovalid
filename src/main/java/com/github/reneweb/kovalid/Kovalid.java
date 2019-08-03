package com.github.reneweb.kovalid;

import com.github.reneweb.kovalid.validators.CollectionValidators;
import com.github.reneweb.kovalid.validators.DoubleValidators;
import com.github.reneweb.kovalid.validators.FloatValidators;
import com.github.reneweb.kovalid.validators.IntValidators;
import com.github.reneweb.kovalid.validators.LongValidators;
import com.github.reneweb.kovalid.validators.ObjectValidators;
import com.github.reneweb.kovalid.validators.OptionalValidators;
import com.github.reneweb.kovalid.validators.StringValidators;

public interface Kovalid<I, O> extends ObjectValidators, StringValidators, IntValidators,
                                       LongValidators, DoubleValidators, FloatValidators,
                                       CollectionValidators, OptionalValidators {

  ValidationResult<O> validate(I input);
}
