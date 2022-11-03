package com.study.flatMap;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FlatMap {
  public static void main(String[] args) {

    List<String> sample = Arrays.asList("apple", "grape");
    log.info("sample = {}", sample);

    List<String> stringList = sample.stream().map(s -> s.split(""))
        .flatMap(Arrays::stream)
        .collect(Collectors.toList());
    log.info("stringList = {}", stringList);

    String[][] strings = new String[][]{
        {"apple", "bat"}
        , {"cat", "base"}
        , {"deep", "back"}
        , {"fast", "beet"}
    };
    List<String[]> listSample = Arrays.asList(strings);
    List<String> collect = listSample.stream().flatMap(strings1 -> Arrays.stream(strings1))
        .collect(Collectors.toList());
    log.info("collect = {}", collect);

    List<String> list = collect.stream().map(s -> s.split(""))
        .flatMap(strings1 -> Arrays.stream(strings1))
        .collect(Collectors.toList());
    log.info("list = {}", list);

  }
}
