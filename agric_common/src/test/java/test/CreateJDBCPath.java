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
    client.create().creatingParentsIfNeeded().forPath("/health98Config/jdbc.driver","com.mysql.cj.jdbc.Driver".getBytes());
    client.create().creatingParentsIfNeeded().forPath("/health98Config/jdbc.url","jdbc:mysql://localhost:3306/health98?serverTimezone=GMT-8".getBytes());
    client.create().creatingParentsIfNeeded().forPath("/health98Config/jdbc.username","root".getBytes());
    client.create().creatingParentsIfNeeded().forPath("/health98Config/jdbc.password","asd123456".getBytes());
        // 关闭
        client.close();
    }
}
