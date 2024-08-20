package org.java.utils;

import java.util.List;

public record Student(
    String firstName,
    String lastName,
    String email,
    String gender,
    String mobile,
    String month,
    String year,
    String day,
    List<String> subjects,
    List<String> hobbies,
    String imagePath,
    String address,
    String state,
    String city
) {
}
