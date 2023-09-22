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
# 5.3 Partition
## 5.4 Message key
## 5.5 Message offset
## 5.6 Schemas
## 5.6 Brokers
## 5.7 Producers
## 5.8 Consumers
