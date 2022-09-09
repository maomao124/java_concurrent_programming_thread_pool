package mao.t8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Project name(项目名称)：java并发编程_线程池
 * Package(包名): mao.t8
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/9
 * Time(创建时间)： 11:44
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

        //执行给定任务，返回已成功完成的任务的结果（即，不抛出异常），如果有的话。
        //正常或异常返回时，取消未完成的任务。如果在此操作进行时修改了给定的集合，则此方法的结果是不确定的
        Integer integer = threadPool.invokeAny(tasks);
        log.debug("结果：" + integer);
    }
}
