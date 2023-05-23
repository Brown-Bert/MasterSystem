package cn.edu.yzx.order.controller;


import cn.edu.yzx.order.entity.Orderlist;
import cn.edu.yzx.order.service.OrderlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Orderlist)表控制层
 *
 * @author yzx
 * @since 2022-04-22 12:51:35
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("orderlist")
public class OrderlistController {
    /**
     * 服务对象
     */
    @Resource
    private OrderlistService orderlistService;

    /**
     * 分页查询
     *
     * @param
     * @param
     * @return 查询结果
     */
    @PostMapping("page")
    public ResponseEntity<List<Orderlist>> queryByPage(@RequestBody Map<String, Object> map) {
        Integer page = (Integer) map.get("page");
        Integer num = (Integer) map.get("num");
        List<Orderlist> orderlistList = this.orderlistService.queryByPage(page, num);
        return ResponseEntity.ok(orderlistList);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Orderlist> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.orderlistService.queryById(id));
    }

//    /**
//     * 新增数据
//     *
//     * @param orderlist 实体
//     * @return 新增结果
//     */
//    @PostMapping
//    public ResponseEntity<Orderlist> add(Orderlist orderlist) {
//        return ResponseEntity.ok(this.orderlistService.insert(orderlist));
//    }

    /**
     * 编辑数据
     *
     * @param orderlist 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Orderlist> edit(Orderlist orderlist) {
        return ResponseEntity.ok(this.orderlistService.update(orderlist));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.orderlistService.deleteById(id));
    }

    /**
     * 新增
     * @param
     * @return ok, data为Boolean值
     */
    @PostMapping("add")
    public ResponseEntity<Map<String, String>> addOne(@RequestBody Orderlist orderlist){
//        String username = consumer.getUsername();
//        String password = consumer.getPassword();
//        Boolean res = consumerService.getPasswordByUsername(username, password);
//        生成token返回给前端
        int length = this.orderlistService.getAllLength();
        String flag = "";
        if (!orderlist.getId().equals("0")) {
            flag = "子新增";
        } else {
            flag = "新增";
        }
        this.orderlistService.insert(orderlist, length);
        Map<String, String> resMap = new HashMap<>();
        resMap.put("flag", flag);
        return ResponseEntity.ok(resMap);
    }

    /**
     * 查询可以显示的数据的长度
     *
     * @param
     * @param
     * @return 查询结果
     */
    @GetMapping("total")
    public ResponseEntity<Integer> queryTotalLength() {
        int totalLength = this.orderlistService.queryTotalLength();
        return ResponseEntity.ok(totalLength);
    }

    /**
     * 修改订单付钱
     *
     * @param
     * @param
     * @return 修改成功与否
     */
    @PostMapping("payfor")
    public ResponseEntity<Integer> payfor(@RequestBody Map<String, String> map) {
        System.out.println("map = " + map);
        String id = map.get("id");
        this.orderlistService.payfor(id);
        return ResponseEntity.ok(1);
    }

    /**
     * 出餐
     *
     * @param
     * @param
     * @return 修改成功与否
     */
    @PostMapping("out")
    public ResponseEntity<Integer> out(@RequestBody Map<String, String> map) {
        System.out.println("map = " + map);
        String id = map.get("id");
        this.orderlistService.out(id);
        return ResponseEntity.ok(1);
    }

    /**
     * 完成订单
     *
     * @param
     * @param
     * @return 修改成功与否
     */
    @PostMapping("finish")
    public ResponseEntity<Integer> finish(@RequestBody Map<String, String> map) {
        String id = map.get("id");
        this.orderlistService.finish(id);
        return ResponseEntity.ok(1);
    }

    /**
     * 修改
     * @param
     * @return ok, data为Boolean值
     */
    @PostMapping("update")
    public ResponseEntity<String> update(@RequestBody Orderlist orderlist){
        System.out.println("orderlist = " + orderlist);
        this.orderlistService.updateFinish(orderlist);
        return ResponseEntity.ok("1");
    }

    /**
     * 子查询
     */
    @PostMapping("fatherid")
    public ResponseEntity<List<Orderlist>> fatherid(@RequestBody Map<String, String> map){
        String id = map.get("fatherid");

        List<Orderlist> orderlists = this.orderlistService.queryByFatherId(id);
        return ResponseEntity.ok(orderlists);
    }
}

