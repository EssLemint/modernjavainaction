package com.study.cp2.check;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class GetMember {
  //동작의 파라미터화 MemberPredicate는 아직 어떤 행동을 할지 모르는 상태이다.
  public static List<Member> filterMembers(List<Member> memberList, MemberPredicate predicate) {
    List<Member> result = new ArrayList<>();
    for (Member member : memberList) {
      if (predicate.test(member)) {
        result.add(member);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    List<Member> memberList = Arrays.asList(new Member(147, "jack", Gender.MEN)
        , new Member(20, "emma", Gender.WOMEN)
        , new Member(3, "linx", Gender.NONE));

    //1. 나이 20이상
    List<Member> checkByAge = filterMembers(memberList, new FilterMembers.CheckMemberByAge());
    //2. 이름에 a가 있는가
    List<Member> checkByName = filterMembers(memberList, new FilterMembers.CheckMemberByName());
    //3. 나이가 20이하이며 여자인 사람
    List<Member> checkAgeAndGender = filterMembers(memberList, new FilterMembers.CheckMemberByAgeAndGender());

    //익명 클래스로 간소화
    List<Member> checkByGender = filterMembers(memberList, new MemberPredicate() {
      @Override
      public boolean test(Member member) {
        return Gender.NONE.equals(member.getGender());
      }
    });

    //익명 로직 람다화
    List<Member> checkByGenderRamda = filterMembers(memberList, member -> Gender.NONE.equals(member.getGender()));

    memberList.sort(Comparator.comparingInt(Member::getAge).thenComparing(Member::getName));
    for (Member member : memberList) {
      log.info("member = {}", member);
    }
  }
}
