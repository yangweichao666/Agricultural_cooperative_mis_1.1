package com.ywc.agric.jobs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author YWC
 * @Date 2021/3/31 15:39
 */
public class MainJob {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("classpath:applicationContext-job.xml");
        System.in.read();
    }
}
