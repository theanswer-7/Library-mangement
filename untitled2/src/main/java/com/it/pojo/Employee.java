package com.it.pojo;

public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private String position;

    // 无参构造（MyBatis 反射需要）
    public Employee() {}

    // 全参构造
    public Employee(Integer id, String name, Integer age, String position) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
    }

    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                '}';
    }
}
