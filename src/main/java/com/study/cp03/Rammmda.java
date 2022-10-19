package com.study.cp03;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.function.ToIntFunction;

@Slf4j
public class Rammmda {
  /**
   * 람다 표현식은 익명 클래스와 비슷하다.
   * 람다 표현식 전체가 함수형 인터페이스의 인스턴스로 취급된다,
   */

  public static void main(String[] args) {
    /**
     * 지금까지 본 람다는 인수를 자신의 바디안에서만 사용했지만
     * 람다는 자유함수 (파라미터 안에 있는 변수가 아닌 외부변수)도 활용할 수 있다.
     * 이 행동을 람다 캡처링이라고 한다.
     * */
    int portNumber = 1234;
    Runnable r = () -> log.info("portNumber = {}", portNumber);
    /**
     * 위 처럼 자유 함수를 사용할때 약간의 제약이 있다.
     * 해당 변수가 실질적/ 명시적으로 final의 상태여야한다는 점이다
     * 즉, 람다 표현식은 한 번만 할당할 수 있는 지역 변수를 캡처할 수 있다.
     * */
    ToIntFunction<String> stringToInt = (String s) -> Integer.parseInt(s);
    //메소드 참조로
    ToIntFunction<String> stringToIntmethod = Integer::parseInt;
  }

}
