package mao.t12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * Project name(项目名称)：java并发编程_线程池
 * Package(包名): mao.t12
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/9
 * Time(创建时间)： 12:47
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
        //尝试停止所有正在执行的任务，停止等待任务的处理，并返回等待执行的任务列表。
        //此方法不等待主动执行的任务终止。使用awaitTermination来做到这一点。
        //除了尽最大努力停止处理正在执行的任务之外，没有任何保证。
        //例如，典型的实现将通过Thread.interrupt取消，因此任何未能响应中断的任务可能永远不会终止。
        List<Runnable> runnableList = threadPool.shutdownNow();

        for (int i = 20; i < 23; i++)
        {
            try
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
            catch (RejectedExecutionException e)
            {
                e.printStackTrace();
            }
        }

        Thread.sleep(500);

        for (Runnable runnable : runnableList)
        {
            log.warn("未执行完成的任务：" + runnable);
        }
    }
}
