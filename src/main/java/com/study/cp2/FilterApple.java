package com.study.cp2;

import static com.study.cp2.Color.GREEN;
import static com.study.cp2.Color.RED;

public class FilterApple {

  public static class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
      return apple.getWeight() > 150;
    }
  }

  public static class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
      return GREEN.equals(apple.getColor());
    }
  }

  public class AppleRedAndHeavyPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
      return RED.equals(apple.getColor()) && apple.getWeight() > 150;
    }
  }
}
