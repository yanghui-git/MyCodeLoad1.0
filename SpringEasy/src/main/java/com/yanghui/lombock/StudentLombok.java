package com.yanghui.lombock;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentLombok {

    String name;

    int age;

    String address;

    Long phone;


    public static void main(String[] args) {
        StudentLombok studentLombok = StudentLombok.builder().name("haha").age(100).build();
        System.out.println(studentLombok);
    }
}
