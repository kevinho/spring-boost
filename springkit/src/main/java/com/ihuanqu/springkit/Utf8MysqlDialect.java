package com.ihuanqu.springkit;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by changbinhe on 27/10/2016.
 */
public class Utf8MysqlDialect extends MySQL5InnoDBDialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
    }
}
