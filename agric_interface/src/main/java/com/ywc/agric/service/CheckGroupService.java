package com.ywc.agric.service;




import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.pojo.CheckGroup;
import com.ywc.agric.exception.HealthException;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/28 15:56
 */

public interface CheckGroupService {
    /**
     * 添加检查组
     * @param checkGroup
     * @param checkItemIds
     */
    void add(CheckGroup checkGroup, Integer[] checkItemIds) throws HealthException;

    /**
     * 分页查询检查组
     * @param queryPageBean
     */

    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    /**
     * 查询检查组项
     * @param id
     * @return
     */
    CheckGroup findByid(Integer id);

    /**
     * 查寻关系表中检查项id
     * @param id
     * @return
     */
    Integer[] findCheckGroupAndCheckItemIds(Integer id);

    void update(CheckGroup checkGroup, Integer[] checkItemIds) throws HealthException;

    /**
     * 查询全部
     * @return
     */
    List<CheckGroup> findAll();
}
