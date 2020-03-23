package pub.cwb.workflow.tmp;

import org.junit.Test;

public class MyProcess {

    static Thread thread;

    static int i;

    static boolean running = true;

    @Test
    public void test() throws InterruptedException {
        threadTest();
        Thread.sleep(1000);
        running = false;
    }

    public static void threadTest() {
        thread = new Thread() {
            @Override
            public void run() {
                while (running) {
                    i++;
                    //System.out.println(i);
                }
                //System.out.println("Stop:" + i);
            }
        };
        thread.start();
    }
}

