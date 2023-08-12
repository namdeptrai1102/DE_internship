# 1. Hadoop ecosystem
- Hadoop là 1 thể hiện của Big Data, nó là một nền tảng đáng tin cậy, phân tán và có thể mở rộng để lưu trữ và phân tích lượng dữ liệu khổng lồ.
- Type of data:
  - Structure: SQL
  - Unstructure: Videos, audio, blogs, log files, social-media posts, ...
  - Semi-structured: bao gồm cả structure và unstructure (JSON, XML,...)
- DataBase: SQL và NoSQL (đã trình bày ở báo cáo trước đó)
- Big Data Defined:
  - Volume: Khối lượng dữ liệu
  - Velocity: Tốc độ truy cập và sử dụng dữ liệu
  - Variety: đa dạng nguồn dữ liệu
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/be57e369-61ed-4a0a-9b2a-032cf4d32fab)
  - Hiện nay có thêm 4 đặc trưng khác: Veracity(xác thực), Value(giá trị, lợi ích), Visualization, Variability(khả năng thay đổi) 
- Big data và data warehouse
  - Data warehouse: hệ thống lấy dữ liệu từ nhiều nguồn khác nhau về một tổ chức, biến đổi và lưu trữ dữ liệu đó cho mục đích báo cáo và phân tích, nó lưu trữ số lượng lớn dữ liệu lịch sử và cho phép truy vấn nhanh, phức tạp trên tất cả dữ liệu.
    ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/5cccc5ef-cbaf-4ef6-bd33-14f2ec1c671e)
    ETL : Extract - Transform - Load
  - Data Warehouse vs Database:
    - OLAP và OLTP (đã trình bày ở báo cáo trước đó)
    - DB thông thường k phù hợp ới các truy vấn phân tích phức tạp vì số lượng bảng yêu cầu join => cần đến data warehouse.
  - Big Data vs Data Warehouse:
    - Data warehouses: có cấu trúc, có schema, csdl quan hệ
    - Big Data: cấu trúc lỏng lẻo và thường không được tinh chỉnh
    - Data Lake: lưu trữ dữ liệu thô, không có cấu trúc và có cấu trúc, thuộc mọi loại, kích thước và định dạng ở định dạng gốc (> data warehouse).
    - Data Mart: tập con của data warehouse, phục vụ nhu cầu của một bộ phận cụ thể trong một tổ chức.
# 2. HDFS
# 3. YARN & Map Reduce
## 3.1 YARN
# 4. SPARK
