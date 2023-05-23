package cn.edu.yzx.order.service;

import cn.edu.yzx.order.entity.Orderlist;

import java.util.List;

/**
 * (Orderlist)表服务接口
 *
 * @author yzx
 * @since 2022-04-22 12:51:35
 */
public interface OrderlistService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Orderlist queryById(String id);

    /**
     * 通过fatherid查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    List<Orderlist> queryByFatherId(String id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param num  每页多少
     * @return 查询结果
     */
    List<Orderlist> queryByPage(Integer page, Integer num);

    /**
     * 新增数据
     *
     * @param orderlist 实例对象
     * @return 实例对象
     */
    Boolean insert(Orderlist orderlist, Integer length);

    /**
     * 修改数据
     *
     * @param orderlist 实例对象
     * @return 实例对象
     */
    Orderlist update(Orderlist orderlist);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 查询数据有多少条
     *
     *
     * @return 查询条数
     */
    int getAllLength();


    /**
     * 查询所有可以显示的数据的长度
     *
     * @return 数据长度
     */
    int queryTotalLength();

    /**
     * 修改订单为已支付
     *
     * @return 数据长度
     */
    Boolean payfor(String id);

    /**
     *
     * @param id
     * @return
     */
    Boolean finish(String id);

    /**
     * 修改详细数据
     * @param orderlist
     * @return
     */
    Boolean updateFinish(Orderlist orderlist);

    /**
     * 出餐
     * @param id
     * @return
     */
    Boolean out(String id);


}
