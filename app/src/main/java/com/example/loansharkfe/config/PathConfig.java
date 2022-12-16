package com.example.loansharkfe.config;

public class PathConfig {

    public static final String basePath = "http://192.168.0.171:8080/api";

    public static final String versionPath = "/v1";

    public static final String fullBasePath = basePath + versionPath;

    public static final String loginPath = fullBasePath + "/auth/login";

}
