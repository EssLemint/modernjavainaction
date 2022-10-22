package com.study.cp05;

import com.study.cp04.Dish;
import com.study.cp04.Type;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class Streams {

  public static void main(String[] args) {

    List<Dish> specialMenu = Arrays.asList(
        new Dish("pork", false, 800, Type.MEAT),
        new Dish("beef", false, 700, Type.MEAT),
        new Dish("chicken", false, 400, Type.MEAT),
        new Dish("french fries", true, 530, Type.OTHER),
        new Dish("rice", true, 350, Type.OTHER),
        new Dish("season fruit", true, 120, Type.OTHER),
        new Dish("pizza", true, 550, Type.OTHER),
        new Dish("prawns", false, 400, Type.FISH),
        new Dish("salmon", false, 450, Type.FISH)
    );

    List<Dish> filterdMenu =
        specialMenu.stream()
            .filter(dish -> dish.getCalories() < 320)
            .collect(Collectors.toList());

    /**
     * takeWhile : 320미만
     * dropWhile : 320이상
     * */
    List<Dish> filterdMenuTake =
        specialMenu.stream()
            .takeWhile(dish -> dish.getCalories() < 320)
            .collect(Collectors.toList());

    List<Dish> filterdMenuDrop =
        specialMenu.stream()
            .takeWhile(dish -> dish.getCalories() < 320)
            .collect(Collectors.toList());

    //limit
    List<Dish> filterdMenuLimit =
        specialMenu.stream()
            .filter(dish -> dish.getCalories() < 320)
            .limit(2)
            .collect(Collectors.toList());

    //skip
    List<Dish> filterdMenuSkip =
        specialMenu.stream()
            .filter(dish -> dish.getCalories() < 320)
            .limit(2)
            .collect(Collectors.toList());

    /**
     * map, flatMap
     * */
    List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
    List<Integer> wordsLength = words.stream()
        .map(String::length)
        .collect(Collectors.toList());

    specialMenu.stream()
        .map(Dish::getName)
        .map(String::length)
        .collect(Collectors.toList());

    String[] arraysOfWords = {"Hello", "World"};
    Stream<String> stringStream = Arrays.stream(arraysOfWords);

    List<String> uniqueChar = stringStream.map(s -> s.split(""))
        .flatMap(Arrays::stream)
        .distinct()
        .collect(Collectors.toList());

    log.info("uniqueChar = {}", uniqueChar);

    /**
     * allMatch
     * noneMatch
     * */

    /**
     *  Optional 값의 존재, 부재 여부를 표현하는 컨테이너 클래스
     *  return이 null이 될 수도 있는 아이들에게 사용하자
     *  ex : findAny
     * */

    /**
     * 리듀싱 연산 : reduce
     * 모든 스트림 요소를 처리해서 값으로 도출하는 작업
     * */
    List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);
    int sum = 0;
    for (int x : numbers) {
      sum += x;
    }
    //reduce는 두가지 값을 가진다. 초기값 0, 두 요소의 결과값
    sum = numbers.stream().reduce(0, (a, b) -> a+b);
    //초기값이 없는 상태
    Optional<Integer> sum2 = numbers.stream().reduce((a, b) -> a+b);
    //최대값
    Optional<Integer> max = numbers.stream().reduce(Integer::max);
    //최소값
    Optional<Integer> min = numbers.stream().reduce(Integer::min);
    //Optional - OptionalInt, OptionalDouble, Optionallong

    //범위 range 1-100까지의 짝수
    IntStream evenNum = IntStream.rangeClosed(1, 100);
    log.info("evenNum = {}", evenNum.count());
  }
}
