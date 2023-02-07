package com.example.loansharkfe.config;

public class PathConfig {

    public static final String basePath = "http://172.28.176.1:8080/api";

    public static final String versionPath = "/v1";

    public static final String fullBasePath = basePath + versionPath;

    public static final String loginPath = fullBasePath + "/auth/login";

    public static final String registerPath = fullBasePath + "/auth/register";

    public static final String getUserByUsernamePath = fullBasePath + "/user/username";

    public static final String getUserByIdPath = fullBasePath + "/user/id";

    public static final String getUserByEmailPath = fullBasePath + "/user/email";

    public static final String sendFriendRequestPath = fullBasePath + "/user/friends/request";

    public static final String updateProfilePicturePath = fullBasePath + "/user/set/profile/picture";

    public static final String manageFriendRequestPath = fullBasePath + "/user/friends/request";

    public static final String getUserProfileByIdPath = fullBasePath + "/user/profile";
}
