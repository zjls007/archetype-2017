package com.cy.common.util;

import com.cy.common.exception.ParamException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by zxj on 2017/3/3.
 */
public class ValidateUtil {

    private static Validator validation = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T t) {
        Set<ConstraintViolation<T>> set = validation.validate(t);
        if (!set.isEmpty()) {
            for (ConstraintViolation<T> c : set) {
                throw new ParamException(c.getMessage());
            }
        }
    }

}
