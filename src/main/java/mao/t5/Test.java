package mao.t5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Project name(项目名称)：java并发编程_线程池
 * Package(包名): mao.t5
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/9
 * Time(创建时间)： 11:25
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
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 20; i++)
        {
            int finalI = i;
            //提交一个返回值的任务以供执行，并返回一个表示该任务待处理结果的 Future。 Future 的get方法将在成功完成后返回任务的结果。
            //如果您想立即阻止等待任务，可以使用result = exec.submit(aCallable).get();形式的结构。
            //注意： Executors类包含一组方法，可以将一些其他常见的类似闭包的对象（例如java.security.PrivilegedAction ）转换为Callable形式，
            //以便可以提交它们
            Future<Integer> future = threadPool.submit(new Callable<Integer>()
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
            futures.add(future);
        }

        for (Future<Integer> future : futures)
        {
            Integer integer = future.get();
            log.debug("结果：" + integer);
        }
    }
}
