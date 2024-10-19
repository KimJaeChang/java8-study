package kr.co.kjc.java8_study.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kr.co.kjc.java8_study.enums.EnumServiceVersion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompletableFutureService {

  public void run(EnumServiceVersion enumServiceVersion) throws InterruptedException {
    System.out.println("version : " + enumServiceVersion);
    if(enumServiceVersion == EnumServiceVersion.V1) {
      runV1();
    } else if(enumServiceVersion == EnumServiceVersion.V2) {
      runV2();
    } else if (enumServiceVersion == EnumServiceVersion.V3) {
      runV3();
    } else if (enumServiceVersion == EnumServiceVersion.V4) {
      runV4();
    }
  }

  public void runV1() {
    MyThread myThread = new MyThread();
    myThread.run();
  }

  /**
   * Thread가 3초 기다린 후에 실행
   */
  public void runV2(){
    Thread thread = new Thread(() -> {
      try {
        Thread.sleep(3000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }

      System.out.println("Thread : " + Thread.currentThread().getName());
    });

    thread.start();

    System.out.println("Hello : " + Thread.currentThread().getName());
  }

  /**
   * 1. Thread가 3초 기다린 후에 실행
   * 2. 7초뒤에 thread.interrupt()로 종료시키는 설정사용으로 인해 Thread가 3초동안 3번 생성된 후 종료된다. (RuntimeException 발동)
   */
  public void runV3() throws InterruptedException {
    Thread thread = new Thread(() -> {
      while (true) {
        System.out.println("Thread : " + Thread.currentThread().getName());
        try {
          Thread.sleep(3000L);
        } catch (InterruptedException e) {
          System.out.println("exit!");
          throw new RuntimeException(e);
        }
      }
    });

    thread.start();

    System.out.println("Hello : " + Thread.currentThread().getName());
    Thread.sleep(7000L);
    thread.interrupt();
  }

  /**
   * 1. Thread가 3초 기다린 후에 실행
   * 2. thread.isAlive()로 상태 확인 후
   * 3. !thread.isInterruped() : 즉 thread가 종료처리 되지 않았다면
   * 4. thread.interrupt()로 종료
   */
  public void runV4() {
    Thread thread = new Thread(() -> {
      while (true) {
        System.out.println("Thread : " + Thread.currentThread().getName());
        try {
          Thread.sleep(3000L);
        } catch (InterruptedException e) {
          System.out.println("exit!");
          throw new RuntimeException(e);
        }
      }
    });

    thread.start();

    System.out.println("Hello : " + Thread.currentThread().getName());

    try {
      System.out.println("Main thread waiting for worker to finish...");
      // 타임아웃을 설정하여 주기적으로 상태를 확인
      while (thread.isAlive()) {
        thread.join(1000);  // 1초마다 상태 확인
        if (!thread.isInterrupted()) {  // 필요에 따라 조건 확인
          thread.interrupt();  // 중단 요청
          System.out.println("Interrupt called.");
        }
      }
    } catch (InterruptedException e) {
      System.out.println("Main thread was interrupted during join!");
    }

    System.out.println(thread + " is finished");

  }

  public void executorRun(EnumServiceVersion enumServiceVersion) {
    if(enumServiceVersion == EnumServiceVersion.V1) {
      executorRunV1();
    } else if(enumServiceVersion == EnumServiceVersion.V2) {
      executorRunV2();
    } else if (enumServiceVersion == EnumServiceVersion.V3) {
      executorRunV3();
    }
  }

  /**
   * 1. ThreadPool을 2Size 만큼 생성한다.
   * 2. executorService.submit하면 Thread를 실행시킨다.
   * 3. 단 ThreadPool을 2Size 만큼 할당했으니 나머지 3개의 Thread는 Blocking Queue 라는 곳에서 대기하며
   * 먼저 실행이 들어간 Thread가 끝나면 실행됌.
   * 4-1. shutdown() : 속도는 느리지만, 큐에 등록된 모든 작업이 끝날 때까지 종료시키지 않는다. (gracefyl)
   * 4-2. shutdownNow() : 응답은 빠르지만, 강제 종료시키며, 아직 실행되지 않은 작업 목록을 리턴. (Thread Pool에 있는 작업이 끝나면 Blocking Queue는 상관하지 않고 종료되는 듯 하다.)
   */
  public void executorRunV1() {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.submit(getRunnable("Hello"));
    executorService.submit(getRunnable("Keesun"));
    executorService.submit(getRunnable("The"));
    executorService.submit(getRunnable("Java"));
    executorService.submit(getRunnable("Thread"));

    executorService.shutdown();
  }

  /**
   * 3초 딜레이 가진 후 ScheduledExecutorService 실행
   */
  public void executorRunV2() {
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutorService.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);

    // 특정 조건으로 shutdown() 필요
    try {
      scheduledExecutorService.wait(1000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    scheduledExecutorService.shutdown();
  }

  /**
   * 1초 딜레이를 가진 후 끝나면 2초 후 ScheduledExecutorService 실행
   */
  public void executorRunV3() {
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutorService.scheduleWithFixedDelay(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);

    // 특정 조건으로 shutdown() 필요
    // scheduledExecutorService.shutdown();
  }

  private static Runnable getRunnable(String message) {
    return () -> System.out.println("getRunnable : [" + message + "] : " + Thread.currentThread().getName());
  }

  class MyThread extends Thread {

    @Override
    public void run() {
      System.out.println("Thread : " + Thread.currentThread().getName());
    }
  }


}
