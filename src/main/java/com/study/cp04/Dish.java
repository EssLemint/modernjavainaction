package com.study.cp04;

import java.util.Arrays;
import java.util.List;

public class Dish {
  
  private final String name;
  private final boolean vegei;
  private final int calories;
  private final Type type;
  
  public Dish(String name, boolean vegei, int calories, Type type) {
    this.name = name;
    this.vegei = vegei;
    this.calories = calories;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public boolean isVegei() {
    return vegei;
  }

  public int getCalories() {
    return calories;
  }

  public Type getType() {
    return type;
  }

  @Override
  public String toString() {
    return "Dish{" +
        "name='" + name + '\'' +
        ", vegei=" + vegei +
        ", calories=" + calories +
        ", type=" + type +
        '}';
  }

  public static final List<Dish> menu = Arrays.asList(
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
}