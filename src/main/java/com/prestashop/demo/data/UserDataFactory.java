package com.prestashop.demo.data;

import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

import static com.prestashop.demo.config.ConfigurationManager.configuration;


public final class UserDataFactory {

    private static final Faker faker = new Faker(new Locale(configuration().faker()));
    private static final Logger logger = LogManager.getLogger(UserDataFactory.class);

    private UserDataFactory() {
    }
}
