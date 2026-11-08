# Product Manager - 商品管理系统

一个简单的前后端分离商品管理系统，用于学习 Vue3、Spring Boot、MySQL 以及 Docker 容器化部署。

## 项目概览

- **前端**：Vue 3 + TypeScript + Vite + Element Plus
- **后端**：Spring Boot 3.x + MyBatis-Plus + MySQL 8
- **架构**：前后端分离，通过 RESTful API 通信

## 项目结构

```
product-manager/
├── frontend/                  # 前端项目
│   ├── src/
│   │   ├── api/              # API 请求封装
│   │   ├── components/       # 可复用组件
│   │   ├── views/            # 页面组件
│   │   ├── router/           # 路由配置
│   │   ├── stores/           # Pinia 状态管理
│   │   └── types/            # TypeScript 类型定义
│   ├── package.json
│   └── vite.config.ts
│
├── backend/                   # 后端项目
│   ├── src/main/java/com/productmanager/
│   │   ├── controller/       # 控制层
│   │   ├── service/          # 服务层
│   │   ├── mapper/           # 数据访问层
│   │   ├── entity/           # 实体类
│   │   ├── dto/              # 数据传输对象
│   │   ├── vo/               # 值对象
│   │   └── config/           # 配置类
│   ├── src/main/resources/   # 资源文件
│   │   ├── application.yml   # 应用配置
│   │   └── mapper/           # MyBatis mapper XML
│   └── pom.xml              # Maven 依赖配置
│
├── database/
│   └── init.sql             # 数据库初始化脚本
│
├── API_DESIGN.md            # API 设计文档
└── README.md                # 项目说明
```

## 开发阶段

### 第一阶段（已完成）
- ✅ 数据库设计与初始化 SQL
- ✅ 项目目录结构规划
- ✅ RESTful API 设计文档

### 第二阶段（进行中）
- Spring Boot 后端开发
  - 实体类、DTO、VO 定义
  - Mapper、Service、Controller 实现
  - 全局异常处理
  - CORS 跨域配置

### 第三阶段
- Vue3 前端开发
  - API 请求封装
  - 商品列表页面
  - 新增/编辑/删除功能
  - 分页和搜索功能

### 第四阶段
- 前后端联调测试

### 第五阶段
- 项目文档整理
- 本地运行说明

## 环境要求

- **Java**: 17+
- **Node.js**: 16+
- **MySQL**: 8.0+
- **Maven**: 3.6+

## 本地运行（后续更新）

### 后端启动

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

后端运行地址：`http://localhost:8080`

### 前端启动

```bash
cd frontend
npm install
npm run dev
```

前端运行地址：`http://localhost:5173`

## 数据库初始化

1. 使用 MySQL 客户端连接数据库
2. 执行 `database/init.sql` 脚本
3. 验证数据库和表已创建

```bash
mysql -u root -proot123 < database/init.sql
```

## API 文档

详见 `API_DESIGN.md`

## 核心功能

- ✅ 商品列表查询（分页）
- ✅ 商品搜索（按名称模糊搜索）
- ✅ 新增商品
- ✅ 编辑商品
- ✅ 删除商品（二次确认）
- ✅ 完整的参数校验
- ✅ 全局异常处理
- ✅ 跨域资源共享（CORS）

## 开发约束

- 不使用 Mock 数据，只调用真实后端接口
- 采用三层架构模式
- 代码清晰规范，方便后续容器化部署
- 每个阶段完成后验证功能正常

## 后续计划

- Docker 容器化部署
- Nginx 反向代理配置
- CI/CD 流水线

## 作者

wangruiyang王瑞阳
