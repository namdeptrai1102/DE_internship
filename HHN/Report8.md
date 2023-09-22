# BUILDING SCALABLE DATA PIPELINES WITH KAFKA
# 1. Introduction
- Đc viết bằng java và scale
- Nền tảng phát trực tuyến sự kiện được phân phối cho phép bạn đọc, ghi, lưu trữ và xử lý các sự kiện (còn được gọi là bản ghi hoặc thông báo trong tài liệu) trên nhiều máy.  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/4652bf80-2032-4bf1-b874-858a6822d7b7)
- Kafka đc sử dụng chính trong building data pipelines and implementing streaming solutions.
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/7aba2bb7-b42a-4169-8ce1-7b391c058aff)
# 2. Characteristic of Distributed Systems
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/33097ffd-401e-46d6-bf6f-5bdd0b57b8fc)
# 3. Messaging Patterns
- Kafkalà trung gian di chuyển các records từ ứng dụng này sang ứng dụng khác và tách chúng ra khỏi nhau.
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/e4726783-1ef9-4f56-9107-b913b7ad9dcb)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/3aacb7ce-7842-4e5a-a942-ce600aa54076)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/ed4cbf41-a518-43bb-8c83-2e82e2ddfb8c)
- Producer of data kbiet consumer of data là ai, cũng kbiet khi nào data được dùng => tách rời producers và consumers (asynchronous messaging) vs 2 pattens:
  - Publish subscribe (Pub-Sub): Producer tạo data và publishes lên 1 channel/topic => message có thể đc nhiều consumers sử dụng và luôn được gửi đến theo thứ tự mà chúng đã đc publish.  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/f2a960fe-4e2f-4f92-a9e9-579ba23bf763)
  - Message queuing: publishes 1 message đến 1 channel đc consumer xử lý duy nhất 1 lần, khi đc xác nhận đã đc sử dụng thì message bị xóa khỏi queue  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/30729308-db34-4759-bf41-6b8051f77045)
- Các trường hợp sdung kafka:
  - Ban đầu nó đc phát triển bởi LinkedIn để track user activity (page views, click tracking, modifications to profile, etc.) and system metrics in real-time.
  - Các tình huống ứng dụng cần gửi thông báo [Chẳng hạn, nhiều ứng dụng khác nhau có thể viết tin nhắn cho Kafka và sau đó một ứng dụng có thể đọc tin nhắn và thực hiện hành động thích hợp (ví dụ: định dạng tin nhắn theo một cách nhất định, lọc tin nhắn, gộp tin nhắn trong một thông báo)]
  - Xây dựng số liệu và ghi nhật ký pipeline: các application có thể publish số liệu Kafka rồi chúng sẽ đc sử dụng bởi các hệ thống giám sát, cảnh báo, phân tích (có thể dùng offlline vs hadoop). Các logs cũng có thể đc publish r chuyển đến các hệ thống log search systems như Elasticsearch hoặc security analysis applications.
  - Kafka dựa trên commit log (nhật ký ghi nhận) để lưu trữ và theo dõi các sự kiện. Khi dữ liệu trong cơ sở dữ liệu thay đổi (ví dụ: một bản ghi được thêm, sửa đổi hoặc xóa), thông tin về thay đổi đó được ghi vào commit log của Kafka. Từ commit log này, có thể trích xuất thông tin về các thay đổi dữ liệu và sử dụng nó để sao chép các cập nhật cơ sở dữ liệu đó lên hệ thống từ xa => data trên hệ thống từ xa luôn cập nhật và đồng bộ với cơ sở dữ liệu gốc.
  - Stream processing: Kafka có thể đc dùng bởi các streaming frameworks (VD: MapReduce), cho phép các application có thể vận hành các Kafka messages cho vc đếm số liệu, partitioning, combining messages, hoặc applying transformations.  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/d87b5b79-24ac-4800-8c69-5c340f4f0bef)
# 4.Commit log
- Commit log là 1 chuỗi các record trong đó mỗi record có ID riêng.
- Đặc điểm:
  - Chỉ có thể thêm record vào cuối commit log
  - Records immutable
  - Commit log luôn đc đọc từ trái sang phải
  - Đơn giản, nhanh chóng và có thể xử lý khối lượng dữ liệu lớn tốt hơn cơ sở dữ liệu quan hệ truyền thống
  - Một hệ thống phức tạp có thể tiếp tục hoạt động khi một số thành phần phụ nhất định gặp lỗi
- Database và cache thường dùng commit log để build lại hệ thống sau sự cố hoặc tối ưu hiệu suất:
  - Mọi thay đổi trước tiên sẽ được ghi vào commit log trước DB (ghi vào commit log) => Cho phép DB trì hoãn việc thực hiện các thay đổi trên đĩa mà chỉ phản ánh chúng trong bộ nhớ.
  - DB có thể tua lại và xem qua events trong commit log và thực hiện các thay đổi đối với cơ sở dữ liệu một cách không đồng bộ. Kể cả khi các thay đổi chưa được thực hiện và xảy ra sự cố, cơ sở dữ liệu vẫn có thể khôi phục bằng cách sử dụng các thay đổi được ghi lại trong commit log.
  - Tóm lại: commit log được sử dụng để tạo lại hoặc sao chép các thay đổi. VD: bản sao của cơ sở dữ liệu có thể đọc các thay đổi từ nhật ký cam kết để tự điều chỉnh theo trạng thái của bản sao cơ sở dữ liệu đang hoạt động.
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/0e4521a3-fef4-434e-ae0f-57026b9d1096)
# 5. Components of Kafka
## 5.1 Message
- Đối vs Kafka, message đơn giản là 1 mảng byte
- Đơn vị dữ liệu trong Kafka ecosystem, giống như 1 record trong 1 bảng relational DB.
  - Message đc gửi theo nhóm và nén lại vs nhau để giảm chi phí
## 5.2 Topic/channel
- Messange được viết và đọc từ các topic, mỗi topic được coi là 1 folder trong hệ điều hành.
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/9e76ea9c-b1ac-4c48-8424-b4f367b28c94)
## 5.3 Partition
1 topic đc chia nhỏ thành các partition, lưu ý:
- Message chỉ được sắp xếp theo thời gian trong một phân vùng, không phải trên toàn bộ topic.
- Message được đọc từ đầu đến cuối trong một phân vùng.
- Message chỉ có thể được thêm vào cuối phân vùng. (giống commit log)
- Các phân vùng cho phép Kafka mở rộng quy mô theo chiều ngang và cũng cung cấp khả năng dự phòng. Mỗi phân vùng có thể được lưu trữ trên một máy chủ khác nhau => cho phép thêm các phân vùng mới vào 1 topic khi tải trên hệ thống tăng lên.
## 5.4 Message key
- Ta có thể lựa chọn message sẽ sử dụng partion nào để đọc ghi với message key (optional)
- Tất cả các message cùng key sẽ đc chuyển đến 1 phân vùng
## 5.5 Message offset
- Message cũng có 1 metadata liên kết tới gọi là offset. Nó là 1 số nguyueen tăng dần dùng để xdinh thứ tự của message trong 1 phân vùng => consumer có thể tiếp tục từ nơi đã dùng lại trc đó
## 5.6 Schemas
- Kafka không bắt buộc nhưng nó khuyến khích các message theo 1 định dạng có cấu trúc dễ hiểu (JSON, XML, Avro,...)
## 5.6 Brokers
- Broker là 1 single Kafka server, nhiều broker vận hành như 1 cụm Kafka
- Cluster sẽ đc điều phối bởi 1 trong những broker (gọi là controller), nó chịu trách nghiệm gán phân vùng cho các broker và giám sát lỗi. Controller đc bầu chọn bởi 5 thành viên trong cluster.
- 1 phân vùng có thể đc sao chép và gán cho nhiều broker. Broker nào giữ phân vùng đc gọi là leader còn những partion-replicating broker là follower. Các producer và consumer sẽ kết nối vs leader.
- Các message sẽ đc lưu trong Kafka 1 tgian rồi sẽ bị xóa sau 1 khoảng tgian hoặc khi topic đạt đến 1 độ lớn nhất định.
- Broker có trách nhiệm nhận message từ producer và commit vào disk, nhận request và gửi các message từ các phân vùng đến consumer.
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/6bd3fcac-7b3c-4e2a-aa7f-ef16f9da58bf)
## 5.7 Producers
- Tạo message, dùng message key gửi đến các partition tùy chỉnh
## 5.8 Consumers
- Đọc các message (vận hành theo nhóm gọi là consumer group, cũng có thể chạy độc lập).
- Mỗi phân vùng đc đọc bởi 1 cosumer nhưng 1 consumer có thể đọc nhiều phân vùng.
- Nếu 1 cái lỗi thì những consumer còn lại sẽ cân bằng lại để chịu lỗi  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/2a0e4520-2a87-4e0d-9cbf-6f87da0162b6)
# 6. Partitions
- Ta có thể chọn tạo message key hoặc ko, nếu ko thì Kafka có cơ chế tự gán default cho phân vùng theo round-robin
- Nếu khóa được xác định nhưng class của phân vùng thì ko => trình phân vùng default đc dùng: nó tạo 1 hàm băm của khóa (bất biến), các message cùng khóa luôn có cùng gtri băm và dc giử đến phân vùng
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/7c3f472f-b496-49dd-b016-8b35a5a5d756)
- Partition Rebalancer
  - Các consumer trong consumer group được điều phối bởi 1 broker (điều phối viên nhóm). Broker lắng nghe heartbeat của consumer, nếu nhận thấy 1 cái crash thì sẽ kích hoạt partion rebalance => high availability and scalability
  - Partition Rebalancer là quá trình chỉ định cviec của phân vùng đã chết cho phân vùng còn khỏe mạnh (trong cùng 1 comsumer group). PR cx có thể thực hiện khi ta add thêm partition vào topic.
  - Khi PR đang đc thực thi, consumer không thể đọc được
  - Khi consumer tbao vs coordinator là nó muốn rời khỏi group, coordinator không cần kiểm tra xem nó là consumer nào mà bắt đầu PR luôn.
  - Tác dụng phụ: những trạng thái duy trì và cache của consumer sẽ bị crash khi PR xảy ra vì lúc đó dữ liệu consumer sẽ phải đọc thành nhiều partition khác nhau  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/00305974-3473-4b74-85ea-651b288d9598)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/28f7fccb-9535-43af-a19c-845f12023af6)
# 7. Kafka Producer
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/13869d29-7ffe-4ef9-8e87-566accfcbc75)
## Write workflow
1. Khởi tạo object từ class ProduceRecord chứa message(value) và topic dự định của nó (message key và partition là optional)
2. Key và val đc gửi qua mạng sẽ đc serialized
3. Data sau đó sẽ đc gửi đến partitioner để thực hiện phân vùng dựa trên khóa, nếu message đã đc chỉ định thì thôi k cần phân vùng cho nó
4. Producer thêm message của 1 loạt record đang chờ gửi đến 1 topic và partition. Một luồng khác sẽ gửi các record tới Kafka broker.
5. Khi broker nhận đc records và viết message tới Kafka thành công => trả về 1 object RecordMetadata chứa data về topic, partiton, và offset trong partition.
6. Nếu ko write đc thì sẽ trả lỗi về producer, producer thử lại vài lần trc khi hủy.
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/f2fe23c9-56be-494a-a47f-efe8f5f15200)
## Sending Message
Có 3 cách để producer gửi message:
- Fire and forget: message gửi đến Kafka mà k cần xác minh xem broker đã nhận đc chưa (hầu hết đều nhận đc cơ mà vx có thể lỗi)
- Synchronous: Message đc gửi và 1 future object trả về sẽ có phương thức get() để gọi xem broker có nhận đc k
- Asynchronous: Message đc gửi và callback đc gọi khi nhận đc phản hồi từ broker => producer có thể tiếp tục gửi các message mà k cần đợi phải hồi từ broker.
# 8. Kafka consumer
- Hiện nay, 1 hay nhiều producer có thể viết message đến 1 topic nhnah hơn nhiều so vs consumer có thể đọc, Kafka giảm thiểu điều này bằng cách cho phép nhiều consumer trong 1 group consumer có thể cùng nhau đọc message từ 1 topic. 1 số cấu hình cho topic:
  - Partitions in a topic and consumers in a group are equal: mỗi consumer đọc từ 1 phân vùng:  
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/c064281d-5521-4619-af16-10c2f1f05620)
  - Partitions in a topic are greater than the number of consumers in a group:  
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/88323a20-16f3-4294-a124-d7fa8393ff90)
  - Single Consumer:  
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/0d4be7d2-6303-4773-b969-f3a5e4c367d4)
  - Partitions in a topic are less than the number of consumers in a group:  
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/87cac4e2-3f5f-4f00-9d13-c74247a2e251)
- Consumer tăng trong group consumer là cơ chế chính để Kafka mở rộng quy mô khi số lượng tin nhắn trong một topic tăng lên => nên có nhiều phân vùng trong một topic để có thể tăng số lượng người tiêu dùng khi tải tăng. 
