package com.zhitar.in28minutesudemyrestful.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.zhitar.in28minutesudemyrestful.controller.FilteringController.SOME_BEAN_FILTER_NAME;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties("oneMoreValue")
@JsonFilter(SOME_BEAN_FILTER_NAME)
public class SomeBean {

    private String val;
    private String value;
//    @JsonIgnore
    private String oneMoreValue;
}
