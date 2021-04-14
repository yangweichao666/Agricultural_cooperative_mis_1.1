package com.ywc.agric.service.impl;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.ywc.agric.dao.ContractDao;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.pojo.Contract;
import com.ywc.agric.pojo.Member;
import com.ywc.agric.service.ContractService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.dsig.XMLValidateContext;

/**
 * @Author YWC
 * @Date 2021/4/14 14:33
 */
@Service(interfaceClass = ContractService.class)
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractDao contractDao;

    @Override
    public void add(Contract contract) {
        contractDao.add(contract);
    }

    @Override
    public PageResult<Contract> findPage(QueryPageBean queryPageBean) {
        //拦截器使用静态方法
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        Page<Contract> page =contractDao.findPage(queryPageBean.getQueryString());

        PageResult<Contract> memberPageResult = new PageResult<Contract>(page.getTotal(),page.getResult());
        return memberPageResult;
    }

    @Override
    public Contract findById(Integer id) {
        return contractDao.findById(id);
    }

    @Override
    public void update(Contract contract) {
        contractDao.update(contract);
    }
}
