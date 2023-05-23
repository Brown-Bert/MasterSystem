package cn.edu.yzx.order.service.impl;

import cn.edu.yzx.order.entity.MessageFromBefore;
import cn.edu.yzx.order.service.AskGptService;
import cn.edu.yzx.order.utils.AskGptUtil;
import cn.edu.yzx.order.utils.ResultMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service("askGptService")
public class AskGptServiceImpl implements AskGptService {

    /**
     * 专业课查询
     *
     * @param message 实体
     * @return 查询结果
     */
    @Override
    public ResultMap selectProfessionFromGpt(MessageFromBefore message){
        try{
            String chat = AskGptUtil.chat(message.getMessage());
            ResultMap resultMap = ResultMap.success(chat);
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            ResultMap fail = ResultMap.fail("搜索失败");
            return fail;
        }
    }

    /**
     * 英语作文修改
     *
     * @param message 实体
     * @return 查询结果
     */
    @Override
    public ResultMap selectEnglishFromGpt(MessageFromBefore message){
        try{
            String chat = AskGptUtil.chat(message.getMessage());
            ResultMap resultMap = ResultMap.success(chat);
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            ResultMap fail = ResultMap.fail("搜索失败");
            return fail;
        }
    }

    /**
     * 数学问题求解
     *
     * @param message 实体
     * @return 查询结果
     */
    @Override
    @PostMapping("math")
    public ResultMap selectMathFromGpt(@RequestBody MessageFromBefore message) {
//        System.out.println("message = " + message.getMessage());
        try{
            String chat = AskGptUtil.chat(message.getMessage());
            ResultMap resultMap = ResultMap.success(chat);
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            ResultMap fail = ResultMap.fail("搜索失败");
            return fail;
        }
    }
}
