package cn.edu.yzx.order.service;

import cn.edu.yzx.order.entity.MessageFromBefore;
import cn.edu.yzx.order.utils.ResultMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AskGptService {

    /**
     * 专业课查询
     *
     * @param message 实体
     * @return 查询结果
     */
    ResultMap selectProfessionFromGpt(MessageFromBefore message);

    /**
     * 英语作文修改
     *
     * @param message 实体
     * @return 查询结果
     */
    ResultMap selectEnglishFromGpt(MessageFromBefore message);

    /**
     * 数学问题求解
     *
     * @param message 实体
     * @return 查询结果
     */
    @PostMapping("math")
    ResultMap selectMathFromGpt(@RequestBody MessageFromBefore message);
}
