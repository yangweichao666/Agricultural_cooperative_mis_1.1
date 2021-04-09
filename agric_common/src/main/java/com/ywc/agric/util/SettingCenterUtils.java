package com.ywc.agric.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.Properties;

/**
 * Description: 读zookeeper的数据库配置信息
 * User: Eric
 * ApplicationContextAware 如果想获取spring容器，就可以实现这个接口
 */
public class SettingCenterUtils extends PropertyPlaceholderConfigurer implements ApplicationContextAware {

    private AbstractApplicationContext applicationContext;

    private boolean flag = true;

    /**
     * spring中使用的配置信息
     *
     * @param beanFactoryToProcess
     * @param props
     * @throws BeansException
     */
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        // 设置数据库的配置
        // 读取zookeeper
        loadFormZk(props);
        // 订阅数据库配置
        if(flag) {
            // 已经订阅过了，不需要再次订阅
            addWatch();
            flag=false;
        }
        super. processProperties(beanFactoryToProcess, props);
    }
    /**
     * 添加对zookeeper中数据库配置的监听
     * @param
     */

    private void addWatch() {
        // 重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3, 3000);
        // 创建客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", 3000, 3000, retryPolicy);
        // 启动
        client.start();

        TreeCache treeCache = new TreeCache(client, "/agricmisConfig");
        try {
            treeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        treeCache.getListenable().addListener(new TreeCacheListener() {
            //判断是否修改过后刷新
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
               if (event.getType() == TreeCacheEvent.Type.NODE_UPDATED) {
                   String path = event.getData().getPath();
                   System.out.println("==================================修改路径:" + path);
                    if(path.startsWith("/agricmisConfig/jdbc")){
                        // 修改了数据库配置
                        // 刷新spring容器
                        applicationContext.refresh();
                    }
                }
            }
        });
    }

    /**
     * 使用zookeeper客户端连接zookeeper且读取数据库的配置信息
     * /config
     *
     * @param props
     */
    private void loadFormZk(Properties props) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3, 3000);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", 3000, 3000, retryPolicy);
        // 启动客户端
        client.start();

        try {
            String driver = new String(client.getData().forPath("/agricmisConfig/jdbc.driver"));
            String url = new String(client.getData().forPath("/agricmisConfig/jdbc.url"));
            String user = new String(client.getData().forPath("/agricmisConfig/jdbc.username"));
            String password = new String(client.getData().forPath("/agricmisConfig/jdbc.password"));

            // 设置到spring配置信息中, ${jdbc.driver}
            props.setProperty("jdbc.driver", driver);
            props.setProperty("jdbc.url", url);
            props.setProperty("jdbc.username", user);
            props.setProperty("jdbc.password", password);
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 只要这个类交给spring来创建，spring就会调用这个方法
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (AbstractApplicationContext) applicationContext;
    }
}
