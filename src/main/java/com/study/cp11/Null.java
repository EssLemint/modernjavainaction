package com.study.cp11;

public class Null {
  public static void main(String[] args) {

    /**
     * null 이 발생시키는 문제
     *
     * 에러 유발 우리가 제일 많이 보는 에러
     * NullPointException
     * 코드의 가독성이 떨어진다.
     * null은 아무 의미가 없다
     * null은 무형식이며 정보를 포함하고 있지 않아서 모든 참조 형식에 null을 할당할 수 있다.
     *
     * 위의 null을 방지하기 위해서 Optional 클래스
     * Optional은 선택형값을 캡슐화하는 클래스
     *
     * 값이 있으면 Optional 클래스는 값을 감싸고
     * 값이 없으면 Optional.empty로 Optional을 반환한다.
     *
     * 그럼 우리가 조심해야하는 empty와 Optional.empty의 차이는
     * 무엇일까?
     *
     * null을 참조하는 것은 > NullPointerException으로 이어지지만
     * Optional.empty()는 Optional 객체이다.
     *
     * 우리는 Optional을 사용해서 값이 없는 상황인지 아니면 데이터에 문제가
     * 존재하는지 아니면 코드의 문제인지 구분을 명확히할 수 있다.
     *
     * 하지만 모든 null에 Optional을 적용하는 것은 바람직하지 않다.
     * Optional의 역할은 더 이해하기 쉬운 API를 설계하도록 돕는 역할이다.
     *
     * Optional로 해당 API를 언랩해서 값이 없을 수 있는 상황에
     * 적절하게 대응하도록 강제하는 효과가 있다.
     *
     * Optional
     *   .orElse
     *   .orElseGet
     *   .orElseThrow
     *   .ifPresent
     *   .ifPresentOrElse
     *   .empty
     *   .filter
     *   .flatMap
     *   .get (가장 위험함)
     *   .map
     *   .stream
     *   .ofNullable
     *
     *   Optional을 통해서 우리는 try/catch 로직을 피해갈 수 있다.
     *   Optional은 기본형으로 성능 개선을 기대하기는 어렵다.
     *
     * */
  }
}
