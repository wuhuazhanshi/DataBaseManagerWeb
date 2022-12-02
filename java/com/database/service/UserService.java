package com.database.service;

import jakarta.servlet.http.HttpSession;

public interface UserService {

    boolean auth(String username, String password, HttpSession session);

    boolean ist(String username, String email, String password, HttpSession session);

    boolean reset(String email, HttpSession session);
}
