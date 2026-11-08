# 第一阶段：构建前端
FROM node:18-alpine AS builder

WORKDIR /app

# 复制依赖清单
COPY package.json package-lock.json ./

# 安装依赖（利用 Docker 层缓存）
RUN npm ci

# 复制源代码
COPY . .

# 构建前端
RUN npm run build

# 第二阶段：Nginx 托管
FROM nginx:alpine

# 复制 Nginx 配置
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 从构建阶段复制构建产物
COPY --from=builder /app/dist /usr/share/nginx/html

# 暴露端口
EXPOSE 80

# 启动 Nginx
CMD ["nginx", "-g", "daemon off;"]
