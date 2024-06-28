package pl.coderslab.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TestUUID {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime date = LocalDateTime.of(2023, 11, 1, 20, 40, 44,
                405685988);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(now.format(formatter),formatter);
        date = date.plusDays(2);
        parse = parse.plusDays(2);

        System.out.println(date);
        System.out.println(now);
        System.out.println(date.isAfter(now));

    }

}
