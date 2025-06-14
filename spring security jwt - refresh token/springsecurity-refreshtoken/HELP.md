# Sau khi chạy dự án:

## Login: lấy access + refresh token
* curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{"username":"admin", "password":"123456"}'
* Nhận về:
<pre>
    {
    "accessToken": "eyJhbGciOi...",
    "refreshToken": "eyJhbGciOi..."
    }
</pre>
## Refresh token: xin Access Token mới
* curl -X POST http://localhost:8080/auth/refresh -H "Content-Type: application/json" -d '{"refreshToken":"eyJhbGciOi..."}'

* Kết quả (nếu token hợp lệ):
<pre>
    {
    "accessToken": "eyJhbGciOi...mới"
    }
</pre>
## Logout: thu hồi refresh token
* curl -X POST http://localhost:8080/auth/logout -H "Content-Type: application/json" -d '{"refreshToken":"eyJhbGciOi..."}'

## Bài viết chi tiết:
https://bimatlaptrinh.com/refresh-token-lam-moi-jwt-de-tranh-dang-nhap-lai/