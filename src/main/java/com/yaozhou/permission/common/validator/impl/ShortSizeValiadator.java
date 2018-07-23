package com.yaozhou.permission.common.validator.impl;

import com.yaozhou.permission.common.validator.ShortSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Yao.Zhou
 * @since 2018/7/22 19:37
 */
public class ShortSizeValiadator implements ConstraintValidator<ShortSize, Short> {

    private short min = Short.MIN_VALUE;
    private short max = Short.MAX_VALUE;

    @Override
    public void initialize(ShortSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Short value, ConstraintValidatorContext context) {
        if (null == value || value > max || value < min) {
            return false;
        }

        return true;
    }

}
