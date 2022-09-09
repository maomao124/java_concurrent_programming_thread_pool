package mao.t10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Project name(项目名称)：java并发编程_线程池
 * Package(包名): mao.t10
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/9
 * Time(创建时间)： 12:27
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 20; i++)
        {
            int finalI = i;
            threadPool.submit(new Runnable()
            {
                @Override
                public void run()
                {
                    log.debug(finalI + "开始运行");
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    log.debug(finalI + "结束运行");
                }
            });
        }

        Thread.sleep(2500);
        log.debug("开始关闭线程池");
        //启动有序关闭，其中执行先前提交的任务，但不会接受新任务。如果已经关闭，调用没有额外的效果。
        //此方法不等待先前提交的任务完成执行。使用awaitTermination来做到这一点
        threadPool.shutdown();

    }
}
