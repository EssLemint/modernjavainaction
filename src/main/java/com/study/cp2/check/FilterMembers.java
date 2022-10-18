package com.study.cp2.check;

public class FilterMembers {
  //1. 나이 20이상
  public static class CheckMemberByAge implements MemberPredicate {
    @Override
    public boolean test(Member member) {
      return member.getAge() >= 20;
    }
  }

  //2. 이름에 a가 있는가
  public static class CheckMemberByName implements MemberPredicate {
    @Override
    public boolean test(Member member) {
      return member.getName().contains("a");
    }
  }

  //3. 나이가 20이하이며 여자인 사람
  public static class CheckMemberByAgeAndGender implements MemberPredicate {
    @Override
    public boolean test(Member member) {
      return member.getAge() <= 20 && member.getGender().equals(Gender.WOMEN);
    }
  }
}
