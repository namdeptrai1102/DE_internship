# Q1: Các loại storage engine được hỗ trợ bởi MySQL là gì? (default là InnoDB)
## 1.1 Transactional (em có tìm hiểu thêm bên dưới)
- InnoDB:
  - Lưu trữ tuân thủ ACID(em có note ở mục 2). Nó hỗ trợ row-level locking(cái này nó giống khóa trong concurrency java, có 4 cấp khóa là Database Level, Table Level, Page-Level và Row Level), khôi phục sự cố và kiểm soát đồng thời nhiều phiên bản. 
  - Là công cụ duy nhất cung cấp ràng buộc toàn vẹn tham chiếu khóa ngoại (đảm bảo tính đúng đắn của dữ liệu trong quá trình insert, delete, update của các bảng có ràng buộc khóa ngoại)
- CSV:
  - Lưu trữ dữ liệu trong tệp CSV.
  - Cung cấp tính linh hoạt cao vì dữ liệu ở định dạng này dễ dàng được tích hợp vào các ứng dụng khác (excel, pdf, gg sheet, ...).
- Merge (Mrg_MyISAM):
  - Merge hoạt động trên các bảng MyISAM bên dưới.
  - Hợp nhất các bảng giúp quản lý khối lượng lớn dữ liệu dễ dàng hơn.
  - Nó nhóm một loạt các bảng MyISAM giống hệt nhau và tham chiếu chúng dưới dạng một đối tượng.
  - Tốt cho môi trường data warehouse.
- Blackhole:
  - Chấp nhận nhưng không lưu trữ dữ liệu, truy xuất luôn trả về một tập hợp trống.
  - Phù hợp sử dụng trong thiết kế cơ sở dữ liệu phân tán nơi dữ liệu được sao chép tự động nhưng không được lưu trữ cục bộ.
  - Có thể được sử dụng để thực hiện các bài kiểm tra hiệu suất hoặc thử nghiệm khác.
- Federated:
  - Cung cấp khả năng tách các máy chủ MySQL để tạo một cơ sở dữ liệu logic từ nhiều máy chủ vật lý.
  - Các truy vấn trên máy chủ cục bộ được thực hiện tự động trên các bảng (liên kết) từ xa.
  - Không có dữ liệu nào được lưu trữ trên các bảng cục bộ. Tốt cho môi trường phân tán.
## 1.2 Non-transactional
- MyISAM:
  - Lưu trữ nhanh, không hỗ trợ transactional.
  - MyISAM cung cấp table-level locking.
  - Được sử dụng chủ yếu trong Web và kho dữ liệu.
- Memory:
  - Tạo các bảng trong bộ nhớ, storage engine nhanh nhất.
  - Cung cấp table-level locking, không hỗ trợ transactional.
  - Lý tưởng để tạo các bảng tạm thời hoặc tra cứu nhanh.
  - Dữ liệu bị mất khi khởi động lại cơ sở dữ liệu.
- Archive:
  - Tối ưu hóa để insert tốc độ cao.
  - Nó nén dữ liệu khi nó được insert vào, không hỗ trợ transactional.
  - Lý tưởng để lưu trữ và truy xuất một lượng lớn dữ liệu lưu trữ, lịch sử hiếm khi được tham chiếu.
### Tìm hiểu thêm: Transaction là gì?
- TRANSACTION trong SQL là tiến trình thực hiện một nhóm các câu lệnh SQL. 
- Các câu lệnh này được thực thi một cách tuần tự và độc lập. 
- Một Transaction được thực hiện thành công khi tất cả câu lệnh đều thành công -> tất cả các thay đổi dữ liệu được thực hiện trong Transaction được lưu vào cơ sở dữ liệu. 
- Nếu chỉ một trong số đó thất bại -> toàn bộ tiến trình sẽ thất bại -> với việc dữ liệu phải rollback về trạng thái ban đầu.
- Tính chất:
  - Atomicity (bảo toàn): hoặc là tất cả các câu lệnh thành công, hoặc hủy bỏ
  - Consistency (nhất quán): đảm bảo rằng cơ sở dữ liệu thay đổi chính xác các trạng thái khi một transaction được thực thi thành công.
  - Isolation (độc lập): cho phép các Transaction hoạt động độc lập và minh bạch với nhau.
  - Durability (bền vững): không có chuyện dữ liệu của Transaction sau khi thực thi có thể chuyển lại trạng thái dữ liệu lúc trước khi thực hiện.
- Các lệnh điều khiển Transaction chỉ được sử dụng với các lệnh thao tác dữ liệu như INSERT, UPDATE và DELETE. Tuy nhiên chúng không thể được sử dụng trong lệnh CREATE TABLE hoặc DROP TABLE vì các hoạt động này được tự động xác định trong cơ sở dữ liệu.
(Phần này em có đọc thêm về các lệnh trong MySQL DDL[thay đổi liên quan đến cấu trúc csdl chứ kphai dữ liệu], DQL[truy vấn], DML[thay đổi về dữ liệu], DCL[các quyền điểu khiển, truy cập csdl], TCL[điều khiển transaction])
# Q2: OLAP DB có hỗ trợ các thao tác thay đổi dữ liệu không?
- KHÔNG vì mục tiêu chính của OLAP là phân tích và truy vấn dữ liệu, chúng thường không hỗ trợ thêm, sửa hoặc xóa các bản ghi trong dữ liệu trực tiếp. Thay vào đó, dữ liệu trong OLAP databases được sao chép từ hệ thống gốc (thường là OLTP).
### Tìm hiểu thêm: Updates and backups trong 2 mô hình:
- Update:
  - Các hệ thống OLAP được thiết kế để xử lý các truy vấn bao gồm hàng nghìn đến hàng triệu hàng dữ liệu.
  - Dữ liệu được cập nhật hàng giờ đến hàng ngày tùy thuộc vào nhu cầu của tổ chức.
  - Ngược lại, các hệ thống OLTP thường cập nhật một vài hàng dữ liệu tại một thời điểm theo thời gian thực hoặc gần thời gian thực.
- Backup:
  -  Các hệ thống OLTP được sao lưu thường xuyên hơn nhiều so với các hệ thống OLAP do bản chất của OLTP là một công cụ xử lý giao dịch -> cần phải sao lưu thường xuyên để duy trì hoạt động kinh doanh và tuân thủ các yêu cầu pháp lý và quy định có liên quan.
  -  Mọi mất dữ liệu phát sinh trong hệ thống OLAP đều có thể được khắc phục bằng cách tải lại dữ liệu bị mất từ ​​nguồn ban đầu.
# Q3: Index hoạt động thế nào
## 3.1 Trong SQL:
- Index là 1 bảng gồm 2 cột (key-value) là Search Key và Data Reference, khi tạo index cho một cột, hệ thống cơ sở dữ liệu sẽ tạo một bản sao nhỏ của dữ liệu trong cột đó và duy trì một cấu trúc dữ liệu tìm kiếm nhanh để giữ cho các giá trị của cột được sắp xếp và dễ tìm kiếm.
    ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/3b4e129d-fab5-443c-9260-f49c59b68163)
  - Data Reference chứa tập hợp các con trỏ chứa địa chỉ khối đĩa.
  - Con trỏ khối đĩa chứa dữ liệu thực được tham chiếu bới Search Key.
  - Data Reference còn được gọi là Block Pointer vì nó sử dụng địa chỉ dựa trên khối (block-based addressing)  
    [Tìm hiểu thêm: Cái này em hiểu nó là một phương pháp để xác định các vị trí vật lý của dữ liệu trong hệ thống lưu trữ. Trong block-based addressing, không gian lưu trữ được chia thành các phần nhỏ và cố định gọi là "block" hoặc "sector". Mỗi block có kích thước nhất định, dữ liệu được lưu trữ trên các block này và được quản lý bằng cách sử dụng một bảng định vị (mapping table) để xác định vị trí của các block trong không gian lưu trữ vật lý.]  
    [Tìm hiểu thêm: Mapping table là một cơ chế để ánh xạ địa chỉ logic sang địa chỉ vật lý và ngược lại, vì không gian lưu trữ ảo và không gian lưu trữ vật lý có thể khác nhau. ]
- Vì các Index tables có kích thước nhỏ nên chúng được lưu trữ trong bộ nhớ chính.
    ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/0a6c1a8b-70f6-471a-a0a7-3cecde027f4b)
- Index sắp xếp bằng cách sử dụng cấu trúc dữ liệu là Btree.
### Tìm hiểu thêm về Btree trong DB:
- B-Tree:
  - B-Tree lưu trữ dữ liệu sao cho mỗi nút chứa các khóa theo thứ tự tăng dần. Mỗi khóa này có hai tham chiếu đến hai nút con khác. Các khóa nút con bên trái nhỏ hơn các khóa hiện tại và các khóa nút con bên phải lớn hơn các khóa hiện tại. Nếu một nút có số lượng khóa là "n" thì nút đó có thể có tối đa "n+1" nút con.
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/abf8fab9-ca76-40ff-8c4c-9a5caa48ad43)
  - Tốc độ:
   1.	Search O(log n)
   2.	Insert O(log n)
   3.	Delete O(log n)
- B+Tree (InnoDB dùng cái này):
  - B+Tree là một cấu trúc dữ liệu khác được sử dụng để lưu trữ dữ liệu và trông gần giống như B-Tree. Sự khác biệt duy nhất là B+Tree lưu trữ dữ liệu trên các nút lá -> tất cả các giá trị nút không phải lá được sao chép lại trong các nút lá.
  - Các nút lá bao gồm tất cả các giá trị và tất cả các bản ghi được sắp xếp theo thứ tự. B+tree cho phép thực hiện tìm kiếm giống như B-tree, nhưng cũng cho phép duyệt qua tất cả các giá trị trong một nút lá nếu chúng ta đặt một con trỏ tới mỗi nút lá như sau.
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/23716ea1-2365-44c8-9a98-87951f2f6db9)
- Tóm lại thì B+Tree sắp xếp nhanh hơn còn B-Tree nhanh hơn khi thêm giá trị vào giữa, nhưng nhìn chung thì B+Tree tốt hơn B-Tree.
## 3.2 Trong NoSQL (em chỉ đọc tóm lược của MongoDB):
- Cũng sử dụng cấu trúc B-tree.
- Có thể hoàn toàn tùy biến tên của index, nhưng khi đã được tạo thì không sửa được nữa.
- 1 số kiểu index:
  - Single Field: đánh index theo 1 trường
  - Compound Index: đánh index theo nhiều trường
  - Multikey Index: hỗ trợ truy vấn các mảng trong các documents. Khi tạo một index trên một cột chứa mảng, MongoDB sẽ tạo một Multikey Index cho cột đó -> mỗi phần tử trong mảng sẽ trở thành một mục riêng biệt trong index.
  - Geospatial Index: hỗ trợ các truy vấn dữ liệu tọa độ không gian địa lý
  - Wildcard Indexes: sử dụng các chỉ mục ký tự đại diện để hỗ trợ truy vấn đối với nhiều trường tùy ý hoặc không xác định.
  - Hashed Indexes: lập chỉ mục cho hàm băm của giá trị của một trường.
# Q4: Ngoài đánh index theo số thứ tự thì có đánh index theo chuỗi được không?
ĐƯỢC. Theo em thì mình chọn cột nào để đánh thì các giá trị cột đó sẽ trở thành Search Key thôi, tuy nhiên k nên làm vậy lắm vì nó tốn bộ nhớ hơn là sử dụng ID.
# Q5: Đánh index cho nhiều cột hoạt động như thế nào?
- Chỉ mục nhiều cột là chỉ mục lưu trữ dữ liệu trên tối đa 32 cột.
- Khi tạo chỉ mục nhiều cột, thứ tự cột là rất quan trọng, các chỉ mục nhiều cột được cấu trúc để có cấu trúc phân cấp.
- Giả sử ta có bảng đánh single-index như sau:
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/b8597066-4f16-4ff5-b141-2435eda952be)
  - Ta đánh index cho cột make:
    ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/6e49d40b-2d5c-4e7a-b21e-268535769c7b)
  - Bây giờ bảng index có 1 cột gồm các con trỏ tới 1 bảng tham chiếu phụ được xếp theo cột make. Tương tự ta đánh index cho cả cột model
    ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/efbcbdb9-b1b8-4245-b9aa-95ea32e25224)
- Ta có 1 câu truy vấn như sau: SELECT * FROM myTable WHERE year=2017 AND make='ACURA' AND model='TL';. Khi đó bảng index chính (ID:, year) sẽ được truy cập trước tiên rồi sẽ tham chiếu tới bảng tham chiếu phụ thứ nhất rồi tham chiếu tới bảng tham chiếu phụ thứ hai.
- Tuy nhiên nếu ta chỉ truy vấn cột make và model thì lại không dùng Multicolumn Indexes được vì con trỏ tham chiếu tới 2 bảng phụ không thể truy cập được.
- Xem thêm gifs trong link này để hiểu rõ hơn: https://dataschool.com/sql-optimization/multicolumn-indexes/
# Q6: Tại sao biết MongoDB thuộc loại CP và Cassandra thuộc loại AP?
Em nghĩ việc phân loại rạch ròi CP, AP hay CA không hoàn toàn chính xác. Có thể 1 số hệ thống thường ưu tiên C hoặc A hoặc P hơn nhưng nhìn chung nó là sự đánh đổi. Các hệ thống trong thế giới thực hiếm khi nằm gọn gàng 1 trong 3 loại này. Phần này em sẽ tìm hiểu sâu hơn sau.
