package com.yaozhou.permission.common.validator;

import com.yaozhou.permission.common.validator.impl.ShortSizeValiadator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 验证Short的大小
 * @author Yao.Zhou
 * @since 2018/7/22 19:33
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(ShortSize.List.class)
@Documented
@Constraint(validatedBy = {ShortSizeValiadator.class})
public @interface ShortSize {

    String message() default "{javax.validation.constraints.Size.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * @return size the element must be higher or equal to
     */
    short min() default 0;

    /**
     * @return size the element must be lower or equal to
     */
    short max() default Short.MAX_VALUE;

    /**
     * Defines several {@link ShortSize} annotations on the same element.
     *
     * @see ShortSize
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        ShortSize[] value();
    }

}
