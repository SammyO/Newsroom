package com.oddhov.newsroom.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by sammy on 07/09/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface NetworkScope {
}
