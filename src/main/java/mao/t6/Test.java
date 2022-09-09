package mao.t6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Project name(项目名称)：java并发编程_线程池
 * Package(包名): mao.t6
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/9
 * Time(创建时间)： 11:36
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        List<Callable<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < 20; i++)
        {
            int finalI = i;

            tasks.add(new Callable<Integer>()
            {
                @Override
                public Integer call() throws Exception
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
                    return finalI;
                }
            });

        }

        //执行给定的任务，返回一个 Futures 列表，在所有完成时保存它们的状态和结果。
        // Future.isDone对于返回列表的每个元素都是true 。
        // 请注意，已完成的任务可能已经正常终止，也可能通过引发异常终止。
        // 如果在此操作进行时修改了给定的集合，则此方法的结果是不确定的
        List<Future<Integer>> futures = threadPool.invokeAll(tasks);
        for (Future<Integer> future : futures)
        {
            Integer integer = future.get();
            log.debug("结果：" + integer);
        }
    }
}
