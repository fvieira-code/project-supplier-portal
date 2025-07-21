CREATE TABLE tb_usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL
);

-- admin/admin (criptografar com BCrypt no insert manual)
INSERT INTO tb_usuario (username, password)
VALUES ('admin', '$2a$10$JvI9VToO6NfW5FvlYMGka.mUPdnA39yz6XQyHaFWeCZzpxzm43ETC');
