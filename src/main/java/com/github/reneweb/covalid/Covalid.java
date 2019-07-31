package com.github.reneweb.covalid;

import com.github.reneweb.covalid.validators.CollectionValidators;
import com.github.reneweb.covalid.validators.DoubleValidators;
import com.github.reneweb.covalid.validators.FloatValidators;
import com.github.reneweb.covalid.validators.IntValidators;
import com.github.reneweb.covalid.validators.LongValidators;
import com.github.reneweb.covalid.validators.ObjectValidators;
import com.github.reneweb.covalid.validators.OptionalValidators;
import com.github.reneweb.covalid.validators.StringValidators;

public interface Covalid<I, O> extends ObjectValidators, StringValidators, IntValidators,
                                       LongValidators, DoubleValidators, FloatValidators,
                                       CollectionValidators, OptionalValidators {

  ValidationResult<O> validate(I input);
}
