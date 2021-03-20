package com.zhitar.in28minutesudemyrestful.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "posts")
@ToString(exclude = "user")
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "users_user_id_fk"))
    @JsonIgnore
    private User user;

}
