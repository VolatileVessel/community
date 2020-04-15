package com.lgs.springboot.demo.mapper;

import com.lgs.springboot.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;



@Mapper
public interface QuestionMapper {
    @Insert("insert into question ( title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag}) ")
     void insert(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(int offset, Integer size);

    @Select("select count(1) from question ")
    Integer count();
    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(Integer userId, Integer offset, Integer size);
    @Select("select count(1) from question where creator=#{userId} ")
    Integer countByUserId(Integer userId);
    @Select("select * from question where id=#{id}")
    Question finById(Integer id);
    @Update("update question set title=#{title},description=#{description} ,tag=#{tag},gmt_modified=#{gmtModified} where id =#{id}")
    void update(Question question);
}

