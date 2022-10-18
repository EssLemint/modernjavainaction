package com.study.cp2.check;

public class Member {
  int age;
  String name;
  private Gender gender;

  public Member(int age, String name, Gender gender) {
    this.age = age;
    this.name = name;
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "Member{" +
        "age=" + age +
        ", name='" + name + '\'' +
        ", gender=" + gender +
        '}';
  }
}
