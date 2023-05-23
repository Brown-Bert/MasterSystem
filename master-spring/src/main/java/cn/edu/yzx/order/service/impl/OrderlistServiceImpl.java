package cn.edu.yzx.order.service.impl;

import cn.edu.yzx.order.dao.OrderlistDao;
import cn.edu.yzx.order.entity.Orderlist;
import cn.edu.yzx.order.service.OrderlistService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Orderlist)表服务实现类
 *
 * @author yzx
 * @since 2022-04-22 12:51:35
 */
@Service("orderlistService")
public class OrderlistServiceImpl implements OrderlistService {
    @Resource
    private OrderlistDao orderlistDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Orderlist queryById(String id) {
        return this.orderlistDao.queryById(id);
    }

    /**
     * 通过fatherid查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public List<Orderlist> queryByFatherId(String id){
        try{
            QueryWrapper<Orderlist> wrapper = new QueryWrapper<>();
            wrapper.eq("father", id);
            List<Orderlist> orderlists = this.orderlistDao.selectList(wrapper);
            return orderlists;
        }catch (Exception e){
            System.out.println("OrderlistServiceImpl---queryByFatherId-----:" + e);
            List<Orderlist> list = new ArrayList<>();
            return list;
        }
    }

    /**
     * 分页查询
     *
     * @param page 页数
     * @param num  每页多少
     * @return 查询结果
     */
    @Override
    public List<Orderlist> queryByPage(Integer page, Integer num) {
        try{
            Page<Orderlist> orderlistPage = new Page<>();
            orderlistPage.setCurrent(Long.parseLong(String.valueOf(page)));
            orderlistPage.setSize(Long.parseLong(String.valueOf(num)));
            QueryWrapper<Orderlist> wrapper = new QueryWrapper<>();
            wrapper.eq("isShow", "1");
            wrapper.eq("father", "0").or().eq("father", "-1");
            IPage<Orderlist> selectPage = this.orderlistDao.selectPage(orderlistPage, wrapper);
            List<Orderlist> lists = selectPage.getRecords();
            return lists;
        }catch (Exception e){
            System.out.println("OrderlistServiceImpl---queryByPage-----:" + e);
            ArrayList<Orderlist> orderlists = new ArrayList<>();
            return orderlists;
        }
    }

    /**
     * 新增数据
     *
     * @param orderlist 实例对象
     * @return 实例对象
     */
    @Override
    public Boolean insert(Orderlist orderlist, Integer length) {
        try{
            if (!orderlist.getId().equals("0")){
                QueryWrapper<Orderlist> wrapper = new QueryWrapper<>();
                wrapper.eq("id", orderlist.getId());
                Orderlist selectOne = this.orderlistDao.selectOne(wrapper);
                selectOne.setFather("-1");
                this.orderlistDao.update(selectOne, wrapper);
                orderlist.setFather(orderlist.getId());
            } else {
                orderlist.setFather("0");
            }
            orderlist.setId(String.valueOf(length+1));
            orderlist.setIsshow("1");
            orderlist.setTag("未付款");
            orderlist.setIsout("未出餐");
            this.orderlistDao.insert(orderlist);
            return true;
        } catch (Exception e){
            System.out.println("OrderlistServiceImpl---insert-----:" + e);
            return false;
        }
    }

    /**
     * 修改数据
     *
     * @param orderlist 实例对象
     * @return 实例对象
     */
    @Override
    public Orderlist update(Orderlist orderlist) {
        this.orderlistDao.update(orderlist);
        return this.queryById(orderlist.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.orderlistDao.deleteById(id) > 0;
    }

    /**
     * 查询数据有多少条
     *
     *
     * @return 查询条数
     */
    @Override
    public int getAllLength() {
        try{
            QueryWrapper<Orderlist> wrapper = new QueryWrapper<>();
            return this.orderlistDao.selectList(wrapper).size();
        }catch (Exception e){
            System.out.println("OrderlistServiceImpl---getAllLength:" + e);
            return -1;
        }
    }

    /**
     * 查询所有可以显示的数据的长度
     *
     * @return 数据长度
     */
    @Override
    public int queryTotalLength(){
        try{
            QueryWrapper<Orderlist> wrapper = new QueryWrapper<>();
            wrapper.eq("isShow", "1").eq("father", "0")
                    .or().eq("father", "-1");
            List<Orderlist> orderlists = this.orderlistDao.selectList(wrapper);
            return orderlists.size();
        }catch (Exception e){
            System.out.println("OrderlistServiceImpl---queryTotalLength-----:" + e);
            return -1;
        }
    }

    /**
     * 修改订单为已支付
     *
     * @return 数据长度
     */
    @Override
    public Boolean payfor(String id){
        try{
            QueryWrapper<Orderlist> wrapper = new QueryWrapper<>();
            wrapper.eq("id", id);
            Orderlist selectOne = this.orderlistDao.selectOne(wrapper);
            selectOne.setTag("已支付");
            this.orderlistDao.update(selectOne, wrapper);
            return true;
        }catch (Exception e){
            System.out.println("OrderlistServiceImpl---payfor-----:" + e);
            return false;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Boolean finish(String id){
        try{
            QueryWrapper<Orderlist> wrapper = new QueryWrapper<>();
            wrapper.eq("id", id);
            Orderlist selectOne = this.orderlistDao.selectOne(wrapper);
            selectOne.setIsshow("0");
            this.orderlistDao.update(selectOne, wrapper);
            return true;
        }catch (Exception e){
            System.out.println("OrderlistServiceImpl---finish-----:" + e);
            return false;
        }
    }

    /**
     * 修改详细数据
     * @param orderlist
     * @return
     */
    public Boolean updateFinish(Orderlist orderlist){
        try{
            QueryWrapper<Orderlist> wrapper = new QueryWrapper<>();
            wrapper.eq("id", orderlist.getId());
            this.orderlistDao.update(orderlist, wrapper);
            return true;
        }catch (Exception e){
            System.out.println("OrderlistServiceImpl---updateFinish-----:" + e);
            return false;
        }
    }

    /**
     * 出餐
     * @param id
     * @return
     */
    public Boolean out(String id){
        try{
            QueryWrapper<Orderlist> wrapper = new QueryWrapper<>();
            wrapper.eq("id", id);
            Orderlist orderlist = this.orderlistDao.selectOne(wrapper);
            orderlist.setIsout("已出餐");
            this.orderlistDao.update(orderlist, wrapper);
            return true;
        }catch (Exception e){
            System.out.println("OrderlistServiceImpl---out-----:" + e);
            return false;
        }

    }
}
