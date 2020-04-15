package com.lgs.springboot.demo.service;

import com.lgs.springboot.demo.DTO.PaginationDTO;
import com.lgs.springboot.demo.DTO.QuestionDTO;
import com.lgs.springboot.demo.mapper.QuestionMapper;
import com.lgs.springboot.demo.mapper.UserMapper;
import com.lgs.springboot.demo.model.Question;
import com.lgs.springboot.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        //paginationDTO 针对分页的DTO
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        //总条数
        Integer totalCount = questionMapper.count();
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1){
            page = 1;
        }
        if (page >totalPage) {
            page =totalPage;
        }
        //分页的逻辑。PaginationDTO里面写
        paginationDTO.setPagination(totalPage, page);
        Integer offset = (page - 1) * size;
        //questions 去和数据库一样的表中查询数据
        List<Question> questions = questionMapper.list(offset, size);
        //questionDTOS 需要管理用户的信息，以便于取到用户头像
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        //question.for
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);

        return paginationDTO;
    }

    public PaginationDTO listByUserId(Integer userId, Integer page, Integer size) {
        //paginationDTO 针对分页的DTO
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        //总条数
        Integer totalCount = questionMapper.countByUserId(userId);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1){
            page = 1;
        }
        if (page >totalPage) {
            page =totalPage;
        }
        //分页的逻辑。PaginationDTO里面写
        paginationDTO.setPagination(totalPage, page);

        Integer offset = (page - 1) * size;
        //questions 去和数据库一样的表中查询数据
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        //questionDTOS 需要管理用户的信息，以便于取到用户头像
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        //question.for
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    public QuestionDTO findById(Integer id) {
        Question question=questionMapper.finById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void insertOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建,插入
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            //保存
            Question question1 = questionMapper.finById(question.getId());
            question.setGmtModified(question1.getGmtCreate());
            //question.setGmtCreate(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }


}
