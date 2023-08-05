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

