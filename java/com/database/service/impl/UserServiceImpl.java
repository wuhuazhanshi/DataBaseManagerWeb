package com.database.service.impl;

import com.database.dao.UserMapper;
import com.database.entity.User;
import com.database.service.UserService;
import com.database.utils.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {

    @Override
    public boolean auth(String username, String password, HttpSession session) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser(username, password);
            if (user == null) return false;
            session.setAttribute("user", user);
            return true;
        }
    }

    @Override
    public boolean ist(String username, String email, String password, HttpSession session) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            if (mapper.getUsername(username) != null || mapper.getEmail(email) != null) {
                return false;
            }
            mapper.setUser(username, email, password);
            return true;
        }
    }

    @Override
    public boolean reset(String email, HttpSession session) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            if (mapper.getEmail(email) != null) {
                mapper.updateUser(email);
                return true;
            } else {
                return false;
            }
        }
    }

}
