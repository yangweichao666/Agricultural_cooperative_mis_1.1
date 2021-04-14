package com.ywc.agric.controller;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.entity.Result;
import com.ywc.agric.pojo.Contract;
import com.ywc.agric.pojo.Setmeal;
import com.ywc.agric.service.ContractService;
import com.ywc.agric.util.QiNiuUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.zookeeper.data.Id;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author YWC
 * @Date 2021/4/14 11:38
 */
@RestController
@RequestMapping("/contract")
public class ContractContrller {
    @Reference
    ContractService contractService;

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<Contract> pageResult = contractService.findPage(queryPageBean);


        //返回分页数据和图片域名链接
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("pageResult", pageResult);
        stringObjectHashMap.put("domain", QiNiuUtils.DOMAIN);
        return new Result(true, MessageConstant.QUERY_CONTRACT_SUCCESS, stringObjectHashMap);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Contract contract, Integer memberId) {
        contract.setMemberId(memberId);
        contractService.add(contract);
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);


    }

    /**
     * 上传文件
     *
     * @param imgFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        //获取原有的图片名称，截取到后缀名
        //获取图片名称
        String originalFilename = imgFile.getOriginalFilename();
        //截取到后缀名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //- 生成唯一文件名，拼接后缀名
        String filename = UUID.randomUUID() + extension;

        try {
            //- 调用七牛上传文件方法
            QiNiuUtils.uploadViaByte(imgFile.getBytes(), filename);
            //- 返回数据给页面
            //{
            //    flag:
            //    message:
            //    data:{
            //        imgName: 图片名,
            //        domain: QiNiuUtils.DOMAIN
            //    }
            //}
            //存储容器·
            Map<String, String> map = new HashMap<String, String>();
            map.put("imgName", filename);
            map.put("domain", QiNiuUtils.DOMAIN);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);

    }

    @PostMapping("/findById")
    public Result findById(Integer id){
       Contract contract =contractService.findById(id);
       return  new Result(true, "根据id查询合同成功",contract);
    }
    @PostMapping("/update")
    public Result update(@RequestBody Contract contract, Integer memberId) {
        contract.setMemberId(memberId);
        contractService.update(contract);
        return new Result(true, "修改成功");


    }
}
