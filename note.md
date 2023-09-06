- Vào folder có vpn, bật vpn: ./vpn
- Mở terminal khác, kết nối: ssh -p 2395 namhh@10.5.0.242 (Authenticator)
- Kết nối vào 1 trong 3 sevver: ssh -p 2395 hdfs3@[**'10.5.94.234'**, '10.5.92.26', '10.5.93.113']
cd data
rm -rf dataNode nameNode
mkdir dataNode nameNode
cd
Máy chủ Master (NameNode):

Namenode: Trên máy chủ master (NameNode), bạn chạy các dịch vụ quản lý dữ liệu HDFS bằng các lệnh như sau:
Khởi động Namenode: hdfs namenode
Khởi động Secondary Namenode (tuỳ chọn): hdfs secondarynamenode
Khởi động ResourceManager (nếu bạn sử dụng YARN): yarn resourcemanager
Máy chủ Datanode:
2. Datanode: Trên các máy chủ datanode, bạn chỉ cần chạy dịch vụ Datanode bằng lệnh sau:

Khởi động Datanode: hdfs datanode
Khởi động NodeManager (nếu bạn sử dụng YARN): yarn nodemanager
Lưu ý rằng việc cài đặt và cấu hình Hadoop cụ thể có thể ảnh hưởng đến cách bạn khởi động dịch vụ. Hãy kiểm tra tài liệu cài đặt và cấu hình của bạn để biết thông tin chi tiết về cách chạy các dịch vụ Hadoop trên máy chủ master và máy chủ datanode của bạn
