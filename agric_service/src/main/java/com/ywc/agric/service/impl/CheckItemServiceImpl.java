package com.ywc.agric.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.dao.CheckItemDao;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.exception.HealthException;
import com.ywc.agric.pojo.CheckItem;

import com.ywc.agric.service.CheckItemService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/18 11:29
 * interfaceClass  发布到zookeeper上的接口
 */
//此注解用于发布服务
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    //自动装配
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);

    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //第二种方法mybatis拦截器
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //模糊查询拼接%
        //判断是否存在查询条件
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");

        }
        //进行查询
         Page<CheckItem> page= checkItemDao.findByCondition(queryPageBean.getQueryString());
        //将page的内容封装到PageResult
        PageResult<CheckItem> checkItemPageResult = new PageResult<>(page.getTotal(), page.getResult());
        return checkItemPageResult;
    }

    @Override
    public void deleteById(int id) throws HealthException {
        //检查次检查项是否被检查组使用
        //检查id是否在t_checkgroup_checkitem表中
        int cout=checkItemDao.findCountByCheckItem(id);
        if (cout>0){
            //上抛异常
            throw new HealthException(MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        checkItemDao.deleteById(id);

    }

    /**
     * 查询检查项
     * @param id
     * @return
     * @throws HealthException
     */
    @Override
    public CheckItem findById(int id) throws HealthException {
        return checkItemDao.findById(id);
    }

    /**
     * 编辑
     * @param checkItem
     */
    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }


}
