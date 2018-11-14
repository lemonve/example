package thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 用户创建线程有三种方式：1、通过继承Thread类，重写run方法；2、通过实现runable接口；3、通过实现callable接口；4、通过线程池创建
 */
public class createThread {

    //@Test
    public void thread(){
        new MyThread().start();
    }

    //@Test
    public void thread2(){
        MyThread2 t = new MyThread2();
        Thread thread = new Thread(t);
        thread.start();
    }

    //@Test
    public void thread3(){
        MyThread3 t = new MyThread3();
        FutureTask<Integer> result = new FutureTask<>(t);
        new Thread(result).start();
        try{
            int sum = result.get();
            System.out.println("----------");
            System.out.println(sum);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void thread4(){
        MyThread4 t = new MyThread4();
        t.executor();
    }

}

//继承Thread
class MyThread extends  Thread{
    public void run(){
        System.out.println("MyThread 继承Thread类");
    }
}

//实现Runnable接口
class MyThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("mythread 实现Runable接口");
    }
}

//实现callable接口
class MyThread3 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <=10 ; i++) {
            System.out.println(i);
            sum +=i;
        }

        return sum;
    }
}

//线程池
class MyThread4 {
    public void executor() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<String> future = service.submit(new Callable() {
            @Override
            public String call() throws Exception {
                return "通过实现Callable接口";
            }
        });
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
