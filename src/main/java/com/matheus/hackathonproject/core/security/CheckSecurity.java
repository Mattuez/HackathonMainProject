package com.matheus.hackathonproject.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public interface CheckSecurity {

    public @interface Transaction {

        @PreAuthorize("hasAuthority('LER')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface canSearch {}
    }

    public @interface UserGroupAccessLevel {

        @PreAuthorize("@authenticationSecurity.userId == #userId")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface canAlterOwnPassword {}

        @PreAuthorize("@authenticationSecurity.userId == #userId or hasAuthority('ESCREVER')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface canAlterUser {}

        @PreAuthorize("hasAuthority('ESCREVER')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface canAlter {}

        @PreAuthorize("hasAuthority('LER') or #userId == @authenticationSecurity.getUserId()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface canSearchSelf {}

        @PreAuthorize("hasAuthority('LER')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface canSearch {}
    }
}
