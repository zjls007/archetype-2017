package com.cy.core;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by zxj on 2017/3/3.
 */
public class ValidateUtil {

    public static void main(String[] args) {
        Validator validation = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ValidBean>> set = validation.validate(null);
        if (!set.isEmpty()) {
            for (ConstraintViolation<ValidBean> v : set) {
                System.out.println(v.getMessage());
            }
        }
    }

    class ValidBean {

    }
}
