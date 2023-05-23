package cn.edu.yzx.order.service.impl;

import cn.edu.yzx.order.entity.Consumer;
import cn.edu.yzx.order.dao.ConsumerDao;
import cn.edu.yzx.order.service.ConsumerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Consumer)表服务实现类
 *
 * @author yzx
 * @since 2022-04-22 00:26:40
 */
@Service("consumerService")
public class ConsumerServiceImpl implements ConsumerService {
    @Resource
    private ConsumerDao consumerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Consumer queryById(String id) {
        return this.consumerDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param consumer 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Consumer> queryByPage(Consumer consumer, PageRequest pageRequest) {
        long total = this.consumerDao.count(consumer);
        return new PageImpl<>(this.consumerDao.queryAllByLimit(consumer, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param consumer 实例对象
     * @return 实例对象
     */
    @Override
    public Consumer insert(Consumer consumer) {
        this.consumerDao.insert(consumer);
        return consumer;
    }

    /**
     * 修改数据
     *
     * @param consumer 实例对象
     * @return 实例对象
     */
    @Override
    public Consumer update(Consumer consumer) {
        this.consumerDao.update(consumer);
        return this.queryById(consumer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.consumerDao.deleteById(id) > 0;
    }

    /**
     * 登录认证
     * @param username 登录的用户名
     * @return “pass” 登录成果
     *          “unPass” 登陆失败
     */
    @Override
    public Boolean getPasswordByUsername(String username, String password) {
        try{
            QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
            wrapper.eq("username", username);
            Consumer consumer = this.consumerDao.selectOne(wrapper);
            if(password.equals(consumer.getPassword())){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            System.out.println("UserServiceImpl---getPasswordByUsername-----:" + e);
            return false;
        }
    }
}
