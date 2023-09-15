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
# 6. Copy thư mục từ xa
scp -P 2395 * hdfs3@10.5.92.26:/home/hdfs3/hadoop/etc/hadoop  
scp -P 2395 * hdfs3@10.5.93.113:/home/hdfs3/hadoop/etc/hadoop
# 7. Demo WC
hadoop jar word_count/MapReduceExample-1.0-SNAPSHOT.jar demo.WC_Runner hdfs://10.5.94.234:8023/alice.txt hdfs://10.5.94.234:8023/r_output
# 8. Hiện html
curl 10.5.94.234:8080
# 9 Cài đặt & chạy jupyter notebook trong server
- Tải Anaconda, install bằng: bash ~/Anaconda3-2023.07-2-Linux-x86_64.sh (nên chọn yes để default)
- vi .bashrc, thêm đường dẫn PATH của anaconda, source ~/.bashrc (anaconda-navigator ko chạy đc trên terminal đâu đừng cố)
- Kiểm tra các môi trường: conda env list
- Tạo môi trường mới (jupy): conda create --name=jupy
- Vào môi trường jupy: source  activate jupy
- Cài đặt ipykernel để  chạy jupyter notebook: conda install -c anaconda ipykernel
- Cài đặt jupyter notebook: python -m ipykernel install --user --name=jupy
- Nếu không thấy file config thì: jupyter notebook --generate-config
- Config cổng: vi /home/hdfs3/.jupyter/jupyter_notebook_config.py (thêm dòng c.NotebookApp.port = 9003)
- Chạy jupyter notebook: jupyter-notebook --port=9003 --ip=0.0.0.0  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/9c508e82-be97-440c-a927-ca53b53841d2)
Nhớ dùng token để đặt mật khẩu không thì đăng nhập vỡ mồm :))


 



