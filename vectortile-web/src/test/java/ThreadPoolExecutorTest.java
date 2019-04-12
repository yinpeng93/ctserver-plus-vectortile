import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/26 0026 13:32
 * @Description:
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            fixedThreadPool.execute(() -> {
//                try {
//                    System.out.println(index);
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }


        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(() -> System.out.println("delay 3 seconds"), 3, TimeUnit.SECONDS);

    }
}
