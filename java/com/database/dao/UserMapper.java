package com.database.dao;

import com.database.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("select * from t_user where username = #{username} and password = #{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

    @Select("select * from t_user where username = #{username}")
    User getUsername(@Param("username") String username);

    @Select("select * from t_user where email = #{email}")
    User getEmail(@Param("email") String email);

    @Insert("insert into t_user values(#{username}, #{email}, #{password})")
    int setUser(@Param("username") String username, @Param("email") String email, @Param("password") String password);

    @Update("update t_user set password='123456' where email = #{email}")
    int updateUser(@Param("email") String email);

}
