package com.it.pojo; // 包名请根据实际项目调整

public class Person {
    // 基本属性
    private Integer id;
    private String name;
    private Integer age;
    private String sex;

    // 关联属性：一个人对应一张身份证（一对一关系）
    private IdCard card;

    // 无参构造器（MyBatis 反射必需）
    public Person() {
    }

    // 全参构造器（方便测试）
    public Person(Integer id, String name, Integer age, String sex, IdCard card) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.card = card;
    }

    // ========== Getter 和 Setter 方法 ==========
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public IdCard getCard() {
        return card;
    }

    public void setCard(IdCard card) {
        this.card = card;
    }

    // ========== 重写 toString() 方便打印输出 ==========
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", card=" + card + // 会连带打印身份证信息
                '}';
    }
}