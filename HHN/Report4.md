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
- MySQL thường nằm trên một máy duy nhất, nhưng nó sẽ gửi CSDL đến một số vị trí => người dùng có thể truy cập thông qua các giao diện máy khách MySQL, các giao diện này chuyển các câu lệnh SQL tới máy chủ và hiển thị kết quả.
###  Các thành phần:
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/141b706c-a1ce-4a74-82ed-7c7745a6959d)
- (1) Client End: Đây là thành phần mà người dùng tương tác với. Bạn gửi nhiều lệnh MySQL tới máy chủ thông qua terminal hoặc GUI. Một số dịch vụ quan trọng do Client end cung cấp là:
  - Connection handling (xử lý kết nối): Khi bạn gửi yêu cầu đến máy chủ, máy chủ sẽ xác nhận yêu cầu. Kết nối được thiết lập ngay lập tức để cho phép bạn thực hiện các yêu cầu tiếp theo.
  - Authentication (xác thực): Xác thực xảy ra ở phía máy chủ khi người dùng kết nối với máy chủ MySQL thông qua tên người dùng và mật khẩu.
  - Security (an toàn): Máy chủ sẽ kiểm tra xem người dùng có các đặc quyền cần thiết để thực hiện các truy vấn cụ thể đối với máy chủ MySQL hay không.
- (2) Server End: Đây là bộ não của MySQL, nó kiểm soát tất cả các chức năng logic của hệ thống. Khi máy khách gửi yêu cầu đến máy chủ, máy chủ sẽ cung cấp đầu ra ngay khi nó khớp với hướng dẫn. Nó bao gồm các thành phần:
  - Thread Handling: Sau khi máy khách gửi yêu cầu thành công đến máy chủ, máy chủ chấp nhận yêu cầu và máy khách kết nối thì được gọi là một luồng => cần xử lý luồng.
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
## 2.2 Tối ưu truy vấn
Em đã tạo file QuerryQuerry.sql để tạo 1 database đồng thời triển khai 5 ví dụ câu tuy vấn không tối ưu và cách tối ưu chúng: https://github.com/namdeptrai1102/DE_internship/blob/main/QuerryQuerry.sql
## 2.3 Database
### 2.3.1 SQL Database
- Cơ sở dữ liệu SQL phù hợp để lưu trữ dữ liệu có cấu trúc.
- Có hai loại ứng dụng:
  - Online Transaction Processing (OLTP): Thu thập, lưu trữ và xử lý dữ liệu từ các giao dịch trong thời gian thực.
  - Online Analytical Processing (OLAP): phân tích dữ liệu lịch sử tổng hợp từ các ứng dụng OLTP.
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/fdcf0de8-6a51-4695-a68c-ecf1891bc3d5)
#### 2.3.1.1 Relational Databases (RDBMS)  
*Relation Database Management(RDBMS) là một trong những kho dữ liệu sớm nhất. RDBMS được tối ưu hóa cho khối lượng công việc OLTP yêu cầu đọc nhanh và cập nhật một số lượng lớn hàng. Đó là lý do tại sao RDBMS là cơ sở dữ liệu hướng theo hàng.*
- Ví dụ điển hình của RDBMS là MySQL và Postgresql, chúng gần tương tự nhau vậy nên em chỉ bổ sung 1 số lợi ích của Postgresql:
  - Hỗ trợ tính năng phức tạp: PostgreSQL cung cấp nhiều tính năng mạnh mẽ bao gồm cửa sổ (window functions), truy vấn đệ quy, thủ tục lưu trữ (stored procedures), VIEW và trigger. 
  - Bảo mật mạnh mẽ: PostgreSQL cung cấp hệ thống bảo mật linh hoạt và phong phú. Nó hỗ trợ quyền chi tiết, quản lý bảo mật đáng tin cậy và các tính năng bảo mật cao cấp khác. 
  - Khả năng mở rộng: PostgreSQL hỗ trợ khả năng mở rộng dễ dàng, đặc biệt là khi xử lý các cơ sở dữ liệu lớn và phức tạp. Nó hỗ trợ phân vùng (partitioning), có thể quản lý dữ liệu lớn một cách hiệu quả và cải thiện hiệu suất truy vấn.
  - Chế độ xem (VIEW) cập nhật: PostgreSQL cho phép cập nhật dữ liệu thông qua các chế độ xem (VIEW), điều này không được hỗ trợ trong MySQL. Điều này giúp dễ dàng thực hiện các hoạt động cập nhật và quản lý dữ liệu.
  - Chế độ phân tích Full-Text: PostgreSQL hỗ trợ tìm kiếm và phân tích Full-Text mạnh mẽ, cho phép tìm kiếm và truy vấn dữ liệu dựa trên nội dung văn bản một cách linh hoạt.
- MySQL thích hợp cho các ứng dụng web và ứng dụng có lưu lượng truy vấn lớn và cần hiệu suất tốt.
- PostgreSQL phù hợp cho các ứng dụng có tính năng phức tạp, yêu cầu tích hợp cao và cần tính bảo mật mạnh mẽ.
#### 2.3.1.2 Columnar Databases
*Trong khi bản ghi nằm trên hàng, thuộc tính phân tích được tính toán trên cột. Các ứng dụng OLAP cần thao tác đọc cột được tối ưu hóa trên bảng. Cơ sở dữ liệu cột được thiết kế cho thông lượng cao của tập hợp cột. Đó là lý do tại sao Columnar DB là cơ sở dữ liệu hướng theo cột. Các Data Warehouses hiện đại đều sử dụng Columar DB*
- Ví dụ điển hình là Amazon Redshift với 1 số điểm nổi bật như sau:
  - Cấu trúc dựa trên cột: Amazon Redshift lưu trữ dữ liệu theo cấu trúc dựa trên cột, điều này cho phép nén dữ liệu hiệu quả hơn và giảm tải cho các truy vấn phức tạp. Thay vì truy cập toàn bộ hàng, nó chỉ tải các cột liên quan đến truy vấn, giúp cải thiện hiệu suất truy vấn.
  - Hiệu suất cao: Nhờ cấu trúc dựa trên cột và việc sử dụng phân tán song song, Amazon Redshift có hiệu suất cao cho các truy vấn phức tạp trên các tập dữ liệu lớn. Nó hỗ trợ các hàm tổng hợp, gom nhóm và tích hợp với các công cụ phân tích dữ liệu phổ biến, cho phép phân tích dữ liệu một cách linh hoạt và mạnh mẽ.
  - Tích hợp dễ dàng: Amazon Redshift tích hợp dễ dàng với các công cụ phân tích dữ liệu phổ biến như Tableau, Power BI và Google Data Studio. Điều này giúp người dùng trực quan hóa và phân tích dữ liệu từ Redshift một cách thuận tiện.
  - Khả năng mở rộng: Amazon Redshift có khả năng mở rộng tự động, giúp đáp ứng nhu cầu của các ứng dụng có khối lượng truy vấn lớn. 
  - Bảo mật và kiểm soát truy cập: Amazon Redshift cung cấp các tính năng bảo mật mạnh mẽ, bao gồm kiểm soát truy cập dựa trên vai trò (Role-Based Access Control), quản lý chứng chỉ và mã hóa dữ liệu trong truy vấn.
  - Mức giá linh hoạt: Amazon Redshift sử dụng mô hình giá cước dựa trên lượng dữ liệu đã xử lý. Người dùng chỉ phải trả tiền cho lượng dữ liệu thực tế được truy vấn và không cần phải quản lý hạ tầng phức tạp.
- Amazon Redshift là một giải pháp data warehouse mạnh mẽ và linh hoạt, hỗ trợ các doanh nghiệp và nhà phát triển trong việc xử lý và phân tích dữ liệu lớn một cách dễ dàng và hiệu quả.
### 2.3.2 No SQL Database
