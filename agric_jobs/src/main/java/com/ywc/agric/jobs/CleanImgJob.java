package com.ywc.agric.jobs;

import com.itheima.health.service.SetmealService;
import com.itheima.health.util.QiNiuUtils;
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
    //
    public void  cleanImg(){
        //查出七牛上的所有图片
        List<String> imgIn7niu = QiNiuUtils.listFile();
        //查出数据库所有的图片
        List<String> imgInDb = setmealServices.findImgs();
        //七牛-数据库
        //相同类型的对象才能删除
        imgIn7niu.removeAll(imgInDb);
        //删除七牛上的垃圾图片
        // 类型转换
        String[] strings = imgIn7niu.toArray(new String[]{});
        QiNiuUtils.removeFiles(strings);


    }
}
