package test;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

/**
 * Description: No Description
 * User: Eric
 */
public class CreateJDBCPath {
@Test
    public void createJDBCPath() throws Exception {
        //1. 重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,1);
        // 客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",30000,3000,retryPolicy);
        // 启动
        client.start();
// 操作
    client.create().creatingParentsIfNeeded().forPath("/agricmisConfig/jdbc.driver","com.mysql.cj.jdbc.Driver".getBytes());
    client.create().creatingParentsIfNeeded().forPath("/agricmisConfig/jdbc.url","jdbc:mysql://localhost:3306/agricmis?serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true&characterEncoding=UTF-8".getBytes());
    client.create().creatingParentsIfNeeded().forPath("/agricmisConfig/jdbc.username","root".getBytes());
    client.create().creatingParentsIfNeeded().forPath("/agricmisConfig/jdbc.password","asd123456".getBytes());
        // 关闭
        client.close();
    }
}
