package com.study.cp16;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class Asys {

  private final List<Shop> shops = Arrays.asList(
      new Shop("BestPrice"),
      new Shop("LetsSaveBig"),
      new Shop("MyFavoriteShop"),
      new Shop("BuyItAll"),
      new Shop("ShopEasy"));

  public static void main(String[] args) {
    /**
     * CompletableFuture, 리액티브 프로그래밍 API 제공
     *
     * Future : 미래의 어느 시점에 결과를 얻는 모델에 활용할 수 있는 인터페이스
     * 계산이 끝났을때 결과에 접근할 수 있는 참조를 제공한다.
     * Future을 사용하면서 시간이 오래 걸리는 작업을 Callable 객체 내부로 감싼 다음에 ExcutorService에
     * 제출해야한다.
     * */

    /**
     * Future 인터페이스는 isDone, 계산이 끝날때까지 기다리는 메서드, 결과 회수 메서드가 존재한다.
     * 단순한 조회하면 쉽지만 동작 A 이후 A결과 출력 이후 B동작  이후 B동작 결과 출력
     * 위와 같은 행동을 지원하기 위해서 CompletableFuture을 지원한다.
     * Stream과 같이 비슷한 패턴, 파이프라이닝을 활용한다.
     *
     * */

    /**
     * 동기 API는 호출한 메서드가 완료될때까지 기다린 후 반환된 값으로 다음 동작을 수행한다.
     * 블록 호출
     *
     * 비동기 메서드가 즉시 반환되며 끝내지 못한 나머지 작업을 호출자 스레드와 동기적으로 실행될 수 있도록 다른 스레드에 할당한다.
     * 이상황을 비블록 호출이라고 한다.
     *
     * 다른 스레드에 할당된 계산 결과는 콜백 메서드를 호출해서 전달하거나 호출자가 메서드안의 메세지를 추가해서 전달한다.
     * */
    Shop shop = new Shop("BestShop");
    long start = System.nanoTime();
    Future<Double> futurePrice = shop.getPriceAsyns("my favorite product");
    //상점에서 제품가격 요청

    long invocationTime = ((System.nanoTime() - start) / 1_000_000);
    log.info("Invocation returned after " + invocationTime + " msecs");

    try {
      double price = futurePrice.get();
      //가격 정보가 있다면 Future에서 가격 정보를 읽고, 가격 정보가 없으면
      //값을 받을 때까지 블록한다.
      log.info("Price is %.2f%n = {}", price);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
    log.info("Price returned after = {}", retrievalTime);

    List<Shop> shops = Arrays.asList(new Shop("Shop 1")
        , new Shop("Shop 2")
        , new Shop("Shop 3")
        , new Shop("Shop 4")
    );

    log.info("findPrices = {}", shop.findPrices("shop2", shops));
    long duration = ((System.nanoTime() - start) / 1_000_000);
    log.info("Done in = {}", duration);
  }

  //스레드 풀 조절 100이하로
  // 제작한 풀은 데몬 스레드를 포함한다.
  //자바에서 일반 스레드가 실행 중이면 자바 프로그램은 종료되지 않는다.
  // 따라서 이벤트를 한없이 기다리면서 종료되지 않는 일반 스레드가 있으면 문제가 될 수 있다.
  //다만 데몬 스레드는 자바 프로그램이 종료될 때 강제적으로 종료 된다.

  private final Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
    Thread t = new Thread(r);
    t.setDaemon(true);
    return t;
  });
  /**
   * 우리가 만든 풀은 데몬 스레드를 포함한다
   * 병렬화 기법 사용시 참조
   * 스트림 병렬화, CompatableFuture 병렬화
   * I/O가 포함 되지 않은 계산 중심의 동작을 실행할 때는 스트림 인터페이스가 가장 구현하기 간단하며 효율적일 수 있다.
   * 작업이 I/O을 기다리는 작업을 병렬로 실행 할때는 CompatableFuture가 더 많은 유연성을 제공해준다.
   * 스트림의 게으른 특성으로실제로 언제 처리할지 예측하기 어려운 문제도 존재한다.
   * */

}
