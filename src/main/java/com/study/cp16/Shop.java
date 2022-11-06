package com.study.cp16;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Shop {

  private final String name;
  private final Random random;

  public Shop(String name) {
    this.name = name;
    random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
  }

  public static void delay() {
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

//  public double getPrice(String product) {
//    return calculatePrice(product);
//  }

//  public double calculatePrice(String product) {
//    delay();
//    Random random = new Random();
//    return random.nextDouble() * product.charAt(0) + product.charAt(1);
//  }

  public String getName() {
    return name;
  }

  /**
   * 최저 금액 점색은 모드 인터넷 상의 가격을 검색해야하는 상황으로 블록 동작이 바람직하지 않다.
   */

  //동기 메서드 -> 비동기로 변환
  public Future<Double> getPriceAsyns(String product) {
    /*
    CompletableFuture<Double> futurePrice = new CompletableFuture<>();
    //계산 결과가 들어갈 CompletableFuture
    new Thread(() -> {
      try {
        double price = calculatePrice(product);
        //새 스레드에서 계산 수행
        futurePrice.complete(price);
        //완료와 future에 값 설정
      } catch (Exception e) {
        futurePrice.completeExceptionally(e); //exception 포함해서
      }
    }).start();
    return futurePrice;
    //계산 결과를 기다리지 않고 값 반환
    */
    return CompletableFuture.supplyAsync(() -> calculatePrice(product));
  }


  public List<String> findPrices(String product, List<Shop> shops) {
    //return
        /* 직렬 동기
        shops.stream().map(shop -> String.format("%s price is %.2f",
        shop.getName(), shop.getPrice(product)))
        .collect(Collectors.toList());
         */
    //병렬 동기 처리
    /*shops.parallelStream().map(shop -> String.format("%s price is %.2f",
            shop.getName(), shop.getPrice(product)))
        .collect(Collectors.toList());

     */
    //비동기 처리
    List<CompletableFuture<String>> priceFutures =
        shops.stream().map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getName() + " price is " + shop.getPrice(product))) //CompletableFuture로 각각의 가격을 비동기적으로 계산
            .collect(Collectors.toList());

    return priceFutures.stream().map(CompletableFuture::join) //모든 비동기 동작이 마무리 되길 기다린다.
        //CompletableFuture에서 기존 작업 요청이 완료 되어야 join이 결과를 반환하면서 다음 상점으로 정보를 요청 하는 상황이다.
        .collect(Collectors.toList());
    //한 스트림으로 처리한것이 아닌 두개의 스트림으로 나눠서 처리했다.
    //스트림은 게으른 특성이 존재하기에 하나의 파이프라인으로 처리했다면 모든 가격 정보 요청 동작이
    //동기적, 순차적으로 이루어 결과가 된다.

  }

  public String getPrice(String product) {
    double price = calculatePrice(product);
    Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];

    return String.format("%s :%.2f:%%s", name, price, code);
  }

  private double calculatePrice(String product) {
    delay();
    return random.nextDouble() * product.charAt(0) + product.charAt(1);
  }


}
