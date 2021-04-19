package com.ywc.agric.service;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.pojo.Contract;
import com.ywc.agric.pojo.Setmeal;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/4/14 14:30
 */
public interface ContractService {
    /**
     * 添加 合同信息
     * @param
     */

    void add(Contract contract);
    PageResult<Contract> findPage(QueryPageBean queryPageBean);


    Contract findById(Integer id);

    void update(Contract contract);

    List<String> findImgs();
}
