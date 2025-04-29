package com.group.libraryapp.domain.user;

import javax.annotation.processing.Generated;
import javax.persistence.*;


@Entity // 기본생성자가 꼭 있어야함
@Table(name = "users")
public class User {
    @Column(nullable = false, length = 25, name = "name")
    private String name;
    private Integer age;


    @Id // 이 필드를 프라이머리키로 알려준다는 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment를 아렬주는 어노테이션
    private Long id = null;

    protected User() {

    }

    public User(String name, Integer age) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name is null or blank");
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }
    public void updateName(String name){
        this.name = name;
    }
}
