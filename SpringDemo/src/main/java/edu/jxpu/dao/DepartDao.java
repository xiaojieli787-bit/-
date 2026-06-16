package edu.jxpu.dao;

import edu.jxpu.model.Depart;
import org.apache.ibatis.annotations.*;
import java.util.List;

// 注意：不用加@Repository！MyBatis会自动扫描生成代理
public interface DepartDao {

    // 完全照搬你原来的SQL和别名，实体类Depart不用改
    @Select("SELECT id as oid, dept_no as dno, dept_name as dname, dept_info as dinfo FROM mis_dept")
    List<Depart> findAll();

    // 根据ID查询（修改功能需要）
    @Select("SELECT id as oid, dept_no as dno, dept_name as dname, dept_info as dinfo FROM mis_dept WHERE id=#{id}")
    Depart findById(@Param("id") int id);

    // 添加部门：和你原来的SQL完全一致
    @Insert("INSERT INTO mis_dept(dept_no, dept_name, dept_info) VALUES(#{dno}, #{dname}, #{dinfo})")
    int save(Depart dept); // MyBatis返回受影响行数（int）

    // 修改部门：和你原来的SQL完全一致
    @Update("UPDATE mis_dept SET dept_no=#{dno}, dept_name=#{dname}, dept_info=#{dinfo} WHERE id=#{oid}")
    int update(Depart dept);

    // 删除部门：和你原来的SQL完全一致
    @Delete("DELETE FROM mis_dept WHERE id=#{id}")
    int delete(@Param("id") int id);
}