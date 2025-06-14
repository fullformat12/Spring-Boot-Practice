# Sau khi chạy dự án, dùng curl để test:
## Đăng nhập và lấy token
* curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{"username":"user", "password":"123456"}'

## Gửi token kèm theo header
* curl http://localhost:8080/api/user/hello -H "Authorization: Bearer <token...>"

## Bài viết chi tiết:
https://bimatlaptrinh.com/spring-security-xac-thuc-rest-api-bang-jwt-token-du-an-hoan-chinh/