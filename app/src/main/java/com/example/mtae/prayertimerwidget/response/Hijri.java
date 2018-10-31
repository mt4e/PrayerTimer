
package com.example.mtae.prayertimerwidget.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "date",
    "format",
    "day",
    "weekday",
    "month",
    "year",
    "designation",
    "holidays"
})
public class Hijri {

    @JsonProperty("date")
    private String date;
    @JsonProperty("format")
    private String format;
    @JsonProperty("day")
    private String day;
    @JsonProperty("weekday")
    private Weekday_ weekday;
    @JsonProperty("month")
    private Month_ month;
    @JsonProperty("year")
    private String year;
    @JsonProperty("designation")
    private Designation_ designation;
    @JsonProperty("holidays")
    private List<String> holidays = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    @JsonProperty("day")
    public String getDay() {
        return day;
    }

    @JsonProperty("day")
    public void setDay(String day) {
        this.day = day;
    }

    @JsonProperty("weekday")
    public Weekday_ getWeekday() {
        return weekday;
    }

    @JsonProperty("weekday")
    public void setWeekday(Weekday_ weekday) {
        this.weekday = weekday;
    }

    @JsonProperty("month")
    public Month_ getMonth() {
        return month;
    }

    @JsonProperty("month")
    public void setMonth(Month_ month) {
        this.month = month;
    }

    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonProperty("designation")
    public Designation_ getDesignation() {
        return designation;
    }

    @JsonProperty("designation")
    public void setDesignation(Designation_ designation) {
        this.designation = designation;
    }

    @JsonProperty("holidays")
    public List<String> getHolidays() {
        return holidays;
    }

    @JsonProperty("holidays")
    public void setHolidays(List<String> holidays) {
        this.holidays = holidays;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
