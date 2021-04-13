package com.ywc.agric.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;

import com.ywc.agric.dao.SetmealDao;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.pojo.Setmeal;
import com.ywc.agric.service.SetmealService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/30 18:42
 */
@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Override
    @Transactional
    public void add(Setmeal setmeal, Integer[] integers) {
        //新增套餐
        setmealDao.add(setmeal);
        //返回新增的id
        int setmeal_id=setmeal.getId();
        //新增关系表中的内容
        if(integers!=null){
            for (Integer integer : integers) {
                setmealDao.addSetmealCheeckGroup(setmeal_id,integer);

            }

        }

    }

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //mybatis拦截器
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //判断条件是否存在
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //今行查询
        Page<Setmeal> page= setmealDao.findPage(queryPageBean.getQueryString());

        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 查询所有图片名
     * @return
     */
    @Override
    public List<String> findImgs() {

        return setmealDao.findImgs();
    }

    @Override
    public List<Setmeal> findAll() {
        //今行查询
        List<Setmeal> pages= setmealDao.findAllPage();

        return pages;
    }

    @Override
    public Setmeal findById(Integer id) {
       Setmeal setmeal= setmealDao.findById(id);
        return setmeal;
    }

    /**
     * 编辑套餐
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    @Transactional
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        //修改套餐
        setmealDao.update(setmeal);
        //删除全部关系
        setmealDao.deleteSetmealAnnGroup(setmeal.getId());
        //添加关系
        //判断勾选的检查项是否为空
        if(checkgroupIds.length>0){
            for (Integer checkgroupId:checkgroupIds) {
                setmealDao.addSetmealCheeckGroup(setmeal.getId(), checkgroupId);
            }

        }

    }
}
