package com.study.cp04;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class GetDishes {

  public static void main(String[] args) {

    List<Dish> dishes = Dish.menu;
    List<String> lowCaloricDishesName = dishes.stream()
            .filter(dish -> dish.getCalories() < 400)
            .sorted(Comparator.comparing(Dish::getCalories))
            .map(Dish::getName)
            .collect(Collectors.toList());

    //코어 멀티코어
    List<String> lowCaloricDishesNameParalle = dishes.parallelStream()
        .filter(dish -> dish.getCalories() < 400)
        .sorted(Comparator.comparing(Dish::getCalories))
        .map(Dish::getName)
        .collect(Collectors.toList());
    /**
     * 병렬 처리? 뭐가 다른거지? -> 자세한건 7장
     *
     * 선언형으로 코드 구현이 가능하다. 흔히 if문으로 제어하는것이 아닌
     * 동작 수행으로 처리 가능하다.
     * 요구사항에 대응하기 쉽다
     *  filter -> sorted -> map -> collect으로 선언 파이프라인 형식으로 가독성, 명확성을 가져갈 수 있다.
     *
     *  스트림으로 우리는 가독성, 유연성 그리고 성능까지 챙길 수 있다.
     * */

    /**
     * 컬랙션 VS 스트림
     *
     * 우선 스트림은 '데이터 처리 연상을 위해 소스에서 추출된 연속된 요소'이다.
     * 두가지 중요 특징이 존재한다.
     * 1. 스트림 연산은 파이프라인 형식을 구성할 수 있도록 스트림 자신을 반환한다.
     * 2. 컬렉션은 반복자를 이용하여 명시적으로 반복하지만 스트림은 내부 반복을 지원한다.
     *
     * 스트림은 한 번만 탐색할 수 있지만 컬렉션은 반복해서 사용할 수 있다.
     *
     * 컬렉션은 자료구조로 시간, 공간의 복잡성 그리고 관련된 요소 저장 및 접근 연산에 집중되어 있다.
     * 스트림은 filter, sort, map과 같이 표현 계산식이 메인이다.
     * -> 컬렉션의 주제는 데이터고 스트림은 계산이다.
     *
     * 데이터를 언제 계산하느냐가 컬렉션과 스트림의 가장 큰 차이다.
     * 컬렉션 : 현재 자료구조가 포함하는 모 데이터를 메모지에 저장함. 적극적으로 생성(ex: 요청 오기도 전에 다 채움)  - 요소 추가, 제거는 가능
     * 스트림 : 요청할때만 요소를 계산한다. 스트림은 게으르게 만들어지는 컬렉션이다. 사용자가 요청할 때만 값을 계산
     *
     * 컬렉션은 사용자가 직접 반복자를 붙여줘야 한다. ex: for-each
     * 반면 스트림은 내부 반복이 가능하다
     * */
      List<String> threeHighCaloricDishNames = dishes.stream()
          .filter(dish -> dish.getCalories() > 300)
          .map(Dish::getName)
          .limit(3)
          .collect(Collectors.toList());
      log.info("threeHighCaloricDishNames = {}", threeHighCaloricDishNames);
      /**
       * 연속된 값 dishes 전달 > filter > map > limit 파이프라인 형성
       * 위의 filter - limit까지의 행위는 연결되어있으며 이를 중간 연산이라고 말한다.
       * 마지막 collect은 닫는 연산, 최종 연산이라고 부른다.
       *
       * 중간 연산의 특징은 단말 연산을 스트림 파이프라인에 실행하지 전까지는
       * 아무런 연산을수행하지 않는다는 것이다.
       * 중간연산을 다 합친 후 그 연산을 최종 연산으로 한번에 처리한다.
       *
       * */
    List<String> threeHighCaloricDishNamesLog = dishes.stream()
        .filter(dish -> { log.info("filter = {}", dish.getName());
          return dish.getCalories() > 300;
        })
        .map(dish -> {
          log.info("map = {}", dish.getName());
          return dish.getName();
        })
        .limit(3)
        .collect(Collectors.toList());
    log.info("dish = {}", threeHighCaloricDishNamesLog);
  }
}
