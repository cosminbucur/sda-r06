package com.sda.hibernate.recap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DemoStream {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        List<String> result = numbers.stream()    // stream of integers
            .filter(number -> number % 2 == 0)  // stream of integers filtered
            .map(number -> String.valueOf(number))  // stream of strings
            .collect(Collectors.toList());// new list of strings

        System.out.println(result);
    }
}
