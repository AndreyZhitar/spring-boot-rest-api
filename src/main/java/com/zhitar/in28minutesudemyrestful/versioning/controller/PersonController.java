package com.zhitar.in28minutesudemyrestful.versioning.controller;

import com.zhitar.in28minutesudemyrestful.versioning.v1.Person;
import com.zhitar.in28minutesudemyrestful.versioning.v2.Name;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class PersonController {

    @GetMapping("v1/person")
    public Person personV1() {
        return new Person("Bob Charlie");
    }

    @GetMapping("v2/person")
    public com.zhitar.in28minutesudemyrestful.versioning.v2.Person personV2() {
        return new com.zhitar.in28minutesudemyrestful.versioning.v2.Person(new Name("Bob", "Charlie"));
    }

    @GetMapping(value = "person/param", params = "version=1")
    public Person paramV1() {
        return new Person("Param Person V1");
    }

    @GetMapping(value = "person/param", params = "version=2")
    public com.zhitar.in28minutesudemyrestful.versioning.v2.Person paramV2() {
        return new com.zhitar.in28minutesudemyrestful.versioning.v2.Person(new Name("Param Person", "V1"));
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
    public Person headerV1() {
        return new Person("Header Person V1");
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
    public com.zhitar.in28minutesudemyrestful.versioning.v2.Person headerV2() {
        return new com.zhitar.in28minutesudemyrestful.versioning.v2.Person(new Name("Header Person", "V1"));
    }

    @GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
    public Person producesV1() {
        return new Person("Produces Person V1");
    }

    @GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
    public com.zhitar.in28minutesudemyrestful.versioning.v2.Person producesV2() {
        return new com.zhitar.in28minutesudemyrestful.versioning.v2.Person(new Name("Produces Person", "V1"));
    }

}
