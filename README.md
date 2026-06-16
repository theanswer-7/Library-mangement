# JavaEE 课程实验代码

大二下学期 JavaEE 课程学习代码，包含 Spring、SpringMVC、MyBatis 等框架的练习项目。

## 项目结构

```
code/
├── cloudLibrary/    # 云端图书馆管理系统（综合项目）
├── mybatis/         # MyBatis 基础练习
├── spring301/       # Spring IoC / DI 练习
└── untitled2/       # MyBatis 高级映射练习（一对一、一对多、多对多）
```

## 各模块说明

### cloudLibrary — 云端图书馆管理系统

基于 **Spring + SpringMVC + MyBatis** 的 Web 图书管理系统。

- **技术栈**：Spring 5.x、SpringMVC、MyBatis、PageHelper、MySQL、Servlet 3.0+
- **功能**：
  - 图书查询、新增、编辑
  - 图书借阅、续借、归还
  - 图书预约管理
  - 超时未归还管理
  - 黑名单候选人管理
  - 留言板功能
- **配置方式**：纯注解配置（无 XML），Servlet 3.0 容器启动

### mybatis — MyBatis 基础

MyBatis 框架入门练习，包含基本的 CRUD 操作和 Mapper 映射。

### spring301 — Spring 基础

Spring 框架 IoC 容器和依赖注入的练习，包括：
- Bean 的配置与管理
- 依赖注入（构造器注入、Setter 注入）
- Spring 与 MyBatis 整合

### untitled2 — MyBatis 高级映射

MyBatis 高级特性练习，包括：
- 一对一关联（Person - IdCard）
- 一对多关联（User - Orders、Employee - 属性）
- 多对多关联（Student - Course）
- 延迟加载
- 动态 SQL

## 开发环境

- **JDK**：1.8
- **构建工具**：Maven
- **IDE**：IntelliJ IDEA
- **数据库**：MySQL 8.0
- **服务器**：Tomcat 9（cloudLibrary 模块）

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/your-username/JavaEE-Course.git
cd JavaEE-Course
```

### 2. 导入数据库

在 MySQL 中创建对应数据库并导入数据（SQL 文件位于各模块的 `src/main/resources/` 目录下）。

### 3. 配置数据库连接

修改各模块中 `jdbc.properties` 或 `db.properties` 的数据库连接信息。

### 4. 运行

- **cloudLibrary**：使用 Tomcat 部署运行（war 包）
- **mybatis / spring301 / untitled2**：直接运行测试类或 Main 类
