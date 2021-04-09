package com.ywc.agric.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.ywc.agric.dao.CheckGroupDao;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.exception.HealthException;
import com.ywc.agric.pojo.CheckGroup;
import com.ywc.agric.service.CheckGroupService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/28 16:03
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    //自动装配
    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 添加检查组
     *
     * @param checkGroup
     * @param checkItemIds
     */
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkItemIds) throws HealthException {

        //添加检查组
        checkGroupDao.addCheckGroup(checkGroup);
       int checkgroup_id=checkGroup.getId();
        //添加关系
        //判断勾选的检查项是否为空
        if (checkItemIds != null) {
            for (Integer checkitem_id : checkItemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkgroup_id, checkitem_id);
            }
        }
    }

    /**
     * 分页查询组
     * @param queryPageBean
     * @return
     */

    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        //mysql拦截器
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //判断是否有条件
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //查询分页 //条件会被分页 //使用page类内含有total 来自PageHelper
        Page<CheckGroup> page= checkGroupDao.findConditon(queryPageBean.getQueryString());
        //将total和页面内容赋值
        PageResult<CheckGroup> result = new PageResult<>(page.getTotal(), page.getResult());
        return result;

    }

    /**
     * 查询检查组内容
     * @param id
     * @return
     */
    @Override
    public CheckGroup findByid(Integer id) {
        CheckGroup checkGroup=checkGroupDao.findByid(id);
        return checkGroup;
    }

    /**
     * 根据组id查询检关系表中
     * @param id
     * @return
     */
    @Override
    public Integer[] findCheckGroupAndCheckItemIds(Integer id) {
        Integer[] checkItemIds=checkGroupDao.findCheckGroupAndCheckItemIds(id);
        return checkItemIds;
    }

    /**
     * 编辑检查组
     * @param checkGroup
     * @param checkItemIds
     * @throws HealthException
     */
    @Override
    //添加事务
    @Transactional
    public void update(CheckGroup checkGroup, Integer[] checkItemIds) throws HealthException {

        //修改检查组
        checkGroupDao.update(checkGroup);
        //删除全部关系
        checkGroupDao.DeleteCheckGroupCheckItems(checkGroup.getId());
        //添加关系
        //判断勾选的检查项是否为空
        if (checkItemIds != null) {
            for (Integer checkitem_id : checkItemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkGroup.getId(), checkitem_id);
            }
        }


    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<CheckGroup> findAll() {
        List<CheckGroup> checkGroups=checkGroupDao.findAll();
        return checkGroups;
    }
}
