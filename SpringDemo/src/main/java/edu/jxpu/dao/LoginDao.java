package edu.jxpu.dao;

import edu.jxpu.model.LoginUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface LoginDao {

    // 方法1：验证登录（和你原来的逻辑完全一致）
    @Select("SELECT count(*) FROM mis_user WHERE account=#{account} AND pwd=#{password}")
    int loginCheck(@Param("account") String account, @Param("password") String password);

    // 方法2：简化版登录验证（和你原来的逻辑完全一致）
    @Select("SELECT count(*) FROM mis_user WHERE account=#{account} AND pwd=#{password}")
    int loginCheck2(@Param("account") String account, @Param("password") String password);

    // 登录返回用户对象（和你原来的SQL完全一致）
    @Select("SELECT account, role FROM mis_user WHERE account=#{account} AND pwd=#{password}")
    LoginUser loginForUser(@Param("account") String account, @Param("password") String password);
}