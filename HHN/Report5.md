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
## 3.1 YARN (Yet Another Resource Negotiator)
- Yarn đc coi tương tự như 1 hđh cho 1 cluster (một tập hợp các máy tính được kết nối, hoạt động cùng nhau để được xem như một hệ thống duy nhất; đại diện cho tập hợp các tài nguyên)
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/06bb31f6-1d04-4a8e-8d41-fb97aae89409)
- Responsibilities: 
  - Quản lý tài nguyên cluster như máy tính, mạng và bộ nhớ
  - Lập kế hoạch và giám sát công việc
- Thực hiện thông qua 2 long-running daemons:
  - Resource Manager:
    - Applications Manager: chạy ApplicationMaster container (khác với docker, container ở đây chỉ là quy định về tài nguyên)
    - Scheduler: phân phối resources disk, memory, CPU, network,...
  - Node Manager: khởi tạo containers, báo cáo tài nguyên sử dụng về Scheduler.
- Workflow:
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/45f2cda7-14eb-495a-9825-35254592343b)
  - Client yêu cầu RM tạo 1 AM process
  - Client submits job và RM sẽ tìm 1 Node Manager khởi chạy container để lưu trữ AM process(đại diện cho client job/application)
  - Node Managers khác được khởi chạy containers hay mặt AM process để chạy tính toán phân tán (các nút được chọn để phân bổ vùng chứa mới càng gần dữ liệu đầu vào càng tốt)
   ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/75d1306c-07f5-44b2-84cc-78124ac08629)
  - Việc ánh xạ công việc có thể xảy ra theo 3 cách:
    - 1 job mỗi application.
    - Nhiều job nỗi application: reused, dữ liệu giữa các công việc có thể được lưu vào bộ nhớ đệm.
    - Ứng dụng chạy vĩnh viễn.
  - Horizontal vs Vertical Scaling
    - Horizontal scaling (mở rộng chiều ngang): mở rộng quy mô bằng cách thêm nhiều máy hơn vào nhóm tài nguyên
    - Vertical scaling (mở rộng chiều dọc): mở rộng quy mô bằng cách nâng cấp (CPU, RAM) vào máy hiện có  
      ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/eb24c360-8f9c-4b2b-9251-759b66aa130a)
- Scheduling:
  - FIFO Scheduler: k phù hợp với shared cluster  
    ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/aafca7b5-6c08-497d-9f64-9b07117c0656)
  - Capacity Scheduler: chia queue ra thành nhiều hàng đợi nhỏ hơn, công việc sẽ được chỉ định hàng đợi thông qua mapreduce.job.queuename, nếu ko thì vào default  
    ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/49333917-66a5-495d-8cc8-1dd57f6d7276)
  - Fair Scheduler : tự động điều chỉnh cho công bằng các job  
    ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/2d523254-5ac7-441c-82ce-17386b186d67)
## 3.2 Map Reduce
### 3.2.1 Basic
- MapReduce là một mô hình lập trình được sử dụng để xử lý các tập dữ liệu lớn trên 1 cluster bằng cách sử dụng thuật toán phân tán.
- Nó là 1 hệ thống xử lý hàng loạt và không phù hợp để phân tích tương tác(truy vấn chậm)
- Gồm 2 giai đoạn:
  - Map phase: dùng 1 map function tạo một tập hợp các key/value trị trung gian.
  - Reduce phase: hợp nhất tất cả các giá trị trung gian được liên kết với cùng một khóa trung gian.
- Đặc điểm: phân tán (map task, reduce task -> nhiều hardware), Song song(reduce task), Chịu lỗi(khởi động lại khi lỗi), Scalable (Horizontally)
- Nó cần được Serializable(cho viết và lưu trữ) and Comparable(cho giai đoạn map)
### 3.2.2 Mapper
- Giải thích code:
  - Truyền vào 1 file text, chia nhỏ nó thành các words qua dấu “ “
  - Xuất ra các cặp key/val, đc sắp xếp bởi reducers sau này
  - Key là tên hãng xe, mỗi lần xuất hiện có val là 1
  => Output là các cặp key (hãng xe) và value = 1, key được phép trùng nhau
- Lưu ý: sắp xếp dữ liệu diễn ra ở phía map, kphai phía reduce
- Map task chạy trên 1 input split, ghi output vào 1 circular buffer (default 100MB), nếu bộ đệm đầy => 1 background thread cho vào 1 tệp tràn.
- Output sẽ đc viết vào local disk của node đang chạy map (tạm thời, k viết trực tiếp vào hdfs), chú ý việc partition (phân vùng chỉ khi reducer >1).
- K có gì đảm bảo một file text sẽ trong 1 block (> 128mb) => Input splits: nếu file đó viết 1 khối ko đủ => chỉ định khối tiếp theo để đọc (block đó có thể cùng node, cùng rack hoặc khác rack)
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/d1a594ba-690e-4225-b81a-bb77adb0861b)
### 3.2.3 Reducer
- Mỗi map task có 1 phân vùng dữ liệu đc sắp xếp cho mỗi reducer, data đc copy sang reducer đích qua HTTP.
- Reducer k đợi tất cả map tasks hoàn thành mà copy song song từ những map tasks đã hoàn thành.
- Data đc sắp xếp và hợp nhất bởi các mappers trc khi chuyển cho reducer
- Reduction function được gọi cho mỗi key và danh sách value của nó. Một reduce task sẽ gộp tất cả các giá trị được liên kết với một khóa nhất định.
- Lưu ý: tất cả các bản ghi cho một khóa nhất định nằm trong một phân vùng duy nhất => một reduce task duy nhất xử lý tất cả dữ liệu cho một khóa nhất định.
 ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/47fd6aad-4f90-4b0b-8d7f-ec8f08ba4760)
### 3.2.4 Combinator and Partitioner
- Combiner: một action cho đầu ra của mapper, giảm thiểu lưu lượng dữ liệu cần truyền từ Mapper đến Reducer bằng cách tổng hợp các giá trị trung gian tương tự với nhau, thực hiện các phép tính đơn giản, có thể bị tắt hoặc thực thi tùy cấu hình.
- Partitioner: cấu hình cụ thể partion nào chứa data nào.
### 3.2.5 Tổng hợp
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/185f9584-2ed2-4705-81e3-5e6a56ce79f4)
1. Job được submit qua waitForCompletion()
2. class JobSubmitter trao đổi vs RM, lấy application ID và check path
3. Copy job resources theo application ID
4. Job đc thực thi tới RM bằng cách gọi phg thức submitApplication() trên RM.
5. ApplicationMaster quản lý vòng đời của 1 job, trong MapReduce thì AM là MRAppMaster
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/49e74179-2714-40af-bc01-8d9a6dad616f)
6. AM khởi tạo một số book-keeping object để theo dõi các task mapper và reducer
7. Nếu job có thể chạy hoàn toàn trên node đó thì là uber job
8. Nếu không sẽ yêu cầu RM allocate thêm. Reducer chạy ở đâu cx được nhưng mapper thì nên chạy ở các node có data locally (các input splits: cùng node, cùng rack hoặc khác rack)
9. Chạy MapReduce
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/f3611c9e-687f-446b-913f-5345455a9b70)
10. Có thể sử dụng các định dạng đầu ra khác ngoài file.
11. Báo cáo tiến trình cho AM, client có thể theo dõi qua UI.
12. Kết thúc, kết quả đc in lên bảng điều khiển, dọn dẹp trạng thái và xóa các output trung gian
### 3.2.6 Resiliency (phục hồi)
- Task failure: báo lại cho AM => chỉ định rescheduling lại task đó 
- AM failure: RM nghe heartbeat và khởi động lại AM nại Node Manager khác 
- Node Manager failure: RM nghe heartbeat và restart lại AM mà đã chạy trên node đó
- RM failure: HA Zookeeper (tìm hiểu sau)

# 4. SPARK