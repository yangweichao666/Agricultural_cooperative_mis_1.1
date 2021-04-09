package com.ywc.agric.service;



import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.pojo.Setmeal;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/30 18:42
 */
public interface SetmealService {
    /**
     * 添加
     * @param setmeal
     * @param integers
     */
    void add(Setmeal setmeal, Integer[] integers);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 查询数据库上套餐的的所有照片名
     * @return
     */
    List<String> findImgs();

    /**
     * 查询所有套餐
     * @return
     */
    List<Setmeal> findAll();

    /**
     * 根据id查询套餐数据
     * @param id
     * @return
     */
    Setmeal findById(Integer id);
}
