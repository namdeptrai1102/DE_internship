# 1. Yêu cầu
- Yêu cầu (1): Tự cài đặt một cơ sở dữ liệu trên máy tính (cụ thể là mysql). trình bày chi tiết về các thành phần liên quan
- Yêu cầu (2): Tự lấy ví dụ về 5 câu query không tốt và cách tối ưu nó.
- Yêu cầu (3): Tìm hiểu về các loại db và trình bày lại (ít nhất 3 db thuộc loại sql, 3 db thuộc loại no sql). 
Tham khảo: https://www.ml4devs.com/articles/datastore-choices-sql-vs-nosql-database/
# 2. Kết quả
## 2.1 My SQL
Em đã cài đặt MySQL, MySQL workbench, extention VSCode lên localhost, thử một số thao tác cơ bản.
### Kiến trúc MySQL:
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/a7744086-a3ad-47d2-ab9c-22525c92d987)
- MySQL tuân theo mô hình khách chủ, máy chủ MySQL quản lý tất cả các lệnh hoặc hướng dẫn cơ sở dữ liệu
- Người dùng gửi lệnh đến máy chủ MySQL thông qua máy khách MySQL trên máy tính.
- MySQL thường nằm trên một máy duy nhất, nhưng nó sẽ gửi CSDL đến một số vị trí -> người dùng có thể truy cập thông qua các giao diện máy khách MySQL, các giao diện này chuyển các câu lệnh SQL tới máy chủ và hiển thị kết quả.
###  Các thành phần:
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/141b706c-a1ce-4a74-82ed-7c7745a6959d)
- (1) Client End: Đây là thành phần mà người dùng tương tác với. Bạn gửi nhiều lệnh MySQL tới máy chủ thông qua terminal hoặc GUI. Một số dịch vụ quan trọng do Client end cung cấp là:
  - Connection handling (xử lý kết nối): Khi bạn gửi yêu cầu đến máy chủ, máy chủ sẽ xác nhận yêu cầu. Kết nối được thiết lập ngay lập tức để cho phép bạn thực hiện các yêu cầu tiếp theo.
  - Authentication (xác thực): Xác thực xảy ra ở phía máy chủ khi người dùng kết nối với máy chủ MySQL thông qua tên người dùng và mật khẩu.
  - Security (an toàn): Máy chủ sẽ kiểm tra xem người dùng có các đặc quyền cần thiết để thực hiện các truy vấn cụ thể đối với máy chủ MySQL hay không.
- (2) Server End: Đây là bộ não của MySQL, nó kiểm soát tất cả các chức năng logic của hệ thống. Khi máy khách gửi yêu cầu đến máy chủ, máy chủ sẽ cung cấp đầu ra ngay khi nó khớp với hướng dẫn. Nó bao gồm các thành phần:
  - Thread Handling: Sau khi máy khách gửi yêu cầu thành công đến máy chủ, máy chủ chấp nhận yêu cầu và máy khách kết nối thì được gọi là một luồng -> cần xử lý luồng.
  - Parser: Phân tích cú pháp các truy vấn SQL. Nó kiểm tra tính hợp lệ của các truy vấn và chuyển đổi chúng thành câu lệnh C/C++ SQL mà MySQL có thể hiểu.
  - Query Optimizer: Đảm nhận vai trò tối ưu hóa các truy vấn SQL được gửi từ các máy khách. Nó cố gắng chọn phương pháp thực hiện truy vấn tốt nhất để cung cấp hiệu suất tốt nhất cho cơ sở dữ liệu.
  - Query Cache: Cải thiện hiệu suất truy vấn bằng cách lưu trữ tạm thời các kết quả của các truy vấn SELECT, giảm thiểu việc thực thi lại các truy vấn giống nhau.
  - Buffer và Cache: Hỗ trợ lưu trữ tạm thời dữ liệu và kết quả truy vấn để giảm thiểu việc truy cập đĩa và tăng hiệu suất.
  - Table Metadata Cache: Một phần riêng biệt của bộ nhớ theo dõi thông tin về các đối tượng (tables), cơ sở dữ liệu và chỉ mục. Số lượng cơ sở dữ liệu tỷ lệ thuận với kích thước metadata.
  - Key Cache: Một cơ chế bộ đệm chứa khối chỉ mục được truy cập thường xuyên nhất.
- (3) Storage End: MySQL hỗ trợ nhiều Storage Engine khác nhau, và mỗi Storage Engine có cơ chế lưu trữ và tính năng riêng biệt. Một số Storage Engine phổ biến:
  - InnoDB.
  - MyISAM.
  - Memory (HEAP).
  - CSV.
  - Archive.
## 2.1 Tối ưu truy vấn
Em đã tạo file QuerryQuerry.sql để tạo 1 database đồng thời triển khai 5 ví dụ câu tuy vấn không tối ưu và cách tối ưu chúng.
