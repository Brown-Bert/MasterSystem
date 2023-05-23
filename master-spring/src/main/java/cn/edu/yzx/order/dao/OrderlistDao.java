package cn.edu.yzx.order.dao;

import cn.edu.yzx.order.entity.Orderlist;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * (Orderlist)表数据库访问层
 *
 * @author yzx
 * @since 2022-04-22 16:45:01
 */
public interface OrderlistDao extends BaseMapper<Orderlist>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Orderlist queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param orderlist 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Orderlist> queryAllByLimit(Orderlist orderlist, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param orderlist 查询条件
     * @return 总行数
     */
    long count(Orderlist orderlist);

    /**
     * 新增数据
     *
     * @param orderlist 实例对象
     * @return 影响行数
     */
    int insert(Orderlist orderlist);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Orderlist> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Orderlist> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Orderlist> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Orderlist> entities);

    /**
     * 修改数据
     *
     * @param orderlist 实例对象
     * @return 影响行数
     */
    int update(Orderlist orderlist);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

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

}

