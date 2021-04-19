package com.ywc.agric.entity;

import java.io.Serializable;

public class Editor implements Serializable {
    private Integer errno;
    private String[] data;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    //wangEditor图片上传返回数据
    public static class ResultUtil {
        //上传成功
        public static Editor success(String[] object) {
            Editor result = new Editor();
            result.setErrno(0);
            result.setData(object);
            return result;
        }

        //上传失败
        public static Editor success() {
            return null;
        }
    }
}
