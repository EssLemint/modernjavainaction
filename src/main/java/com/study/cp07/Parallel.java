package com.study.cp07;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parallel {
  /**
   * 병렬 처리...
   * n까지의 총합을 구하는 상황에서 해당 숫자가 커지면 해당 연산을 병렬로 처리하는것을 추천한다.
   *
   * 그럼 우리가 생각해야할 부분은
   * 결과, 변수는 어떻게 동기화해야할지,
   * 스레드는 몇개를 사용할지
   * 숫자는 어떻게 생성?
   * 생성된 숫자는 누가 더할지
   * */
  public static void main(String[] args) {
    /**
     * 병렬 스트림은 내부적으로 ForkJoinPool을 사용.
     * ForkJoinPool은 프로세서 수, Runtime.getRunTime().availableProcessors()가 반환하는 값의 스레드를 갖는다.
     *
     * 특별한 이유가 없다면 기본값을 그대로 사용하는 것을 권장한다.
     * */

    /**
     * 우리는 성능을 위해서 병렬 처리를 진행한다. 하지만 우리가 측정을 하지 않은 상태에서는 오르지 추측일 뿐
     * 자바에서는 JMH 라이브러리를 통해서 측정한다.
     *
     * JMH는 이용이 편하며, 어노테이션 기반, 그리고 JVM을 대상으로 하는 다른 언어용 벤치마크 구현이 가능하다.
     *
     * */

  }
}
