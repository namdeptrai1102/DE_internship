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
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/38552096-9da8-48db-bde3-5f2d1bca4c51)
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
*Cơ sở dữ liệu NoSQL phục vụ cho các loại dữ liệu bán cấu trúc: key-value, wide column, document (tree), và graph.*
#### 2.3.2.1 Key-Value Database
- Kho lưu trữ Key-value là CSDL từ điển hoặc hashmap. Nó được thiết kế cho các hoạt động CRUD với một khóa duy nhất cho mỗi bản ghi: CREAT (key-value), Read(key), Read(key), Delete(key, value)
- Các giá trị không có schema cố định và có thể là bất kỳ thứ gì từ giá trị nguyên thủy đến cấu trúc phức hợp.
- Key-value stores có khả năng phân vùng cao (do đó có thể mở rộng theo chiều ngang).
- Redis là một kho lưu trữ key-value phổ biến:
  - Redis chủ yếu được sử dụng để lưu trữ dữ liệu tạm thời, bộ đệm (cache) dữ liệu, và hỗ trợ cho các ứng dụng thời gian thực như chat, đếm ngược, phân tích dữ liệu, và hệ thống theo dõi.
  - Điểm nổi bật của Redis:
    - Hiệu suất cao: Redis hoạt động hoàn toàn trong bộ nhớ và sử dụng cấu trúc dữ liệu tối ưu để đạt được hiệu suất tốt nhất. Nó có thể xử lý hàng triệu lượt truy vấn mỗi giây, làm cho nó trở thành một trong những hệ quản trị cơ sở dữ liệu nhanh nhất.
    - Hỗ trợ nhiều cấu trúc dữ liệu: Redis hỗ trợ nhiều cấu trúc dữ liệu như strings, hashes, lists, sets, sorted sets, bitmaps và hyperloglogs. Điều này cho phép lưu trữ và thao tác dữ liệu một cách linh hoạt và hiệu quả.
    - Replication và High Availability(tính khả dung): Redis hỗ trợ sao chép dữ liệu và có khả năng High Availability thông qua việc cấu hình các node Redis thành các cụm sao chép và chịu lỗi.
    - Hỗ trợ truy vấn atomic và transactions: Redis hỗ trợ các hoạt động atomic và transactions, cho phép thực hiện nhiều hoạt động trong một giao dịch, đảm bảo tính toàn vẹn dữ liệu.
    - Lua scripting: Redis hỗ trợ thực thi các tập lệnh Lua bên trong máy chủ, cho phép người dùng thực hiện các tác vụ phức tạp và tùy chỉnh.
  - Redis là một công cụ mạnh mẽ cho việc lưu trữ và truy xuất dữ liệu nhanh chóng và có hiệu suất cao. Nó được sử dụng rộng rãi trong các ứng dụng web, hệ thống thời gian thực, và bộ nhớ cache để cải thiện hiệu suất ứng dụng.
#### 2.3.2.2 Wide-column Database
- Wide-column Database có bảng, hàng và cột. Nhưng tên của các cột và loại của chúng có thể khác nhau đối với mỗi hàng trong cùng một bảng.
- Về mặt logic, Nó là một ma trận thưa thớt được phiên bản với ánh xạ đa chiều (giá trị hàng, giá trị cột, dấu thời gian). Nó giống như một kho lưu trữ khóa-giá trị hai chiều, với mỗi giá trị ô được đánh phiên bản bằng dấu thời gian.
- Wide-column Database có khả năng phân vùng cao. Nó có một khái niệm về họ cột được lưu trữ cùng nhau.
- Tọa độ logic của một ô là: (Khóa hàng, Tên cột, Phiên bản).
- Tra cứu vật lý như sau: Từ điển khu vực ⇒ Thư mục họ cột ⇒ Khóa hàng ⇒ Họ tên cột ⇒ Định tính cột ⇒ Phiên bản => Wide-column Database là cơ sở dữ liệu theo hàng.
- Apache HBase là 1 ví dụ điển hình.
Đọc thêm: https://www.slideshare.net/larsgeorge/hbase-in-practice
#### 2.3.2.3 Document Database
- Document Database dùng để lưu trữ và truy xuất tài liệu bao gồm các đối tượng lồng nhau, một cấu trúc cây như XML, JSON và YAML.
- Document Database khai thác cấu trúc cây của giá trị để cung cấp các hoạt động phong phú hơn. Đây là CSDL hướng tài liệu.
- MongoDB là một ví dụ phổ biến về Document Database.
  - Điểm nổi bật:
    - Tài liệu dưới dạng JSON: MongoDB lưu trữ dữ liệu dưới dạng các tài liệu JSON, giúp dễ dàng lưu trữ dữ liệu phức tạp và linh hoạt.
    - Cơ sở dữ liệu không có cấu trúc cứng: MongoDB cho phép lưu trữ các tài liệu có cấu trúc khác nhau trong cùng một bảng (collection), không yêu cầu định nghĩa cơ sở dữ liệu cứng trước.
    - Tích hợp tốt với các ngôn ngữ lập trình: MongoDB hỗ trợ nhiều ngôn ngữ lập trình như Java, Python, Node.js, và nhiều ngôn ngữ khác, giúp dễ dàng tích hợp vào các ứng dụng.
    - Cung cấp các tính năng linh hoạt: MongoDB hỗ trợ các tính năng như Replica Set (đảm bảo bền vững dữ liệu), Sharding (mở rộng quy mô), Full-text search (tìm kiếm toàn văn bản), Aggregation (tổng hợp dữ liệu), và nhiều tính năng khác.
    - Tích hợp tốt với công nghệ đám mây: MongoDB cung cấp phiên bản dành cho các môi trường đám mây như MongoDB Atlas, giúp dễ dàng triển khai và quản lý cơ sở dữ liệu trên đám mây.
  - MongoDB được sử dụng rộng rãi trong các ứng dụng web, ứng dụng di động, hệ thống IoT, phân tích dữ liệu và các ứng dụng có yêu cầu lưu trữ dữ liệu phức tạp và linh hoạt.
#### 2.3.2.4 Graph Database
- Graph Database giống như Document Database nhưng được thiết kế cho đồ thị thay vì cây tài liệu.
- Ví dụ, cơ sở dữ liệu đồ thị sẽ phù hợp để lưu trữ và truy vấn kết nối mạng xã hội, điển hình là Neo4j: 
  - Neo4j là một hệ quản trị cơ sở dữ liệu đồ thị. Thay vì sử dụng cấu trúc bảng truyền thống như các hệ quản trị cơ sở dữ liệu quan hệ (SQL), Neo4j sử dụng mô hình đồ thị, trong đó dữ liệu được biểu diễn bằng các node (đỉnh) và các mối quan hệ (cạnh) giữa chúng.
  - Đặc điểm nổi bật của Neo4j:
    - Cơ sở dữ liệu đồ thị: Neo4j hỗ trợ lưu trữ dữ liệu dưới dạng đồ thị, cho phép biểu diễn và truy vấn các mối quan hệ phức tạp một cách hiệu quả. Điều này làm cho nó phù hợp cho các ứng dụng có tính chất mạng xã hội, lưới điện, định tuyến, phân tích dữ liệu phức tạp, và nhiều lĩnh vực khác.
    - Ngôn ngữ truy vấn Cypher: Neo4j sử dụng ngôn ngữ truy vấn Cypher, một ngôn ngữ đồ thị đơn giản và trực quan để truy vấn dữ liệu. Cypher cho phép người dùng dễ dàng tạo các truy vấn phức tạp liên quan đến mối quan hệ giữa các node.
    - Hiệu suất cao: Nhờ kiến trúc đồ thị, Neo4j cung cấp hiệu suất truy vấn cao đối với các dữ liệu có mối quan hệ phức tạp. Nó sử dụng các thuật toán tối ưu hóa để tối đa hóa thời gian truy vấn và đảm bảo tính mở rộng.
    - Các tính năng quan hệ: Neo4j hỗ trợ các tính năng quan hệ như các thuộc tính, chỉ mục, ghi chú, và trọng số cho các mối quan hệ. Điều này cho phép dữ liệu được biểu diễn một cách chi tiết và linh hoạt.
    - Mở rộng dễ dàng: Neo4j có thể mở rộng dễ dàng với các cụm và phân tán dữ liệu. Điều này cho phép nó xử lý các tập dữ liệu lớn và có khả năng mở rộng khi cần thiết.
  - Neo4j được sử dụng rộng rãi trong nhiều lĩnh vực, bao gồm mạng xã hội, e-commerce, phân tích dữ liệu phức tạp, quản lý nguồn lực, và nhiều ứng dụng khác có tính chất mạng.
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/5c9116bc-6214-4778-9d0c-2d918f0e8a43)
# 3. Bonus
## CAP (NoSQL)
https://www.ibm.com/topics/cap-theorem
- Thuật ngữ CAP gồm 3 thành phần:
  - Consistency (tính nhất quán): tất cả các máy khách đều nhìn thấy cùng một dữ liệu tại cùng một thời điểm, bất kể chúng kết nối với nút nào.
  - Availability (tính khả dụng): bất kỳ máy khách nào yêu cầu dữ liệu đều nhận được phản hồi, ngay cả khi một hoặc nhiều nút ngừng hoạt động.
  - Partition tolerance (dung sai phân vùng): cụm phải tiếp tục hoạt động mặc dù có bất kỳ sự cố giao tiếp nào giữa các nút trong hệ thống (phân vùng ở đây nghĩa là điểm bị ngắt liên lạc trong HPT)
-  NoSQL DB được phân loại dựa trên 2 tính chất CAP mà nó hỗ trợ:
  - CP database (Consistency & Partition tolerance): Khi một phân vùng xảy ra giữa hai nút bất kỳ, hệ thống phải tắt nút không nhất quán (nghĩa là làm cho nó không khả dụng) cho đến khi phân vùng được giải quyết.[VD: MongoDB]
  - AP database (Availability & Partition tolerance): Khi một phân vùng xảy ra, tất cả các nút vẫn khả dụng nhưng những nút ở đầu sai của phân vùng có thể trả về phiên bản dữ liệu cũ hơn các nút khác. Khi phân vùng được giải quyết, cơ sở dữ liệu AP thường đồng bộ lại các nút. [VD: Apache Cassandra]
  - CA database (Consistency & Availability): mang lại tính nhất quán và tính khả dụng trên tất cả các nút. Tuy nhiên, nó không thể làm điều này nếu có một phân vùng giữa hai nút bất kỳ trong hệ thống -> không có khả năng chịu lỗi [chỉ có trên lý thuyết]
