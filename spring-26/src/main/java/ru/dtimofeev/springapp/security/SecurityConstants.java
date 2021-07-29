package ru.dtimofeev.springapp.security;

public class SecurityConstants {
    public static final String SECRET = "MY_SECRET";
    public static final long EXPIRATION_TIME = 5_000;
    public static final String TOKEN_PREFIX = "Token_best_prefix ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ROLES_CLAIM = "roles";
}
