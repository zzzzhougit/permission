package com.yaozhou.permission.generator;

import org.mybatis.generator.api.ShellRunner;

/**
 * @author Yao.Zhou
 * @since 2018/7/17 21:33
 */
public class Generate {

    public static void main(String[] args) {
        args = new String[] { "-configfile", "src\\main\\java\\com\\yaozhou\\permission\\generator\\generator.xml", "-overwrite" };
        ShellRunner.main(args);
    }

}
