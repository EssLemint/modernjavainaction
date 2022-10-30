package com.study.cp12;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

@Slf4j
public class DateTime {
  public static void main(String[] args) {
    /**
     *  java util에서 제공해주는 클래스들
     *
     *  LocalDate, LocalTime, LocalDateTime, Instant, Duration, Period
     *
     *  Instant :  기계의 날짜를 계산, 초와 나노초 정보를 제공하기에 사람이 읽을 수 있는
     *  시간 정보는 제공하지 않는다.
     * */

    /**
     * Duration는 초와 나노초로 시간 단위를 표현하기에 between 메서드에 LocalDate 전달이 불가능하다.
     * Period로  처리 가능하다
     * */
    Period tenDays = Period.between(LocalDate.of(2017, 9, 11),
        LocalDate.of(2017, 9, 21));
    log.info("tenDays = {}", tenDays);

    /**
     * LocalDate에 3일을 더해야하는 경우는?
     * 날짜 포매터 사용
     * */
    LocalDate date1 = LocalDate.of(2017, 9, 21);
    LocalDate date2 = date1.plusWeeks(1);
    LocalDate date3 = date2.minusYears(6);
    LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);
    log.info("date1 = {}, date2 = {}, date3 = {}, date4 = {}", date1, date2, date3, date4);

    /**
     * 조금 더 복잡한 다음주 일요일, 돌아오는 평일, 특정 달의 마지막 날...
     *
     * TemporalAdjusters
     * */
    LocalDate date = LocalDate.of(2014, 3, 18);
    LocalDate dateNextOrSame = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    log.info("dateNextOrSame = {}", dateNextOrSame);
    LocalDate dateLastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
    log.info("dateLastDayOfMonth = {}", dateLastDayOfMonth);

    /**
     * DateTimeFormatter
     * */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate formatterDate = LocalDate.of(2014, 3, 16);
    String formattedDate = formatterDate.format(formatter);
    LocalDate formateDate = LocalDate.parse(formattedDate, formatter);
    log.info("formatter = {}\nformatterDate = {}\nformattedDate = {}\nformateDate = {}",
        formatter, formatterDate, formattedDate, formateDate);

    DateTimeFormatter korFormate = new DateTimeFormatterBuilder()
        .appendText(ChronoField.DAY_OF_MONTH)
        .appendLiteral(". ")
        .appendText(ChronoField.MONTH_OF_YEAR)
        .appendLiteral(" ")
        .appendText(ChronoField.YEAR)
        .parseCaseInsensitive()
        .toFormatter(Locale.KOREA);

    log.info("korFormate = {}", korFormate);

  }
}
