package com.ywc.agric.dao;




import com.github.pagehelper.Page;
import com.ywc.agric.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/28 16:13
 */
public interface CheckGroupDao {
    int addCheckGroup(CheckGroup checkGroup);

    /**
     * 添加检查组与检查项的关系
     * @param checkgroup_id 注意要取别名，类型相同
     * @param checkitem_id
     */
//    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);
void addCheckGroupCheckItem(@Param("checkgroup_id")Integer checkgroup_id, @Param("checkitem_id")Integer checkitem_id);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<CheckGroup> findConditon(String queryString);

    /**
     * 根据组id查询
     * @param id
     * @return
     */
    CheckGroup findByid(Integer id);

    Integer[] findCheckGroupAndCheckItemIds(Integer id);

    void update(CheckGroup checkGroup);

    void DeleteCheckGroupCheckItems(Integer id);

    List<CheckGroup> findAll();
}
