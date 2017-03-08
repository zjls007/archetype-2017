package com.cy.common;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Config {

    public static final String JDBC_URL;
    public static final String JDBC_USERNAME;
    public static final String JDBC_PASSWORD;

    static {
        ResourceBundle rb = PropertyResourceBundle.getBundle("config");

        JDBC_URL = rb.getString("jdbc.url");
        JDBC_USERNAME = rb.getString("jdbc.username");
        JDBC_PASSWORD = rb.getString("jdbc.password");
    }

}