package cn.edu.yzx.order.controller;

import cn.edu.yzx.order.entity.MessageFromBefore;
import cn.edu.yzx.order.service.AskGptService;
import cn.edu.yzx.order.service.ConsumerService;
import cn.edu.yzx.order.utils.ResultMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Consumer)表控制层
 *
 * @author yzx
 * @since 2022-04-22 00:26:40
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("askGpt")
public class AskGptController {

    /**
     * 服务对象
     */
    @Resource
    private AskGptService askGptService;

    /**
     * 专业课查询
     *
     * @param message 实体
     * @return 查询结果
     */
    @PostMapping("profession")
    public ResponseEntity<ResultMap> selectProfessionFromGpt(@RequestBody MessageFromBefore message) {
//        System.out.println("message = " + message.getMessage());
        ResultMap map = this.askGptService.selectProfessionFromGpt(message);
        System.out.println("map = " + map.getData());
        return ResponseEntity.ok(map);
    }
    /**
     * 英语作文修改
     *
     * @param message 实体
     * @return 查询结果
     */
    @PostMapping("english")
    public ResponseEntity<ResultMap> selectEnglishFromGpt(@RequestBody MessageFromBefore message) {
//        System.out.println("message = " + message.getMessage());
        ResultMap map = this.askGptService.selectEnglishFromGpt(message);
        System.out.println("map = " + map.getData());
        return ResponseEntity.ok(map);
    }

    /**
     * 数学问题求解
     *
     * @param message 实体
     * @return 查询结果
     */
    @PostMapping("math")
    public ResponseEntity<ResultMap> selectMathFromGpt(@RequestBody MessageFromBefore message) {
//        System.out.println("message = " + message.getMessage());
        ResultMap map = this.askGptService.selectEnglishFromGpt(message);
        System.out.println("map = " + map.getData());
        return ResponseEntity.ok(map);
    }

    /**
     * 心理咨询
     *
     * @param message 实体
     * @return 查询结果
     */
    @PostMapping("counselor")
    public ResponseEntity<ResultMap> counselorFromGpt(@RequestBody MessageFromBefore message) {
        // 待写
        return ResponseEntity.ok(null);
    }
}
