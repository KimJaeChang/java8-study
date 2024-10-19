package kr.co.kjc.java8_study.service;

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

  class MyThread extends Thread {

    @Override
    public void run() {
      System.out.println("Thread : " + Thread.currentThread().getName());
    }
  }


}
