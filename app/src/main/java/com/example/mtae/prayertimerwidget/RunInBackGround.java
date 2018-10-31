package com.example.mtae.prayertimerwidget;

import android.os.AsyncTask;

import com.example.mtae.prayertimerwidget.response.CalendarByCity;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RunInBackGround extends AsyncTask<String, Integer, CalendarByCity> {

    @Override
    protected CalendarByCity doInBackground(String... strings) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        return restTemplate.getForObject(strings[0], CalendarByCity.class);
    }
}
