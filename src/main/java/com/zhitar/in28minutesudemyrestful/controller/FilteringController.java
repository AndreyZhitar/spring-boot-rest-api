package com.zhitar.in28minutesudemyrestful.controller;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.zhitar.in28minutesudemyrestful.domain.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("filtering")
public class FilteringController {

    public static final String SOME_BEAN_FILTER_NAME = "SomeBeanFilter";

    @GetMapping
//    field1, field2
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        return applyFilterSomeBeanFilter(someBean, "val", "value");
    }

    @GetMapping("list")
//    field2, field3
    public MappingJacksonValue retrieveListSomeBean() {
        List<SomeBean> someBeans = Arrays.asList(
                new SomeBean("1", "2", "3"),
                new SomeBean("one", "two", "three"),
                new SomeBean("ten", "twenty", "thirty")
        );
        return applyFilterSomeBeanFilter(someBeans, "oneMoreValue", "value");
    }

    private MappingJacksonValue applyFilterSomeBeanFilter(Object bean, String... fields) {
        MappingJacksonValue mapping = new MappingJacksonValue(bean);
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter(
                SOME_BEAN_FILTER_NAME,
                SimpleBeanPropertyFilter.filterOutAllExcept(fields));
        mapping.setFilters(filterProvider);
        return mapping;
    }
}
