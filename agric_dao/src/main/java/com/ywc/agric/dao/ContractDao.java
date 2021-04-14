package com.ywc.agric.dao;

import com.github.pagehelper.Page;
import com.ywc.agric.pojo.Contract;

/**
 * @Author YWC
 * @Date 2021/4/14 14:35
 */
public interface ContractDao {
    Page<Contract> findPage(String queryString);

    void add(Contract contract);

    Contract findById(Integer id);

    void update(Contract contract);
}
