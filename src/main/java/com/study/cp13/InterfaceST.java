package com.study.cp13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class InterfaceST {
  public static void main(String[] args) {
    /**
     * 인터페이스를 정의하는 두 가지 방법이 존재한다.
     * 1. 인터페이스 내부에 있는 정적 메소드를 사용하는것
     * 2. 디폴트 메서드를 사용하는것
     * */

    List<Integer> numbers = Arrays.asList(3, 4, 5, 6, 7, 1, 2);
    numbers.sort(Comparator.naturalOrder());
    /**
     * sort는 List 인터페이스의 default 메서드이다.
     * Comparator.naturalOrder()은 Comparator을 반환, 새로 추가되는 정적 메서드다.
     * */
    /**
     * 보통 우리는 인터페이스 그리고 해당 인터페이스를 활용할 수 있게 정의된 유틸리티 클래스를 주로 사용한다.
     *  ex) Collections 는 Collection 객체를 황용할 수 있는 유틸리티 클래스이다.
     * */

    /**
     * 클래스 또는 인터페이스로부터 같은 시그니처를 갖는 메소드를 상속 받을 시 주의할 점이 존재한다.
     *
     * 1. 클래스가 우선 순위가 가장 높다. 디폴트 메소드 보다 높은 우선 순위를 갖는다.
     * 2. 1번외의 상황에서는 서브 인터페이스의 우선 순위가 높다. B가 A를 상속 받느면 B가 A보다 우선 순위가 높다.
     * 3. 우선 순위가 정해지지 않았다면 여러 인터페이스를 상속받는 클래스가 명시적으로 디폴트 메서드를 오버라이드하고 호출해야 한다.
     *
     *
     * */


  }
}
