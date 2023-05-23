package cn.edu.yzx.order.controller;

import cn.edu.yzx.order.entity.Consumer;
import cn.edu.yzx.order.service.ConsumerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * (Consumer)表控制层
 *
 * @author yzx
 * @since 2022-04-22 00:26:40
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("consumer")
public class ConsumerController {
    /**
     * 服务对象
     */
    @Resource
    private ConsumerService consumerService;

    /**
     * 分页查询
     *
     * @param consumer 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Consumer>> queryByPage(Consumer consumer, PageRequest pageRequest) {
        return ResponseEntity.ok(this.consumerService.queryByPage(consumer, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Consumer> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.consumerService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param consumer 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Consumer> add(Consumer consumer) {
        return ResponseEntity.ok(this.consumerService.insert(consumer));
    }

    /**
     * 编辑数据
     *
     * @param consumer 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Consumer> edit(Consumer consumer) {
        return ResponseEntity.ok(this.consumerService.update(consumer));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.consumerService.deleteById(id));
    }

    /**
     * 登录认证
     * @param consumer 把username 和 password封装到Consumer中
     * @return ok, data为Boolean值
     */
    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Consumer consumer){
        String username = consumer.getUsername();
        String password = consumer.getPassword();
        Boolean res = consumerService.getPasswordByUsername(username, password);
//        生成token返回给前端
        String token="token";
        Map<String, String> resMap = new HashMap<>();
        resMap.put("flag", res.toString());
        resMap.put("token", token);
        return ResponseEntity.ok(resMap);
    }


}

