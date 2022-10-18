package com.study.cp2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.study.cp2.Color.GREEN;
import static com.study.cp2.Color.RED;

@Slf4j
public class getApple {

  /**
   * 첫 요구 사항 : 사과에서 초록색만
   * */
  public static List<Apple> filterGreenApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>(); //사과 리스트
    for (Apple apple : inventory) {
      if (GREEN.equals(apple.getColor())) {
        result.add(apple);
      }
    }
    return result;
  }

  /**
   * 다음 요구 사항 색은 빨, 초 + 무게까지 식별하는 상황이다.
   *  코드의 문제점 : 다음 요구 사항에 대해서 유연하게 대응할 수 없다, 추가 요구 사항에 대하여 대처가 어려워짐
   * */
  public static List<Apple> filterColorAndWeight(List<Apple> inventory, Color color, int weight, boolean flag) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if ((flag && apple.getColor().equals(color)) ||
      !flag && apple.getWeight() > weight) {
        result.add(apple);
      }
    }
    return result;
  }

  /**
   * 참 또는 거짓을 반환하는 함수 : predicate
   * 선택 조건을 결정하는 인터페이스를 생성한다. - interface ApplePredicate, class FilterApple
   * > 이게 검사는 filterApple에서 처리하도록 하자
   *
   * 메서드는 객체만 인수로 받는다. test 메서드를 ApplePredicate객체로 감싸서 전달해야한다
   *
   * 컬렉션 탐색 로직, 각 항목에 적용할 동작을 분리할 수 있다는게 동작 파라미터화의 강점이다.
   *
   * 요기서 생기는 궁금점 ApplePredicate의 구현체를 보면 안에 다양한  check 메서드들이 있는 상황이다
   * 그럼 코드는 어떻게 판단하고 행동하는가?
   *
   */
  public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (predicate.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    List<Apple> inventory = Arrays.asList(new Apple(80, GREEN)
        , new Apple(155, GREEN)
        , new Apple(120, RED));

    List<Apple> heavyApples = filterApples(inventory, new FilterApple.AppleHeavyWeightPredicate());
    List<Apple> greenApples = filterApples(inventory, new FilterApple.AppleGreenColorPredicate());

    for (Apple heavyApple : heavyApples) {
      log.info("heavyApple = {}", heavyApple.toString());
    }
    for (Apple greenApple : greenApples) {
      log.info("greenApple = {}", greenApple.toString());
    }

    /**
     * 현재 불필요한 코드들이 많은 상황 > 익명 클래스를 통해 간결화 해보자
     *
     * 따로 분리하지않고 안에서 선언하는 방식
     * */

    List<Apple> beforeRamdaredApples = filterApples(inventory, new ApplePredicate() {
      public boolean test(Apple apple) {
        return RED.equals(apple.getColor());
      }
    });

    //람다식으로 변경해보자
    List<Apple> redApples = filterApples(inventory, apple -> RED.equals(apple.getColor()));

    /**
     * 자바에서 스레드를 이용하면 병렬로 코드 블록을 실행할 수 있다.
     *  Runnable을 사용해서 실행할 코드 블록을 지정할 수 있다.
     * */
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello World");
      }
    });

    Thread tr = new Thread(() -> System.out.println("Hello World"));

  }
}
