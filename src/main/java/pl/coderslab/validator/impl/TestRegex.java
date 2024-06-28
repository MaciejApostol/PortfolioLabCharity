package pl.coderslab.validator.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestRegex {
    public static boolean findMatch(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return !matcher.find();
    }

    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().getTime());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        dtf.format(now);

//        Street
        Pattern streetPattern = Pattern.compile(
                "\\d{1,4} \\p{Lu}*\\p{Ll}+" +   //np. 3 Maja
                        "|" +
                        "(\\p{Lu}\\p{Ll}+((( )|-)\\p{Lu}\\p{Ll}+)*)" +    //np. Jana Nowaka-Jeziorańskiego
                        " \\d{1,4}(/\\d{1,4})*(\\p{Lu}|\\p{Ll})*( m\\.\\d{1,4})*");   //np. Długa 8/9A m.11

        String street = "Poznańska 1";

        String formattedStreet = street.replaceAll(" {2,}", " ");
        String[] split = formattedStreet.split(" ");
        formattedStreet = Arrays.stream(split)
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
        String pattern = "[^\\p{Lu}\\p{Ll}\\d ]";
        for (String s : formattedStreet.split("")) {
            if (s.matches(pattern)) {
                s = String.format("\\%s", s);
                formattedStreet = formattedStreet.replaceAll(" *" + s + " *", s);
            }
        }
        Matcher streetMatcher = streetPattern.matcher(street);
        System.out.println(street + "\n" + streetMatcher.matches());

//        City
        Pattern cityPattern = Pattern.compile("\\p{Lu}\\p{Ll}+((( )|-)\\p{Lu}\\p{Ll}+)*");

        String city = "Zielona Góra";
        Matcher cityMatcher = cityPattern.matcher(city);
        System.out.println(city + "\n" + cityMatcher.matches());


//        Password
        String password = "H\"-slo123";
        List<String> results = new ArrayList<>();
        if (password.length() < 8) {
            results.add("Too short");
        }
        if (findMatch("\\p{Lu}+", password)) {
            results.add("No capital letters");
        }
        if (findMatch("\\p{Ll}+", password)) {
            results.add("No small letters");
        }
        if (findMatch("[!@#$%^&*()_\\-+={}\\[\\]|:;'`\"<,>\\\\.?/]", password)) {
            results.add("No special characters");
        }
        if (findMatch("\\d", password)) {
            results.add("No digit");
        }
        if (results.size() == 0) {
            results.add("All good");
        }
        results.forEach(System.out::println);
    }
}
