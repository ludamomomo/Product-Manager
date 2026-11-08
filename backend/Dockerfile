# 多阶段构建：第一阶段编译
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

# 复制 pom.xml 和源代码
COPY pom.xml .
COPY src ./src

# 编译项目
RUN mvn clean package -DskipTests

# 第二阶段：运行
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# 从构建阶段复制 JAR 文件
COPY --from=builder /app/target/product-manager-backend-1.0.0.jar .

# 暴露端口
EXPOSE 8080

# 启动应用
CMD ["java", "-jar", "product-manager-backend-1.0.0.jar"]
