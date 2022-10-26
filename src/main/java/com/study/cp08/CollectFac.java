package com.study.cp08;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class CollectFac {
  public static void main(String[] args) {

    /**
     * 불변 list, set, map
     * */
    /**
     * 요소 추가시 UnsupprtedOperationException 발생
     * */
    List<String> names = List.of("mick", "Oliv", "Tit");
    log.info("names = {}", names);

    /**
     * 중복 요소가 존재시 IllegalArgumentExceptoin 발생
     * 집합은 고유해야한다.
     * */
    Set<String> namesSet = Set.of("mick", "Oliv", "Tit");
    log.info("namesSet = {}", namesSet);

    /**
     *  map 불변
     * */
    Map<String, Integer> ageAndName = Map.of("mick", 30, "Oliv", 10, "Tit", 66);
    log.info("ageAndName = {}", ageAndName);

    Map<String, Integer> agesAndNames = Map.ofEntries(
        Map.entry("mick", 30), Map.entry("Oliv", 10), Map.entry("Tit", 66));
    log.info("agesAndNames = {}", agesAndNames);

    /**
     * removeIf, replaceAll, sort
     * */
    List<String> namesArray = new java.util.ArrayList<>(List.of("mick", "Oliv", "Tit"));
    namesArray.removeIf(s -> s.equals("mick"));
    log.info("namesArray= {}", namesArray);

    namesArray.replaceAll(s -> s.replaceAll("i", "r"));
    log.info("namesArray = {}", namesArray);

    /**
     * 정보를 캐시할때  computeIfAbsent를 활용할 수 있다.
     * */
    Map<String, Integer> namesIfAbsent = new java.util.HashMap<>(Map.ofEntries(
        Map.entry("mick", 30), Map.entry("Oliv", 10), Map.entry("Tit", 66)));
    namesIfAbsent.computeIfAbsent("hello", s -> namesIfAbsent.values().size());
    log.info("namesIfAbsent.values() = {}, namesIfAbsent.keySet= {}", namesIfAbsent.values(), namesIfAbsent.keySet());

    ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
    long parallelismThreshold = 1;
    Optional.ofNullable(map.reduceValues(parallelismThreshold, Long::max));

    log.info("mappingCount = {}, keySet = {}", map.mappingCount() , map.keySet());
  }
}
