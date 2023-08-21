# Q1: Rack trong hdfs là gì?
- Rack là một tập hợp vật lý gồm các nút trong cụm Hadoop (có thể từ 30 đến 40).
- 1 cluster lớn có thể có nhiều rack
- Giao tiếp giữa các Node dữ liệu có trên cùng một rack nhanh hơn khá nhiều so với ở 2 giá khác nhau. 
=> Namenode chọn Datanode gần nhất để đạt được hiệu suất tối đa trong khi thực hiện đọc/ghi thông tin giúp giảm lưu lượng mạng
# Q2: Thứ tự của block replication trong hdfs
- Hadoop đặt replica đầu tiên trên cùng một node vs client (client đang chạy bên ngoài cụm => một nút sẽ được chọn ngẫu nhiên)
- Bản sao replica được đặt trên rack được chọn ngẫu nhiên khác với replica thứ nhất.
- Replica thứ ba được đặt trên một node được chọn ngẫu nhiên trên cùng một giá với replica thứ hai.
- Bất kỳ replica nào khác được đặt trên các node được chọn ngẫu nhiên mà không đặt quá nhiều replica trong cùng một rack.
# Q3: Trình tự đọc/ghi trong hdfs
## Ghi:
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/cb6b4026-fe21-4568-a921-ef67669e1acb)
- Client ghi đệm data trên đĩa cục bộ, đợi cho đến khi có đủ dữ liệu để tạo thành 1 data-block HDFS trước khi liên hệ với Namenode để yêu cầu lưu trữ dữ liệu này.
- Sau khi liên hệ vs client, Namenode check các quyền cần thiết, trả về client dsach các DataNode để ghi vào
- Client bắt đầu ghi vào Datanode
- Datanode nhận dữ liệu theo từng phần, ghi vào local repository rồi chuyển phần đó sang Datanode tiếp theo trong dsach
- 1 data pipeline hình thành từ client đến tất cả các Datanodes, 1 Datanode có thể đồng thởi nhận và truyền data.
## Đọc:
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/8d175c02-8f02-4d82-bc32-9d35d0812863)
- Client gọi RPC (lời gọi thủ tục từ xa) cho Namenode để nhận về vị trí các block chứa file mà client muốn đọc
- Namenode trả về list địa chỉ Datanode cần đọc, đc xếp theo độ gần vs client. Nếu client nằm trên node chứa data nó cần thì sẽ đọc locally.
- Với ng dùng, ứng dụng client đọc như 1 luồng liên tục.
- Ktra lỗi bằng check sum. Lỗi => tự động chuyển thằng datanode khác, sẽ k quay lại node này nữa, báo cho Namenode để xử lí.
- Lưu ý: Namenode chỉ có tác dụng xử lí request cung cấp data node location. Dữ liệu sẽ k dc truyền qua name node mà client đọc trực tiếp từ data node để tránh bottleneck.
## Độ ưu tiên đọc/ghi
- Ưu tiên theo độ gần với node client (cùng node, cùng rack, khác rack)
# Q4: Block report là gì?
- DataNode xác định các bản sao khối mà nó sở hữu cho NameNode bằng cách gửi block report.
  - Block report chứa ID block,  generation stamp(cái này để xdinh phiên bản) và độ dài cho mỗi replica block mà máy chủ lưu trữ.
  - Block report đầu tiên được gửi ngay sau khi đăng ký DataNode (1 báo cáo khối đầy đủ chứa danh mục tất cả các khối của node)
  - Các block report tiếp theo được gửi mỗi giờ (đây là các báo cáo khối gia tăng, cung cấp cho NameNode cập nhật về vị trí của các bản sao khối trên cụm và thông tin liên quan đến các khối đã được thêm/xóa gần đây)
- Giống như block report, DataNode cũng gửi cache report đến NameNode.
  - Cache report chứa danh sách các khối được lưu trong cache trên DataNode.
  - Sau khi nhận được report này, NameNode xác định trạng thái cache trên DataNode và phản hồi bằng cách gửi các hướng dẫn về bộ nhớ đệm để DataNode thực thi.
- Nếu block & cache report xảy ra quá thường xuyên hoặc mất quá nhiều thời gian để hoàn thành, chúng có thể khiến cụm Hadoop gặp trục trặc.
  - Chúng cũng có thể khiến cụm không thể phục vụ các yêu cầu của người dùng cuối một cách hiệu quả.
  => Để tránh kết quả như vậy, admin nên quan sát chặt chẽ các hoạt động block & cache report trên mỗi DataNode, nhanh chóng xác định các DataNode nơi có quá nhiều hoạt động trong số này đang xảy ra hoặc mất quá nhiều thời gian để hoàn thành, suy ra nguyên nhân của sự cố và giải quyết tốt trước khi người dùng cuối nhận thấy bất kỳ độ trễ nào.
