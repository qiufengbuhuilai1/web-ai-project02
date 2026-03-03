package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    @Select("select count(*) from student where clazz_id=#{clazzId}")
    Integer getStudentCountByClazzId(Integer clazzId);

    List<Student> list(StudentQueryParam studentQueryParam);

    @Insert("insert into student(name,no,gender,phone,degree,clazz_id,id_card,is_college,address,graduation_date,create_time,update_time) " +
            "values (#{name},#{no},#{gender},#{phone},#{degree},#{clazzId},#{idCard},#{isCollege},#{address},#{graduationDate},#{createTime},#{updateTime})")
    void insert(Student student);

    @Select("select * from student where id=#{id}")
    Student getById(Integer id);

    @Update("update student set name=#{name},no=#{no},gender=#{gender},phone=#{phone},degree=#{degree},clazz_id=#{clazzId},id_card=#{idCard},is_college=#{isCollege},address=#{address},graduation_date=#{graduationDate},update_time=#{updateTime} where id=#{id}")
    void updateById(Student student);


    void deleteByIds(List<Integer> ids);

    @Update("update student set violation_score=violation_score+#{score},violation_count=violation_count+1,update_time=now() where id=#{id}")
    void violateOperation(Integer id, Integer score);

    @MapKey("pos")
    List<Map<String, Object>> studentCountDate();

    @MapKey("name")
    List<Map<String, Object>> countStudentDegreeData();
}
