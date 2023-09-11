# 1. Kết nối vào mạng vpn và các sever
- Vào folder có vpn, bật vpn: ./vpn
- Mở terminal khác, kết nối: ssh -p 2395 namhh@10.5.0.242 (Authenticator)
- Kết nối vào 1 trong 3 sevver: ssh -p 2395 hdfs3@[**'10.5.94.234'**, '10.5.92.26', '10.5.93.113']
# 2. Xóa data để format
cd data  
rm -rf dataNode nameNode  
mkdir dataNode nameNode  
cd  
# 3. 1 số câu lệnh khởi động
- Máy chủ Master (NameNode):
Namenode: Trên máy chủ master (NameNode), bạn chạy các dịch vụ quản lý dữ liệu HDFS bằng các lệnh như sau:
  - Khởi động Namenode: hdfs --daemon start namenode
  - Khởi động Secondary Namenode (tuỳ chọn): hdfs --daemon start secondarynamenode
  - Khởi động ResourceManager (nếu bạn sử dụng YARN): yarn --daemon start resourcemanager
- Máy chủ Datanode:
Datanode: Trên các máy chủ datanode, bạn chỉ cần chạy dịch vụ Datanode bằng lệnh sau:
  - Khởi động Datanode: hdfs --daemon start datanode
  - Khởi động NodeManager (nếu bạn sử dụng YARN): yarn --daemon start nodemanager
# 4. Kiểm tra nhật ký
tail -n 1000 hadoop/logs/
# 5. Xem list tiến trình, xóa tiến trình
yarn application -list
yarn application -kill application_1694426408331_0002

