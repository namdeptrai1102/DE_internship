# 1.Yêu cầu
## 1.1. Collection(s)
- Mô tả: viết trương trình java sử dụng các cấu trúc dữ liệu HashMap, HashSet, ArrayList
- Tham khảo (Overview) http://cs.lmu.edu/~ray/notes/collections/ (compare) http://www.codejava.net/java-core/collections/java-collections-framework-
summary-table (performance) http://infotechgems.blogspot.com/2011/11/java-collections-performance-time.html
- Điều kiện hoàn thành: Cần nắm được HashMap,HashSet,ArrayList là gì, cách phương thức sử dụng ra sao, so sánh các đặc điểm. nắm được khái niệm
hashcode, equals và lấy ví dụ minh họa sử dụng của hashcode, equal trong Set.
## 1.2. Design Pattern
- Đọc hiểu các Design Pattern cơ bản, lựa chọn ít nhất 3 Design Pattern để implement bằng java và trình bày lại
## 1.3. Serialize
- Tìm hiểu liên quan serialize trong java. (viết code ví dụ minh họa bằng java và giải thích code) (phần này có chút liên quan tới trên cơ sở kiến thức từ 1.2, thực
tập sinh có thể code liên hệ với code cũ)
# 2. Kết quả
## 2.1. Collection(s) [package collections]
### 2.1.1 HashMap
- HashMap là một cấu trúc dữ liệu dựa trên khóa-giá trị (key-value).
- Nó lưu trữ các cặp khóa-giá trị trong một tập hợp không có thứ tự.
- Mỗi khóa phải là duy nhất trong HashMap. HashMap cho phép truy cập, thêm, xóa và cập nhật giá trị dựa trên khóa.
- Nó cung cấp thời gian truy cập (lookup) tốt (trung bình là O(1)), giúp tìm kiếm dữ liệu nhanh chóng.
- Em đã tạo 1 class Hashmap với 1 số phương thức như: thêm, xóa, kiểm tra tồn tại khóa/giá trị, tính kích thước,copy bằng cách duyệt từng cặp khóa-giá trị, in hashcode, so sánh map.
### 2.1.2 HashSet
- HashSet là một cấu trúc dữ liệu không cho phép các phần tử trùng lặp và không có thứ tự.
- Nó được triển khai dựa trên HashMap, trong đó giá trị của các phần tử trong HashSet được sử dụng như khóa trong HashMap.
- HashSet cung cấp các phương thức để thêm, xóa và kiểm tra sự tồn tại của các phần tử. 
- Nó cũng cung cấp việc lặp qua các phần tử, nhưng không đảm bảo thứ tự của chúng.
- Em đã tạo 1 class Hashset với 1 số phương thức như: thêm, xóa, kiểm tra tồn tại giá trị, kiểm tra rỗng, tính kích thước, tìm hashcode, clone, so sánh set.
### 2.1.3 ArrayList
- ArrayList là một mảng động có kích thước thay đổi linh hoạt.
- Nó cung cấp các phương thức để thêm, xóa, truy cập và cập nhật phần tử.
- ArrayList lưu trữ các phần tử theo thứ tự và cho phép truy cập ngẫu nhiên vào các phần tử dựa trên chỉ số.
- Nếu kích thước ArrayList vượt quá giới hạn hiện tại, nó tự động tăng kích thước nội bộ để chứa thêm phần tử.
- Em đã tạo 1 class Arraylist với 1 số phương thức như: thêm, xóa, lấy giá trị theo index, lấy dãy con theo phạm vi index.
### 2.1.4 So sánh:
|  Collection  |     D     |     O      |      S     |      TS    |
|--------------|-----------|------------|------------|------------|
|    HashMap   |    No     |     No     |     No     |     No     |
|    HashSet   |    No     |     No     |     No     |     No     |
|   ArrayList  |    Yes    |     Yes    |     No     |     No     |  
- D: Duplicate elements ís allowed? (nhân bản phần tử)
- O: Elements are ordered? (Có thứ tự)
- S: Elements are sorted? (Sắp xếp thứ tự)
- TS: The collection is thread-safe (cấu trúc dữ liệu hoặc lớp có thể an toàn sử dụng đồng thời bởi nhiều luồng (threads) mà không gây ra các lỗi hoặc sự không nhất quán dữ liệu.)
## 2.2. Design Pattern
### 2.2.1 Factory Method Pattern
### 2.2.2 Adapter Pattern
### 2.2.3 Observer Pattern
## 2.3. Serialize

### 3. Câu hỏi: 
Cơ chế hashcode, thread-safe
