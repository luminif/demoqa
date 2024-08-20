package org.java.demoqa;

import org.java.core.BaseSeleniumTest;
import org.java.utils.Student;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class DemoqaTest extends BaseSeleniumTest {

    @Test
    public void checkForm() {
        Student student = new Student(
            "Jesse",
            "Pinkman",
            "example@email.com",
            "Male",
            "1234567890",
            "February",
            "2014",
            "12",
            List.of("Maths", "Computer Science"),
            List.of("Sports", "Music"),
            "src/test/resources/123.jpg",
            "123 Main Street",
            "NCR",
            "Delhi"
        );

        new MainPage().fillForm(student);

        ResultPage resultPage = new ResultPage();
        assertTrue(resultPage.getHeader().contains("Thanks for submitting the form"));

        List<String> actual = resultPage.getFormData();
        List<String> expected = expectedResult(student);

        assertEquals(actual.size(), expected.size());
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    private List<String> expectedResult(Student student) {
        return List.of(
            student.firstName() + " " + student.lastName(),
            student.email(),
            student.gender(),
            student.mobile(),
            student.day() + " " + student.month() + "," + student.year(),
            String.join(", ", student.subjects()),
            String.join(", ", student.hobbies()),
            new File(student.imagePath()).getName(),
            student.address(),
            student.state() + " " + student.city()
        );
    }
}