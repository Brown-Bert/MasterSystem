package cn.edu.yzx.order.service;

import cn.edu.yzx.order.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Consumer)表服务接口
 *
 * @author yzx
 * @since 2022-04-22 00:26:40
 */
public interface ConsumerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Consumer queryById(String id);

    /**
     * 分页查询
     *
     * @param consumer 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Consumer> queryByPage(Consumer consumer, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param consumer 实例对象
     * @return 实例对象
     */
    Consumer insert(Consumer consumer);

    /**
     * 修改数据
     *
     * @param consumer 实例对象
     * @return 实例对象
     */
    Consumer update(Consumer consumer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 登录认证
     * @param usernaame 登录的用户名
     * @return “pass” 登录成果
     *          “unPass” 登陆失败
     */
    Boolean getPasswordByUsername(String usernaame, String password);

}
