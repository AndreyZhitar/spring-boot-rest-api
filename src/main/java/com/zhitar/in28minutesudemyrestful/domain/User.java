package com.zhitar.in28minutesudemyrestful.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonRootName("user")
@ApiModel(description = "Some details about user")
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Size(min = 2, message = "Name should be at least 2 characters")
    @ApiModelProperty(notes = "Name should be at least 2 characters")
    private String name;
    @Past
    @NotNull
    @ApiModelProperty(notes = "Birth date should be in the past")
    private LocalDateTime birthdate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Post> postList = new ArrayList<>();

    public User(Long id, String name, LocalDateTime birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }
}
