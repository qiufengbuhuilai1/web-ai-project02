package com.itheima.mapper;

import com.itheima.pojo.ClassQueryParam;
import com.itheima.pojo.Clazz;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassMapper {

    List<Clazz> list(ClassQueryParam classQueryParam);

    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void addClass(Clazz clazz);


    @Select("select * from clazz where id=#{id}")
    Clazz getById(Integer id);

    @Update("update clazz set name=#{name},room=#{room},begin_date=#{beginDate},end_date=#{endDate},master_id=#{masterId},subject=#{subject},update_time=#{updateTime} where id=#{id}")
    void updateById(Clazz clazz);

    @Delete("delete from clazz where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from clazz")
    List<Clazz> listAll();
}
