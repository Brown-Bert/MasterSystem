package cn.edu.yzx.order.dao;

import cn.edu.yzx.order.entity.Consumer;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * (Consumer)表数据库访问层
 *
 * @author yzx
 * @since 2022-04-22 00:26:40
 */
public interface ConsumerDao extends BaseMapper<Consumer>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Consumer queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param consumer 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Consumer> queryAllByLimit(Consumer consumer, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param consumer 查询条件
     * @return 总行数
     */
    long count(Consumer consumer);

    /**
     * 新增数据
     *
     * @param consumer 实例对象
     * @return 影响行数
     */
    int insert(Consumer consumer);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Consumer> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Consumer> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Consumer> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Consumer> entities);

    /**
     * 修改数据
     *
     * @param consumer 实例对象
     * @return 影响行数
     */
    int update(Consumer consumer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


}

