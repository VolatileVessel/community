package com.lgs.springboot.demo.service;

import com.lgs.springboot.demo.DTO.PaginationDTO;
import com.lgs.springboot.demo.DTO.QuestionDTO;
import com.lgs.springboot.demo.exception.CustomizeErrorCode;
import com.lgs.springboot.demo.exception.CustomizeException;
import com.lgs.springboot.demo.exception.ICustomizeErrorCode;
import com.lgs.springboot.demo.mapper.QuestionExtMapper;
import com.lgs.springboot.demo.mapper.QuestionMapper;
import com.lgs.springboot.demo.mapper.UserMapper;
import com.lgs.springboot.demo.model.Question;
import com.lgs.springboot.demo.model.QuestionExample;
import com.lgs.springboot.demo.model.User;
import org.apache.ibatis.session.RowBounds;
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
    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(Integer page, Integer size) {
        //paginationDTO 针对分页的DTO
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        //总条数
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
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
        //List<Question> questions = questionMapper.list(offset, size);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
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
       // Integer totalCount = questionMapper.countByUserId(userId);
        QuestionExample questionexample = new QuestionExample();
        questionexample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount=(int) questionMapper.countByExample(questionexample);
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
      //  List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds(offset, size));
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
       // Question question=questionMapper.finById(id);
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
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
            questionMapper.insertSelective(question);
        }else {
            //保存
           // Question question1 = questionMapper.selectByPrimaryKey(question.getId());
            Question updateQuestion = new Question();
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setGmtModified(System.currentTimeMillis());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
             questionMapper.updateByExampleSelective(updateQuestion, example);

        }
    }


    public void incView(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        //返回int 类型，更新成功。
        questionExtMapper.incView(question);

        //阅读数加1
        /*Question question = questionMapper.selectByPrimaryKey(id);
        Question question = new Question();
        question.setViewCount(question.getViewCount()+1);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andIdEqualTo(id);
        questionMapper.updateByExampleSelective(question, example);*/
    }
}
