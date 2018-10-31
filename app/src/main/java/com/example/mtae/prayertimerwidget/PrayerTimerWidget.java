package com.example.mtae.prayertimerwidget;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;

import com.example.mtae.prayertimerwidget.response.CalendarByCity;
import com.example.mtae.prayertimerwidget.response.Datum;
import com.example.mtae.prayertimerwidget.response.Timings;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

/**
 * Implementation of App Widget functionality.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class PrayerTimerWidget extends AppWidgetProvider {

    final static String url = "http://api.aladhan.com/v1/calendarByCity?";
    static final LocalDateTime today = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.prayer_timer_widget);

        CalendarByCity calendarByCity = null;

        try {
            calendarByCity = new RunInBackGround().execute(findByCityUrl()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if("OK".equals(calendarByCity.getStatus())) {
            List<Datum> datums = calendarByCity.getData();
            Optional<Datum> datum = datums.stream().filter(isToday()).findFirst();
            Timings timings = datum.get().getTimings();

            views.setTextViewText(R.id.time_sobh, timings.getFajr().replace("(CET)", ""));
            views.setTextViewText(R.id.time_dohr, timings.getDhuhr().replace("(CET)", ""));
            views.setTextViewText(R.id.time_asr, timings.getAsr().replace("(CET)", ""));
            views.setTextViewText(R.id.time_maghreb, timings.getMaghrib().replace("(CET)", ""));
            views.setTextViewText(R.id.time_icha, timings.getIsha().replace("(CET)", ""));
        }
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Predicate<Datum> isToday(){
        return datum -> (today.getDayOfMonth()) == Integer.valueOf(datum.getDate().getGregorian().getDay());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static String findByCityUrl(){
        return String.format("%scity=%s&country=%s&method=%s&month=%s&year=%s",url,"Lyon","France","2",today.getMonth(),today.getYear());
    }
}

