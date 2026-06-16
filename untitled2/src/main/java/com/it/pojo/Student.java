package com.it.pojo;

import java.util.List;

public class Student {
    private Integer id;
    private String name;
    private Integer age;

    // 必须添加：课程列表（多对多关系）
    private List<Course> courseList;

    // 无参构造
    public Student() {
    }

    // 有参构造（可选）
    public Student(Integer id, String name, Integer age, List<Course> courseList) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.courseList = courseList;
    }

    // Getter 和 Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // 关键！必须有这个 getter 方法，测试类才能调用 student.getCourseList()
    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    // 可选：toString 方法，方便打印
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}