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
Em đã đọc hết 23 design pattern tại trang https://www.javatpoint.com/design-patterns-in-java và triển khai 3 ví dụ phía dưới
### 2.2.1 Factory Method Pattern (tham khảo diagram FactoryMethodPattern.png trong package FactoryMethodPattern)
- Định nghĩa:
  - Factory Method Pattern là một mẫu thiết kế (design pattern) thuộc nhóm mẫu Creational Pattern trong lập trình hướng đối tượng.
  - Mục đích chính của mẫu Factory Method là cung cấp một giao diện chung để tạo đối tượng, nhưng cho phép các lớp con xác định loại cụ thể của đối tượng sẽ được tạo.
- Thực hiện:
  - Em tạo lớp trừu tượng Exercise với thuộc tính intensity cùng với 2 phương thức getIntensity() và restSecond() để tính thời gian nghỉ tùy thuộc loại hình tập luyện và số reps.
  - 3 lớp con kế thừa lớp Exercise và ghi đè phương thức getIntensity ứng với mỗi loại hình luyện tập.
  - Lớp GetExerciseFactory dùng để khởi tạo đúng loại hình luyện tập ứng với thông điệp được nhập vào.
  - Lớp GenerateRestTime sẽ trả về thời gian nghỉ phù hợp với dữ liệu được nhập vào.
### 2.2.2 Adapter Pattern (tham khảo diagram AdapterPattern.png trong package AdapterPattern)
- Định nghĩa:
  - Adapter Pattern là một mẫu thiết kế (design pattern) thuộc nhóm Structural Patterns trong lập trình hướng đối tượng.
  - Nó cho phép các đối tượng hoạt động cùng nhau mặc dù có giao diện không tương thích hoặc không tương thích với nhau.
  - Mục đích chính của mẫu Adapter là chuyển đổi giao diện của một đối tượng thành một giao diện khác mà client mong muốn sử dụng. Điều này giúp các đối tượng làm việc với nhau dễ dàng, ngay cả khi chúng có giao diện không tương thích ban đầu.
- Thực hiện:
  - Ban đầu em tạo 1 interface GymCard với 2 phương thức giveGymDetails() và getGymCard(), đây là giao diện mục tiêu giúp người dùng chỉ cần gửi thông tin và nhận xác thực mà không cần biết hệ thống được vận hành thế nào.
  - Tiếp theo em tạo lớp GymDetails gồm các thuộc tính dữ liệu thẻ tập gym của người dùng và các phương thức getter và setter.
  - Sau đó em tạo class GymCustomer kế thừa GymDetails và thực thi GymCard, đây chính là Adapter tham chiếu đến đối tượng GymDetails và triển khai giao diện mục tiêu là GumCard.
  - Lớp ClienDemo đóng vai trò là người dùng thực hiện việc nhập thông tin và nhận xác thực.
### 2.2.3 Observer Pattern (tham khảo diagram ObserverPattern.png trong package ObserverPattern)
- Định nghĩa:
  - Observer Pattern (a.k.a Publisher-Subscriber Pattern or Event-Subscriber Pattern) là một mẫu thiết kế hướng đối tượng trong lập trình, nằm trong nhóm mẫu thiết kế hành vi (behavioral design patterns).
  - Nó cho phép các đối tượng (observers) đăng ký và theo dõi sự thay đổi của một đối tượng khác (subject) và tự động nhận thông báo khi sự thay đổi xảy ra.
 - Thực hiện:
   - Ban đầu em tạo giao diện Activity với 4 phương thức attach(), detach() (thêm, xóa người theo dõi), notification() và getState().
   - Sau đó em tạo 1 giao diện Member với phương thức update() được truyền vào đối tượng Activity.
   - Tiếp theo em thực thi Activity bằng giao diện ConcreteActivity gồm List<Member> và thuộc tính state, lớp này thêm phương thức setState() và ghi đè các phương thức của giao diện. Phương thức notification() duyệt memberList và update() từng phần tử.
   - Lớp ConcreteMember thực thi giao diện Member ghi đè update() in ra trạng thái của member.
   - Cuối cùng, lớp Demo chạy thử hệ thống. Nó thêm, xóa các thành viên và khi thay đổi trạng thái thì mọi thành viên đang có trong list đều được cập nhật tương ứng.
## 2.3. Serialize [package File]
### 2.3.1 Serialized
- Đầu tiên em tạo class Profile implements Serializable với 1 số thuộc tính và phương thức khởi tạo.
- Tại class Serialized, em khởi tạo 1 Profile, serialized rồi in dữ liệu trước và sau khi deserialized.
- Giải thích kết quả:
  - name và sex được lưu thông qua serialization và khi được deserialized sẽ giữ nguyên giá trị khởi tạo (kể cả khi sex đã bị thay đổi sau bước serialization).
  - age là biến static nên không được lưu thông qua serialization -> khi ta thay đổi age = 22 sau bước serialization thì output cũng được cập nhật.
  - key là biến transient nên không được serialization -> kết quả = 0.
  - biến valid cũng là transient nhưng vì có thêm từ khóa final -> kết quả luôn = 1
### 2.3.2 Class FileIndirectWritingText
- Ngoài việc viết thông điệp vào file IndirectBText.txt ở tuần 1, em đã viết thêm Serialization và Deserialization cho nó.
