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
## 2.1 File system
- OS file system : Apple’s MacOS dùng APFS(Apple File System), thay thế hệ thống trc đó là HFS+. Windows hỗ trợ FAT, các biến thể của FAT và NTFS file systems, còn Linux hỗ trợ EXT family of file systems.
- Distributed file systems: sử dụng network, truy cập nhiều node.
- Disk block: đơn vị nhỏ nhất cho đọc ghi 
- Metadata: owner, security access controls, ngày thay đổi gần nhất, ngày tạo, size (được lưu trong i-node). 
- i-node : lưu metadata , chứa thông tin địa chỉ vật lí này lưu data gì (map physical -> logical)
## 2.2 The big picture
- Mục tiêu của HDFS: lưu trữ file lớn, streaming data acess(tối ưu ghi một đọc nhiều), chạy trên hardware giá rẻ.
- 2 software daemons tạo ra HDFS:
  - Name node (stores metadata): theo dõi tất cả dữ liệu được lưu trữ trên HDFS, kiểm soát access của máy khách vào tệp. Lưu trữ trên memory => limit của hdfs phụ thuộc vào memory của name node.
  - Data node (stores acture data): giữ actural bytes tạo nên 1 file đc lưu trữ trong HDFS.
- Data-locality: node chạy map reduce muốn tối thiểu hóa network transfer cần ở gần data cần tới
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/77de0bf2-708f-41e9-87ff-ef6122ac7970)
## 2.3 HDFS block
- Các filesystem nằm bên trên physical disk, hoạt động trên 1 mức trừu tượng gọi là filesystem block thay vì làm vc trực tiếp với các disk blocks (sự phức tạp được che giấu vs ng dùng filesystem).
- HDFS kphai 1 filesystem vật lý, nó là một sự trừu tượng hóa ảo trên các hệ thống tệp dựa trên đĩa phân tán.
- Trong HDFS, file có thể lớn hơn bất kì disk nào trong cụm. Default block size là 128MB, đây là đơn vị nhỏ nhất để THAM CHIẾU (đọc/ghi)  mà name node có thể tham chiếu tới.
  - VD muốn lưu file 10MB thì cũng chỉ mất 10MB ổ đĩa chứ k phải 128MB
  - 128 mb k  phải là một đơn vị lưu trữ mà là một đơn vị để lưu vị trí của nó trong metadata của name node, tức là namenode sẽ lưu thông tin data dang ở block nào chứ k lưu cụ thể hơn.
    ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/99788114-f295-4da2-b850-4935f0832131)
- Mỗi block có thể replication 
- Large block size:
  - Giảm áp lực cho namenode phải lưu trữ thông tin, các file mang tính sequence hoặc file nén (HAR) nằm liền kề nhau đc gộp thành một tệp HAR chiếm một khối HDFS duy nhất trong bộ nhớ.
  - Giảm thời gian tìm kiếm: Các filesystem blocks xuất hiện liên tục trên đĩa vật lý.
  - Cải thiện tốc độ mạng: mỗi block đc truyền trên 1 kết nối TCP, HDFS có thể duy trì kết nối TCP liên tục với Datanode và giảm chi phí mạng. Nhiều block lớn thì số lượng block sẽ ít => Name node ít đc yêu cầu hơn => ít lưu lượng truy cập mạng => ít kết nối TCP liên tục.
  - Tăng tốc độ Map Reduce: khối quá nhỏ => quá  nhiều mapper với ít hoạt động, tốn chi phí quản lý, khối cx k nên quá lớn => quá ít mapper, tốn tgian chạy
- Hdfs k đọc ghi song song
## 2.4 Block Replication
- Fault tolerant là một dạng dự phòng phần cứng đầy đủ
- Trên thực tế:
  - Replica đầu tiên đc đặt trên cùng node vs client
  - Replica thứ 2 đc đặt ngẫu nhiên ở 1 rack khác, replica thứ 3 cũng đc đặt ngẫu nhiên trên rack này
  - Các replica sau sẽ đc đặt ngẫu nhiên trên các node nhưng k quá nhiều bản trên cùng 1 rack.
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/5bbfb5b4-e0a1-42ad-b1f1-03f83239f58d)
## 2.5 Name node
- Name node lưu trữ tất cả các metadata của file và thư mục trong cây
- Chúng đc lưu trên local disk bởi 2 thực thể sau:
  - Namespace Image File (FS Image): là một checkpoint system metadata tại 1 ID transaction cụ thể.
  - Edit log: nhật ký thay đổi của metadata với lần gần nhất FS image 
- Khi Namenode khởi chạy, nó chọn FS image và áp dụng edit log để nhận trạng thái mới nhất của metadata. Sau đó Namenode ghi 1 HDFS state mới vào FS image và bắt đầu hoạt động bth với 1 edit file trống.
- Mỗi lần chạy Namenode đều phải thao tác vs edit log, edit log lớn sẽ khiến cho Namenode khởi động chậm => Cần Secondary Namenode
- Secondary Namenode
  - Kphai backup Namenode
  - Kết hợp định kì edit log với FS image
  - Giúp Namenode chính nhưng k thể thay thế nếu Namenode chính failure
  - Thường chạy tại máy khác vì nó yêu cầu tài nguyên như Namenode chính
  - Ko hdong nếu HDFS triển khai high availability (trình bày sau)
- Namenode là single point of failure của HDFS: chết là sụp đổ cả hệ thống => giải pháp:
  -  Backup image file và edit log
  -  Secondary namenode: duy trì bản copy FS image nhưng bị delay time => vẫn mất data
  -  Standby namenode (trình bày sau)
- Hạn chế: lưu trữ trong memory nên sẽ bị limit dung lượng hdfs phụ thuộc vào name node memory 
## 2.6 Datanode
- Datanode lưu trữ data blocks trong local filesystem và gửi block report cho Namenode
- Dữ liệu của HDFS đc lưu trữ trong thư mục đc chỉ định (cài đặtt cấu hình dfs.datanode.data.dir, default là ${hadoop.tmp.dir}/dfs/data)
## 2.7 Đọc ghi trong HDFS
- Ghi:
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/753d33c9-01e5-4e2b-8c70-de4a6c2b3899)
  - Client ghi đệm data trên đĩa cục bộ, đợi cho đến khi có đủ dữ liệu để tạo thành 1 data-block HDFS trước khi liên hệ với Namenode để yêu cầu lưu trữ dữ liệu này.
  - Sau khi liên hệ vs client, Namenode check các quyền cần thiết, trả về client dsach các DataNode để ghi vào.
  - Client bắt đầu ghi vào Datanode
  - Datanode nhận dữ liệu theo từng phần, ghi vào local repository rồi chuyển phần đó sang Datanode tiếp theo trong dsach
  - 1 data pipeline hình thành từ client đến tất cả các Datanodes, 1 Datanode có thể đồng thởi nhận và truyền data.
- Đọc:
 ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/dd7148be-bf14-41d1-a23c-6fae109822e1)
  - Client gọi RPC (lời gọi thủ tục từ xa) cho Namenode để nhận về vị trí các block chứa file mà client muốn đọc
  - Namenode trả về list địa chỉ Datanode cần đọc, đc xếp theo độ gần vs client. Nếu client nằm trên node chứa data nó cần thì sẽ đọc locally.
  - Với ng dùng, ứng dụng client đọc như 1 luồng liên tục.
  - Lỗi => tự động chuyển thằng datanode khác, sẽ k quay lại node này nữa. Ktra lỗi bằng check sum, nếu sai nó sẽ báo cho Namenode để xử lí.
  - Lưu ý: Namenode chỉ có tác dụng xử lí request cung cấp data node location. Dữ liệu sẽ k dc truyền qua name node mà client đọc trực tiếp từ data node để tránh bottleneck.
## 2.8 High Availability
## 2.9 Distcp


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
## 4.1 Intro
- 1 nền tảng phổ cập về xử lí dữ liệu, vượt trội hơn so với MapReduce:
  - Job lặp đi lặp lại sẽ k tốn time I/O
  - Hỗ trợ analysis truy vấn hiệu quả (Hive hoặc Pig)
  - Cung cấp nhiều api
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/5571b493-584d-4aea-901b-87576642e613)
  [2 trường hợp đầu: sử dụng các kỹ thuật lưu trữ tạm thời hoặc tối ưu hóa cách thực hiện các truy vấn để tránh phải đọc dữ liệu cùng một từ đĩa nhiều lần]
## 4.2 Architecture 
- Spark có nhiều điểm tương đồng với MapReduce, gồm 2 tiến trình là tiến trình điều phối và tiến trình phụ là:
  - Driver: quản lý
  - Executor: thực thi job được driver chỉ định và gửi lại trạng thái
   ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/00e193e8-02b9-4beb-b201-ae9d563b1d45)
- Spark tương thích với 1 số quy trình quản lý cụm:
  - Hadoop YARN: ‘HADOOP_CONF_DIR’ đc cài đặt trong file spark-env.sh (xác định đường dẫn đến thư mục chứa các tệp cấu hình của Hadoop)
  - Apache Mesos
  - Built-in standalone cluster manager: chỉ có các thành phần cụ thể của Spark, không phụ thuộc vào các thành phần Hadoop và trình điều khiển Spark đóng vai trò là trình quản lý cụm (tự nó có bộ quản lý phân tán của chính nó)
  - Kubernetes
  - Local mode: toàn bộ ứng dụng Spark chạy trên local jvm 
- Execution modes:
  - Cluster mode: 
    - User submits a spark application tới cluster manager
    - Manager sinh ra tiến trình driver và executor để thực thi job
    - Cả driver và executor đều trong 1 cluster
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/58ba2e16-ebc2-4733-a06e-45291f36d8e3)
  - Client mode:
    - Gần giống với cluster mode, khác là driver process và  executor process k đc đặt trên cùng 1 cluster.
    - Máy client duy trì driver process, cluster chịu trách nhiệm duy trì executor process.
    ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/0a178e33-e83c-4285-8021-cd53d6592a7c)
## 4.3 Spark Application Life Cycle:
  1. User submit Spark job cho cluster (nếu cluster management là YARN thì client sẽ kết nối với RM, nếu đc accept thì RM sẽ tạo 1 Spark driver process trong cluster)
  2. Thiết lập 1 SparkSession (điểm truy cập duy nhất để tương tác với Spark), nó sẽ giao tiếp vs cluster manager daemon (RM) để khởi chạy executor processes.
  3. RM chạy executor processes trên các node và trả về location cho diver process(RM chạy executer xong sẽ giao quyền lại cho driver điều khiển các executer)
  4. Driver giao task cho executer và excuter báo cáo trạng thái lại cho driver
  5. Diver thoát khi các Spark job hoàn thành => RM tắt các executor processes
## 4.4 Spark API
- Bao gồm RDD ở mức phi cấu trúc cấp thấp, DataFrames và Datasets ở mức cấu trúc cấp cao.
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/a277456d-4d1c-4409-9d42-3f0aea3e5499)
### 4.4.1 RDD (Resilient Distributed Datasets)
- Read-only (bất biến)
- Đc phân vùng trên cụm, có thể vận hành song song và có thể khôi phục nếu một node bị falure
-  Ko khuyến khích dùng
-  Gthich tên:
  - Resilient: có khả năng chịu lỗi, tự tìm lại đc cách tính toán những phân vùng bị mất
  - Distributed: hoạt động trên cluster
  - Datasets: đa dạng nguồn data như json, csv, text, database 
- Có thể dùng cache cho cviec lặp lại, hỗ trợ phân vùng thủ công
- RDD là bất biến => Transformations cho phép dùng RDD để tạo ra 1 RDD mới (chúng sẽ k đc thực thi cho đến khi một hành động được thực hiện)
- RDD theo dõi transformation của nó bằng đồ thị phụ thuộc, giúp ta biết đc những transformation nào phải đc thực thi sau khi 1 hdong đc gọi.
- Mỗi RDD có 1 số thuộc tính như sau:
  - List các phân vùng
  - Function tính toán mỗi phân vùng
  - List phụ thuộc vào các RDD khác
  - Phân vùng tùy chọn cho RDD key-value (nếu cầu quản lý việc phân phối dữ liệu dựa trên các khóa).
  - List tùy chọn các vị trí ưu tiên tính toán phân vùng (tính toán trên các máy gần với dữ liệu giúp tối ưu hóa hiệu suất)
