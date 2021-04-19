package com.ywc.agric.jobs;

import com.ywc.agric.service.ContractService;
import com.ywc.agric.service.SetmealService;
import com.ywc.agric.service.UserService;
import com.ywc.agric.util.QiNiuUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/31 14:30
 */
//包扫描用
@Component("CleanImgJob")
public class CleanImgJob {
    /**
     * 订阅服务
     */
    @Reference
    SetmealService setmealServices;
    @Reference
    ContractService contractService;
    @Reference
    UserService userService;
    //
    public void  cleanImg(){
        //查出七牛上的所有图片
        List<String> imgIn7niu = QiNiuUtils.listFile();
        //查出数据库所有的图片
        List<String> imgInDbSetmeal = setmealServices.findImgs();
        List<String> imgInDbContract=contractService.findImgs();
        List<String> imgInDbUser=userService.findImgs();
        imgInDbSetmeal.addAll(imgInDbContract);
        imgInDbSetmeal.addAll(imgInDbUser);
//        ArrayUtils.addAll(new List[]{imgInDbSetmeal},imgInDbContract,imgInDbUser);
        //七牛-数据库
        //相同类型的对象才能删除
        imgIn7niu.removeAll(imgInDbSetmeal);
        //删除七牛上的垃圾图片
        // 类型转换
        String[] strings = imgIn7niu.toArray(new String[]{});
        QiNiuUtils.removeFiles(strings);


    }
}
