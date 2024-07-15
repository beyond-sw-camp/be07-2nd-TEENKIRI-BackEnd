CREATE TABLE Users (
    user_id bigint AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    del_yn ENUM('N', 'Y') DEFAULT 'N',
    address VARCHAR(255),
    email VARCHAR(100) NOT NULL UNIQUE,
    user_type ENUM('student', 'teacher', 'admin') NOT NULL,
    is_verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- user_id: 고유한 사용자 식별자.
-- username: 사용자의 아이디.
-- password: 암호화된 비밀번호.
-- del_yn: 회원탈퇴 여부.
-- phone: 사용자의 전화번호.
-- address: 사용자의 주소.
-- email: 사용자의 이메일 주소.
-- user_type: 사용자의 유형(student, teacher, admin).
-- is_verified: 사용자 인증 여부.
-- created_at: 계정 생성 시각.