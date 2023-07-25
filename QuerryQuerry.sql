-- TẠO DATABASE
CREATE Database gym_db;
USE gym_db;

CREATE TABLE exercises (
	id INT PRIMARY Key Auto_Increment,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(100) NOT NULL,
    description TEXT
    );

CREATE TABLE gym_members (
    id INT PRIMARY Key Auto_Increment,
    name VARCHAR(100) NOT NULL,
    age INT,
    sex ENUM ('Male', 'Female', 'Other'),
    email VARCHAR(100) UNIQUE
);
USE gym_db;
CREATE TABLE exercise_log(
    id INT PRIMARY KEY Auto_Increment,
    id_member INT NOT NULL,
    id_exercise INT NOT NULL,
    date DATE NOT NULL,
    sets INT,
    reps INT,
    weight FLOAT,
    duration TIME,
    FOREIGN KEY (id_member) REFERENCES gym_members(id),
    FOREIGN KEY (id_exercise) REFERENCES exercises(id)
);
INSERT INTO exercises (name, category, description)
VALUES
  ('Squat', 'Legs', 'Stand with feet shoulder-width apart, lower your hips and bend your knees.'),
  ('Bench Press', 'Chest', 'Lie on a flat bench, lower the barbell to your chest, and push it back up.'),
  ('Pull-up', 'Back', 'Hang from a bar and pull your body up until your chin is over the bar.'),
  ('Deadlift', 'Back', 'Lift a barbell from the ground to a standing position, then lower it back down.'),
  ('Shoulder Press', 'Shoulders', 'Sit on a bench with back support, lift dumbbells overhead and lower them back down.');

USE gym_db;
INSERT INTO gym_members (name, age, sex, email)
VALUES
  ('John Doe', 30, 'Male', 'john.doe@example.com'),
  ('Jane Smith', 25, 'Female', 'jane.smith@example.com'),
  ('Mike Johnson', 28, 'Male', 'mike.johnson@example.com'),
  ('Emily Lee', 32, 'Female', 'emily.lee@example.com');

INSERT INTO exercise_log (id_member, id_exercise , date, sets, reps, weight, duration)
VALUES
  (1, 1, '2023-07-20', 3, 8, 100, '00:30:00'),
  (2, 3, '2023-07-21', 4, 10, NULL, '00:45:00'),
  (3, 2, '2023-07-22', 5, 5, 80, NULL),
  (1, 4, '2023-07-22', 3, 6, 120, NULL),
  (4, 5, '2023-07-23', 3, 12, 15, '00:20:00');

USE gym_db;
SELECT * FROM exercises;
SELECT * FROM gym_members;
SELECT * FROM exercise_log;

-- TỐI ƯU TRUY VẤN

-- VD1: Xem cột trong bảng
USE gym_db;
SELECT * FROM exercise_log;
SELECT id_member, id_exercise, sets, reps FROM exercise_log; -- Tối ưu
-- Thay vì sử dụng SELECT * để lấy tất cả các cột, chúng ta nên chỉ định cụ thể các cột cần thiết để giảm bớt khối lượng dữ liệu truy vấn và tăng hiệu suất.

-- VD2: Loại bỏ các giá trị trùng lặp từ kết quả truy vấn.
USE gym_db;
SELECT DISTINCT sets FROM exercise_log;
-- DISTINCT phải quét toàn bộ bảng để tìm các giá trị duy nhất và loại bỏ các giá trị trùng lặp -> gây tốn nhiều thời gian và tài nguyên của máy chủ.
SELECT sets, /*COUNT(*)*/ FROM exercise_log GROUP BY sets; -- Tối ưu
--GROUP BY không loại bỏ các giá trị trùng lặp, mà thay vào đó nhóm các dòng dữ liệu có cùng giá trị theo các cột được chỉ định.

-- VD3:Truy vấn và chỉ mục
USE gym_db;
CREATE INDEX cate ON exercises(category); -- tối ưu
SELECT * FROM exercises WHERE category = 'back'; 
-- Sử dụng chỉ mục cho cột category giúp tăng tốc độ truy vấn khi tìm kiếm dữ liệu, đặc biệt khi bảng có số lượng dòng lớn. 
-- Chỉ mục giúp MySQL tìm kiếm nhanh chóng các giá trị trong cột và cải thiện hiệu suất truy vấn (tuy nhiên giảm tốc độ thêm sửa xóa).

-- VD4:Truy vấn không cần thiết sử dụng NOT IN
USE gym_db;
SELECT * FROM gym_members WHERE age NOT IN (30,28);
-- Khi sử dụng NOT IN, MySQL phải quét qua toàn bộ bảng để tìm các age không nằm trong danh sách => tốn nhiều thời gian.
SELECT * FROM gym_members WHERE age < 28 OR age >30; -- Tối ưu
-- Sử dụng các so sánh (< và >) để tìm các age nằm ngoài phạm vi (28,30) cho phép MySQL dễ dàng sử dụng chỉ mục (nếu có) để tìm kiếm => cải thiện hiệu suất truy vấn.

-- VD5: Truy vấn theo thời gian
USE gym_db;
SELECT * FROM exercise_log WHERE YEAR(date) = 2023 AND MONTH(date) = 7;
-- Sử dụng YEAR() và MONTH() để tách năm và tháng từ cột date => MySQL phải tính toán trên từng dòng trong bảng => các phép so sánh phức tạp và giảm hiệu suất truy vấn.
SELECT * FROM exercise_log WHERE date >= '2023-07-01' AND date < '2023-08-01'; --tối ưu
-- So sánh trực tiếp với chuỗi ngày '2023-07-01' (đầu tháng) và '2023-08-01' (đầu tháng tiếp theo) => giúp MySQL sử dụng chỉ mục (nếu có) và cải thiện hiệu suất truy vấn.