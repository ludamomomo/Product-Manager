# Product Manager - RESTful API 设计

## 基础信息

- Base URL: `http://localhost:8080/api`
- Content-Type: `application/json`
- 响应格式: 统一的 JSON 格式

## 统一响应格式

### 成功响应

```json
{
  "code": 200,
  "message": "success",
  "data": {}
}
```

### 分页响应

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "records": [],
    "total": 0,
    "pages": 0,
    "current": 1,
    "size": 10
  }
}
```

### 错误响应

```json
{
  "code": 400,
  "message": "错误信息描述",
  "data": null
}
```

## API 端点

### 1. 分页查询商品列表

**请求**

```
GET /api/products
```

**参数**

- `current` (query, int, 默认1): 当前页码
- `pageSize` (query, int, 默认10): 每页条数
- `name` (query, string, 可选): 商品名称，支持模糊查询

**响应**

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "records": [
      {
        "id": 1,
        "name": "iPhone 15 Pro",
        "price": 8999.00,
        "stock": 50,
        "createTime": "2024-01-01T10:00:00",
        "updateTime": "2024-01-01T10:00:00"
      }
    ],
    "total": 10,
    "pages": 1,
    "current": 1,
    "size": 10
  }
}
```

### 2. 获取商品详情

**请求**

```
GET /api/products/{id}
```

**参数**

- `id` (path, long): 商品ID

**响应**

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 1,
    "name": "iPhone 15 Pro",
    "price": 8999.00,
    "stock": 50,
    "createTime": "2024-01-01T10:00:00",
    "updateTime": "2024-01-01T10:00:00"
  }
}
```

### 3. 新增商品

**请求**

```
POST /api/products
```

**请求体**

```json
{
  "name": "新商品",
  "price": 999.00,
  "stock": 100
}
```

**参数校验**

- `name`: 必填，非空字符串
- `price`: 必填，不能小于0
- `stock`: 必填，不能小于0

**响应**

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 11,
    "name": "新商品",
    "price": 999.00,
    "stock": 100,
    "createTime": "2024-01-02T10:00:00",
    "updateTime": "2024-01-02T10:00:00"
  }
}
```

### 4. 修改商品

**请求**

```
PUT /api/products/{id}
```

**参数**

- `id` (path, long): 商品ID

**请求体**

```json
{
  "name": "修改后的商品名",
  "price": 1099.00,
  "stock": 80
}
```

**参数校验**

- `name`: 可选，如果提供则非空
- `price`: 可选，如果提供则不能小于0
- `stock`: 可选，如果提供则不能小于0

**响应**

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 11,
    "name": "修改后的商品名",
    "price": 1099.00,
    "stock": 80,
    "createTime": "2024-01-02T10:00:00",
    "updateTime": "2024-01-02T10:30:00"
  }
}
```

### 5. 删除商品

**请求**

```
DELETE /api/products/{id}
```

**参数**

- `id` (path, long): 商品ID

**响应**

```json
{
  "code": 200,
  "message": "success",
  "data": null
}
```

## HTTP 状态码

| 状态码 | 含义 |
|-------|------|
| 200   | 请求成功 |
| 400   | 参数错误或业务异常 |
| 404   | 资源不存在 |
| 500   | 服务器错误 |

## 错误码定义

| 错误码 | 描述 |
|-------|------|
| 200   | 成功 |
| 400   | 请求参数错误 |
| 404   | 资源不存在 |
| 500   | 系统内部错误 |
