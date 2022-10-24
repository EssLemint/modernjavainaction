package com.study.cp06;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

@Slf4j
public class Collector {
  public enum CaloricLevel {
    DIET, NORMAL, FAT
  }

  public static void main(String[] args) {
    List<Dish> dishes = Dish.menu;

    Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);

    Optional<Dish> dishCaloriesComparators = dishes.stream().collect(Collectors.maxBy(dishCaloriesComparator));

    int totalCalories = dishes.stream().collect(Collectors.summingInt(Dish::getCalories));
    log.info("totalCalories = {}", totalCalories);

    IntSummaryStatistics dishStatistics = dishes.stream().collect(Collectors.summarizingInt(Dish::getCalories));
    log.info("dishStatistics= {}", dishStatistics);

    String menuName = dishes.stream().map(Dish::getName).collect(Collectors.joining());
    String menuNames = dishes.stream().map(Dish::getName).collect(Collectors.joining(","));
    log.info("menuName = {}", menuName);
    log.info("menuName = {}", menuNames);

    /**
     * groupingBy
     * 스트림 요소를 하느이 값으로 리듀스, 요약
     * 요소 그룹화
     * 요소 분할
     * 연산을 바킷 (BUCKET)에 담는다고 생각하면 편하다
     */

    Map<Type, List<Dish>> dishesType = dishes.stream().collect(Collectors.groupingBy(Dish::getType));
    log.info("dishesType = {}", dishesType);

    Map<CaloricLevel, List<Dish>> dishByCaloricLevel = dishes.stream().collect(Collectors.groupingBy(dish -> {
      if (dish.getCalories() <= 400) return CaloricLevel.DIET;
      else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
      else return CaloricLevel.FAT;
    }));
    log.info("dishByCaloricLevel = {}", dishByCaloricLevel);

    //500 칼로리 이상 FISH Type missing
    Map<Type, List<Dish>> caloricDishesByType = dishes.stream()
        .filter(dish -> dish.getCalories() > 500)
        .collect(Collectors.groupingBy(Dish::getType));
    log.info("caloricDishesByType = {}", caloricDishesByType);

    // + FISH Type
    Map<Type, List<Dish>> caloricDishesByTypeFish = dishes.stream().collect(Collectors.groupingBy(
        Dish::getType, Collectors.filtering(dish -> dish.getCalories() > 500,
            Collectors.toList()))
    );
    log.info("caloricDishesByTypeFish = {}", caloricDishesByTypeFish);

    //counting
    Map<Type, Long> typesCount = dishes.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
    log.info("typesCount = {}", typesCount);


    Map<Type, Set<CaloricLevel>> dishByCaloricLevelByType = dishes.stream().collect(
        Collectors.groupingBy(Dish::getType,
            mapping(dish -> {
              if (dish.getCalories() <= 400) return CaloricLevel.DIET;
              else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
              else return CaloricLevel.FAT;
            }, toSet())));
    log.info("dishByCaloricLevelByType = {}", dishByCaloricLevelByType);

    /**
     * 분할 partitioning function 불리언을 반환
     * 장점은 false, true 두가지 요소를 모두 유지한다는 점이다.
     */
    Map<Boolean, List<Dish>> partitionMenu = dishes.stream().collect(Collectors.partitioningBy(Dish::isVegei));
    log.info("partitionMenu = {}", partitionMenu);

    List<Dish> vegiMenu = dishes.stream().filter(Dish::isVegei).collect(Collectors.toList());
    log.info("vegiMenu = {}", vegiMenu);


  }
}
