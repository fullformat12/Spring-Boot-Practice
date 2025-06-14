# Sau chạy dự án, dùng curl để test:
## Tạo data mẫu:

<pre>
INSERT INTO role_entity(name) VALUES ('ADMIN'), ('USER');

INSERT INTO user_entity (username, password)
VALUES (
  'alice',
  '$2b$10$t0FI.scUVq0Kn6Hf7fN9aODkLOSDXdkgnefHl2pDmtHHL/WdAmadu' -- BCrypt
);

-- alice có cả hai quyền
INSERT INTO user_roles(username, role_id) VALUES ('alice', 1), ('alice', 2);
</pre>

## Curl
* curl -u alice:123456 http://localhost:8080/api/secure/admin-only
* curl -u alice:123456 http://localhost:8080/api/secure/common

## Bài viết chi tiết:
https://bimatlaptrinh.com/spring-security-phan-quyen-nang-cao-voi-nhieu-vai-tro-tu-database/