package com.example.mtae.prayertimerwidget;

import com.example.mtae.prayertimerwidget.response.CalendarByCity;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    final static String url = "http://api.aladhan.com/v1/calendarByCity?city=Lyon&country=France&method=2&month=10&year=2018";

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void parseAndFindADay(){

        // String fileName = "/home/mtae/AndroidStudioProjects/PrayerTimerWidget/app/src/test/java/com/example/mtae/prayertimerwidget/ResponseMonth.txt";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        /* Optional<String> jsonString  = null;
        try {
            jsonString = Files.lines(Paths.get(fileName)).findAny();
        } catch (IOException e) {
            e.printStackTrace();
        }
        when(restTemplateMock.getForObject(url, CalendarByCity.class)).thenReturn(jsonString.get()); */

        CalendarByCity result = restTemplate.getForObject(url, CalendarByCity.class);

        System.out.println(result.getStatus());

    }
}