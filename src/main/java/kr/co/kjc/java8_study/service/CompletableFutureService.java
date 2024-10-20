package kr.co.kjc.java8_study.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import kr.co.kjc.java8_study.enums.EnumServiceVersion;
import kr.co.kjc.java8_study.global.constants.TextConstants;
import kr.co.kjc.java8_study.global.utils.CommonUtils;
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

  public void callableRun(EnumServiceVersion enumServiceVersion) {
    if(enumServiceVersion == EnumServiceVersion.V1) {
      callableRunV1();
    } else if (enumServiceVersion == EnumServiceVersion.V2) {
      callableRunV2();
    } else if (enumServiceVersion == EnumServiceVersion.V3) {
      callableRunV3();
    } else if (enumServiceVersion == EnumServiceVersion.V4) {
      callableRunV4();
    } else if (enumServiceVersion == EnumServiceVersion.V5) {
      callableRunV5();
    } else if (enumServiceVersion == EnumServiceVersion.V6) {
      callableRunV6();
    }
  }

  public void callableRunV1() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Callable<String> hello = () -> {
      Thread.sleep(2000L);
      return "Hello";
    };

    Future<String> future = executorService.submit(hello);

    System.out.println("\t");
    System.out.println("isDone : " + future.isDone());
    System.out.println("Started!");
    System.out.println("\t");

    try {
      future.get(); // Blocking
                    // get()을 쓰면 Future의 값을 가져온다. 즉 Callable내부에 Thread.sleep()이 포함되어 있으니 2초를 기다린다.
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }

    System.out.println("\t");
    System.out.println("isDone : " + future.isDone());
    System.out.println("End!!");
    System.out.println("\t");

    executorService.shutdown();
  }

  public void callableRunV2() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Callable<String> hello = () -> {
      Thread.sleep(2000L);
      return "Hello";
    };

    Future<String> future = executorService.submit(hello);

    System.out.println("\t");
    System.out.println("isDone : " + future.isDone());
    System.out.println("Started!");
    System.out.println("\t");

    future.cancel(false); // 진행중인 작업을 취소한다. 취소한 후에는 future.get()으로 Future의 값을 가져오지 못한다.

    try {
      future.get(); // Blocking
                    // get()을 쓰면 Future의 값을 가져온다. 즉 Callable내부에 Thread.sleep()이 포함되어 있으니 2초를 기다린다.
                    // 하지만 상단에서 future를 calcel 시켰기 때문에 값을 가져올 수 없다.
    } catch (InterruptedException | ExecutionException | CancellationException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    System.out.println("\t");
    System.out.println("isDone : " + future.isDone());
    System.out.println("End!!");
    System.out.println("\t");

    executorService.shutdown();
  }

  public void callableRunV3() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    Callable<String> hello = () -> {
      Thread.sleep(2000L);
      return "Hello";
    };

    Callable<String> java = () -> {
      Thread.sleep(3000L);
      return "Java";
    };

    Callable<String> keesun = () -> {
      Thread.sleep(1000L);
      return "Keesun";
    };

    try {
      List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, keesun));
      // Callable을 한번에 future로 선언할 수 있다.
      // 다만 Callable로 실행이 끝나면 바로바로 끝는게 아니라 invokeAll로 인입한 List 데이터들이 다 끝날 때 까지 기다린 후에 종료된다.
      futures.stream()
          .forEach(f -> {
            try {
              System.out.println("f : " + f.get());
            } catch (InterruptedException | ExecutionException e) {
              throw new RuntimeException(e);
            }
          });
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void callableRunV4() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    Callable<String> hello = () -> {
      Thread.sleep(2000L);
      return "Hello";
    };

    Callable<String> java = () -> {
      Thread.sleep(3000L);
      return "Java";
    };

    Callable<String> keesun = () -> {
      Thread.sleep(1000L);
      return "Keesun";
    };

    try {
      // invokeAny는 제일 빨리 끝나는 Thread를 호출한다.
      // 다만 Executors을 newSingleThreadExecutor로 선언해 놔서 Array에 들어간 순서대로 Thread가 실행이 되기 때문에
      // Arrays.asList에 인입된 순서중 가장 먼저 선언한 value가 반환된다.

      String s = executorService.invokeAny(Arrays.asList(hello, java, keesun)); // ex1) 제일 먼저 선언된 hello가 고정으로 제일 먼저 반환된다.
//      String s = executorService.invokeAny(Arrays.asList(java , hello, keesun)); // ex2) 제일 먼저 선언된 java가 고정으로 제일 먼저 반환된다.
      System.out.println("s : " + s);
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }
  
  public void callableRunV5() {
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    Callable<String> hello = () -> {
      Thread.sleep(2000L);
      return "Hello";
    };

    Callable<String> java = () -> {
      Thread.sleep(3000L);
      return "Java";
    };

    Callable<String> keesun = () -> {
      Thread.sleep(1000L);
      return "Keesun";
    };

    try {
      // invokeAny는 제일 빨리 끝나는 Thread를 호출한다.
      // 다만 Executors을 newFixedThreadPool를 선언해 놔서 Array에 들어간 순서대로 Thread가 실행이 되서
      // 제일 빨리 끝나는 Thread가 반환된다.

      String s = executorService.invokeAny(Arrays.asList(hello, java, keesun)); // ex1) 순서상으론 hello가 먼저 인입되었지만 keesun 쓰레드가 가장 짧으므로 제일 먼저 반환된다.
      System.out.println("s : " + s);
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  public void callableRunV6() {
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    Callable<String> hello = () -> {
      Thread.sleep(2000L);
      return "Hello";
    };

    Callable<String> java = () -> {
      Thread.sleep(3000L);
      return "Java";
    };

    Callable<String> keesun = () -> {
      Thread.sleep(1000L);
      return "Keesun";
    };

    try {
      // invokeAny는 제일 빨리 끝나는 Thread를 호출한다.
      // 다만 Executors을 newFixedThreadPool를 선언해 놔서 Array에 들어간 순서대로 Thread가 실행이 되서
      // 제일 빨리 끝나는 Thread가 반환된다.

      String s = executorService.invokeAny(Arrays.asList(hello, java, keesun)); // ex1) 순서상으론 hello가 먼저 인입되었지만 keesun 쓰레드가 가장 짧으므로 제일 먼저 반환된다.
      System.out.println("s : " + s);
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  public void completableFutureRun(EnumServiceVersion enumServiceVersion) {
    if (enumServiceVersion == EnumServiceVersion.V1) {
      completableFutureRunV1();
    } else if (enumServiceVersion == EnumServiceVersion.V2) {
      completableFutureRunV2();
    } else if (enumServiceVersion == EnumServiceVersion.V3) {
      completableFutureRunV3();
    } else if (enumServiceVersion == EnumServiceVersion.V4) {
      completableFutureRunV4();
    } else if (enumServiceVersion == EnumServiceVersion.V5) {
      completableFutureRunV5();
    } else if (enumServiceVersion == EnumServiceVersion.V6) {
      completableFutureRunV6();
    } else if (enumServiceVersion == EnumServiceVersion.V7) {
      completableFutureRunV7();
    } else if (enumServiceVersion == EnumServiceVersion.V8) {
      completableFutureRunV8();
    } else if (enumServiceVersion == EnumServiceVersion.V9) {
      completableFutureRunV9();
    } else if (enumServiceVersion == EnumServiceVersion.V10) {
      completableFutureRun10();
    } else if (enumServiceVersion == EnumServiceVersion.V11) {
      completableFutureRun11();
    } else if (enumServiceVersion == EnumServiceVersion.V12) {
      completableFutureRun12();
    }
  }

  public void completableFutureRunV1() {

    CompletableFuture<String> future = new CompletableFuture<>();
    future.complete("Hello");

    try {
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+future.get());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 실행만 필요할 때
   */
  public void completableFutureRunV2() {

    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
    });

    try {
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+future.get());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 콜백으로 return 값이 필요할 때
   * thenApply()
   */
  public void completableFutureRunV3() {

    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    }).thenApply((s) ->{
      return s.toUpperCase();
    });

    try {
      String s = future.get();
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+s);
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 콜백으로 run하기만 하면 될 때
   */
  public void completableFutureRunV4() {

    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    }).thenRun(() -> {
      System.out.println("Hello thenRun : " + Thread.currentThread().getName());
    });

    try {
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+future.get());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * ExecutorService를 매개변수로 이용할 때
   * *** ExecutorService를 이용하지 않으면 기본적으로 commonPool을 이용한다.
   * 콜백으로 run하기만 하면 될 때
   */
  public void completableFutureRunV5() {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
//    ExecutorService executorService = Executors.newFixedThreadPool(5);

    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    }, executorService).thenRunAsync(() -> {
      System.out.println("Hello thenRun : " + Thread.currentThread().getName());
    }, executorService);

    try {
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+future.get());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  public void completableFutureRunV6() {

    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    });

    // thenCompose() : 두 작업이 서로 이어서 실행하도록 조합
    CompletableFuture<String> future = hello.thenCompose(CompletableFutureService::getWorld);

    try {
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+future.get());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  public void completableFutureRunV7() {

    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    });

    CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
      System.out.println("World : " + Thread.currentThread().getName());
      return "World";
    });

    // thenCombine() : 두 작업을 독립적으로 실행하고 둘 다 종료 했을 때 콜백 실행
    CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> h + " " + w);

    try {
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+future.get());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }

  }

  public void completableFutureRunV8() {

    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    });

    CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
      System.out.println("World : " + Thread.currentThread().getName());
      return "World";
    });

    // allOf() : 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행
    // 기본적으로 사용하면 Void 타입으로 future.get() 사용시 null이 출력된다.
    CompletableFuture<Void> future = CompletableFuture.allOf(hello, world);

    try {
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+future.get());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }

  }

  public void completableFutureRunV9() {

    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    });

    CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
      System.out.println("World : " + Thread.currentThread().getName());
      return "World";
    });

    // allOf() : 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행
    // 리턴 타입을 사용하기 위해 추가적인 로직이 필요하다. ex) Array
    List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
    CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

    // 해당 로직은 Blocking이 되지 않는다.
    CompletableFuture<List<String>> future = CompletableFuture.allOf(futuresArray)
        .thenApply((s) -> {
          return futures.stream()
              .map(CompletableFuture::join)
              .collect(Collectors.toList());
        });

    try {
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+future.get());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }

  }

  public void completableFutureRun10() {

    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      try {
        Thread.sleep(5000L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return "Hello";
    });

    CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
      System.out.println("World : " + Thread.currentThread().getName());
      try {
        Thread.sleep(3000L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return "World";
    });

    // anyOf() : 여러 작업 중에 가장 빨리 끝난 하나의 결과에 콜백 실행
    // 현재 상단에 Thread.sleep을 줘서 world가 항상 먼저 실행되어야만 한다.
    CompletableFuture<Void> future = CompletableFuture.anyOf(hello, world)
        .thenAccept((t) -> System.out.println("가장 먼저 끝낸 future : " + t));

    try {
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+future.get());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }

  }

  public void completableFutureRun11() {

    boolean throwError = true; // 에러를 발생시키면 exceptionally 메소드를 실행시킨다.
//    boolean throwError = false; // 에러를 발생시키기지 않으면 exceptionally 메소드를 실행시키지 않고 넘어간다.

    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      if(throwError) {
        throw new IllegalArgumentException();
      }
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    }).exceptionally(ex -> {
      System.out.println("에러 발생 : " + ex);
      return "Error!";
    });

    try {
      // 상단에서 예외가 발생되었으니 get()이 실행되지 않는다.
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+hello.get());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  public void completableFutureRun12() {

//    boolean throwError = true; // 에러를 발생시키든, 정상적인 Case든 둘 다 handle() 메소드를 실행시킨다.
    boolean throwError = false; // 에러를 발생시키든, 정상적인 Case든 둘 다 handle() 메소드를 실행시킨다.

    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      if(throwError) {
        throw new IllegalArgumentException();
      }
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    }).handle((result, ex) -> {
      if(ex != null) {
        System.out.println("에러 발생 : " + ex);
        return "Error!";
      }

      return result;
    });

    try {
      // 만약에 상단에서 예외가 발생되면 get()이 실행되지 않는다.
      // 정상적인 Case에선 get()이 실행된다.
      System.out.println(TextConstants.LOGGING_METHOD_FORMAT+hello.get());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }

  }

  private static Runnable getRunnable(String message) {
    return () -> System.out.println("getRunnable : [" + message + "] : " + Thread.currentThread().getName());
  }

  private static CompletableFuture<String> getWorld(String message) {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println("World : " + Thread.currentThread().getName());
      return message + " World";
    });
  }

  static class MyThread extends Thread {

    @Override
    public void run() {
      System.out.println("Thread : " + Thread.currentThread().getName());
    }
  }


}
