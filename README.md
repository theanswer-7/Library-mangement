# 云端图书馆管理系统

基于 **Spring + SpringMVC + MyBatis** 的 Web 图书管理系统，JavaEE 课程综合项目。

## 技术栈

- Spring 5.x
- SpringMVC
- MyBatis
- PageHelper（分页插件）
- MySQL 8.0
- Servlet 3.0+
- Tomcat 9

## 功能

- 图书查询、新增、编辑
- 图书借阅、续借、归还（含归还确认）
- 图书预约管理
- 超时未归还管理
- 黑名单候选人管理
- 留言板功能
- 用户注册登录、角色权限（ADMIN / USER）
- 全注解配置（无 XML），Servlet 3.0 容器启动

## 项目结构

```
cloudLibrary/
├── src/main/java/com/it/
│   ├── config/          # Spring、SpringMVC、MyBatis、JDBC 配置
│   ├── controller/      # 控制器层
│   ├── dao/             # Mapper 接口
│   ├── interceptor/     # 过滤器 / 拦截器
│   ├── pojo/            # 实体类
│   ├── service/         # 服务接口与实现
│   └── utils/           # 工具类（分页结果、响应结果）
├── src/main/resources/  # MyBatis XML、配置文件
└── src/main/webapp/     # JSP 视图、CSS、JS
```

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/theanswer-7/Library-mangement.git
```

### 2. 配置数据库

在 MySQL 中创建数据库并导入数据，修改 `jdbc.properties` 中的连接信息：

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/你的数据库名
jdbc.username=你的用户名
jdbc.password=你的密码
```

### 3. 运行

使用 IntelliJ IDEA + SmartTomcat / Tomcat 9 部署运行。

## 开发环境

- JDK 1.8
- Maven
- IntelliJ IDEA
