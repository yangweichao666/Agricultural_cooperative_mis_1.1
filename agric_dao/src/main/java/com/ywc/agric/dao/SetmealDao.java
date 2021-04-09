package com.ywc.agric.dao;

import com.github.pagehelper.Page;

import com.ywc.agric.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/30 18:46
 */
public interface SetmealDao {

    void add(Setmeal setmeal);

    /**
     * 添加关系
     * @param setmeal_id
     * @param checkgroup_id
     */
    void addSetmealCheeckGroup(@Param("setmeal_id") Integer setmeal_id, @Param("checkgroup_id") Integer checkgroup_id);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<Setmeal> findPage(String queryString);

    /**
     * 查询图片名
     * @return
     */
    List<String> findImgs();

    /**
     * 查询所有套餐
     * @return
     */
    List<Setmeal> findAllPage();

    /**
     * 根据id查询套餐详情
     * @param id
     * @return
     */
    Setmeal findById(Integer id) ;

}
