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
        CuratorFramework client = CuratorFrameworkFactory.newClient("82.156.203.206:2181",30000,3000,retryPolicy);
        // 启动
        client.start();
// 操作
    //如果节点以存在 需要先删除节点 不存在则需要注释调
//    client.delete().deletingChildrenIfNeeded().forPath("/agricmisConfig");
    //添加节点
    client.create().creatingParentsIfNeeded().forPath("/agricmisConfig/jdbc.driver","com.mysql.cj.jdbc.Driver".getBytes());
    client.create().creatingParentsIfNeeded().forPath("/agricmisConfig/jdbc.url","jdbc:mysql://localhost:3306/agricmis?serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true&characterEncoding=UTF-8".getBytes());
    client.create().creatingParentsIfNeeded().forPath("/agricmisConfig/jdbc.username","root1".getBytes());
    client.create().creatingParentsIfNeeded().forPath("/agricmisConfig/jdbc.password","asd123456".getBytes());
        // 关闭
        client.close();
    }
}
