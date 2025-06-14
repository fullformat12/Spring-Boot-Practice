# Sau chạy dự án, dùng curl để test:
## Tạo user mẫu:

<pre>
INSERT INTO user_entity (username, password, role)
VALUES (
  'user',
  '$2b$10$t0FI.scUVq0Kn6Hf7fN9aODkLOSDXdkgnefHl2pDmtHHL/WdAmadu', -- 123456
  'USER'
);
</pre>

## Curl
* curl http://localhost:8080/api/public/hello
* curl -u user:123456 http://localhost:8080/api/user/hello
* curl -u admin:123456 http://localhost:8080/api/admin/hello

## Bài viết chi tiết:
https://bimatlaptrinh.com/spring-security-xac-thuc-nguoi-dung-tu-database-chuan-kien-truc-de-mo-rong/