package com.yaozhou.permission.model;

import lombok.*;

import java.util.Set;

/**
 * @author Yao.Zhou
 * @since 2018/7/31 18:07
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String subject;
    private String message;
    private Set<String> receivers;

}
