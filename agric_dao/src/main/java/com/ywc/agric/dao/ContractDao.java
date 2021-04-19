package com.ywc.agric.dao;

import com.github.pagehelper.Page;
import com.ywc.agric.pojo.Contract;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/4/14 14:35
 */
public interface ContractDao {
    Page<Contract> findPage(String queryString);

    void add(Contract contract);

    Contract findById(Integer id);

    void update(Contract contract);

    /**
     * 查询所有的合同图片
     * @return
     */
    List<String> findImgs();
}
