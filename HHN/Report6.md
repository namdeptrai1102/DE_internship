# 1. Tìm hiểu shell linux
## 1.1 Các command cơ bản:
- cd: chọn thư mục (cd .. để back lại thư mục trc đó)
- ls: trả về 1 list file và folder
- cp: copy file/thư mục sang 1 destination path
- mv: di chuyển file/thư mục sang 1 destination path
- mkdir: tạo 1 thư mục (nếu chưa tồn tại)
- cat:
  - In nội dung file (tại dòng n -optional): cat -n filename (dùng thêm more để xem theo từng trang và less để tùy chỉnh xem lên xuống)
  - In nội dung nhiều file: cat filename1 filename2
  - Tạo file mới, viết nội dung file(ctrD để hoàn thành): cat >filename
  - Cho nội dung từ file này vào file khác: cat filenam1 > filename2
  - Ghép nội dung từ nhiều file vào file khác: cat filenam1 filename2 > filename3
  - In đảo ngược thứ tự của file được ghép vào: tac filename3 (nó sẽ in nội dung filename2 rồi in filename1)
  - Nối file trước vào đuôi file sau: cat filenam1 >> filename2
  - Nối nội dung vào 1 file: cat >> filename
- head -N filename: in N dòng đầu trong file (default là 10)
## 1.2 Các command liên quan đến quyền:
- chmod [options] [mode] [File_name]
  - [options]:
    - -R: áp dụng thay đổi quyền một cách đệ quy cho tất cả các tệp và thư mục trong thư mục đã chỉ định.
    - -v: hiển thị một thông báo cho mỗi tệp được xử lý, chỉ ra cả sự thay đổi quyền đã được thực hiện
    - -c: hoạt động tương tự như '-v' nhưng trong trường hợp này nó chỉ hiển thị thông báo cho các tệp có quyền được thay đổi
  - [mode]:
    - Đối tượng: u(owner), g(group), o(others), a(all)
    - Toán tử: + (thêm quyền), - (bớt quyền), = (trao quyền)
    - Quyền: r(đọc), w(ghi), x(thực thi)
    - Octal mode: sử dụng 3 bit biểu diễn quyền của u,g,o (VD: 777 tức là tất cả đc thực thi cả 3 quyền)
- chown: thay đổi chủ sở hữu của file hoặc folder: sudo chown [options] new_owner[:new_group] file(s)
    - new_owner: Tên người dùng mới mà bạn muốn đặt làm chủ sở hữu cho tệp hoặc thư mục.
    - new_group: Tên nhóm người dùng mới mà bạn muốn đặt cho tệp hoặc thư mục (tùy chọn).
    - file(s): Danh sách các tệp hoặc thư mục mà bạn muốn thay đổi chủ sở hữu.
    - VD: chown user1 example.txt(sở hữu bởi user1), chown user2:group2 myfolder
(sở hữu bởi user2 nhóm 2)
- ls -l: trả về list các file và thư mục cùng thông tin của chúng:
    - VD: drwxrwxr-x 3 hoanghainam hoanghainam 4096 Jul 21 09:39 apache-maven
    - drwxrwxr-x: d là thư mục (file là -), theo sau là quyền
    - 3: Đây là số lượng liên kết tới thư mục hoặc tệp
    - hoanghainam: Đây là tên người dùng chủ sở hữu của thư mục hoặc tệp.
    - hoanghainam: Đây là tên của nhóm mà người dùng thuộc về. Tất cả các thành viên trong nhóm này có quyền truy cập như nhau đối với thư mục hoặc tệp.
    - 4096: Đây là kích thước của thư mục hoặc tệp trong byte.
    - Jul 21 09:39: Đây là ngày và thời gian mà thư mục hoặc tệp được sửa đổi cuối cùng.
    - apache-maven: Đây là tên của thư mục hoặc tệp.   
## 1.3 Các command thực hiện song song:
- cat test.txt | wc -l: in ra số dòng của file
  - nếu chỉ có wc thì default là: số dòng - số từ - số kí tự
- cat test.txt | grep "a": in ra tất cả các dòng chứa kí tự "a"
- cat test.txt | head: in ra các dòng đầu tiên của file, defaut là 10
- echo "aabb" > test.txt: ghi mới "aabb" vào file test.txt 
- echo "cc" >> test.txt: nối tiếp "cc" vào file test.txt
## 1.4 Sử dụng vim:
Vim là 1 text editor nâng cao và có cấu hình cao được xây dựng để cho phép chỉnh sửa văn bản hiệu quả, nỗ trợ hầu hết các loại tệp => vim còn được gọi là trình soạn thảo của lập trình viên  
**Hướng dấn: vimtutor**
  - Tạo file mới: vim filename.txt
  - Sửa file: i + Enter (sau đó bắt đầu ghi nội dung), Esc để thoát insert mode
    - i: con trỏ tại vị trí hiện tại
    - a: con trỏ tại vị trí sau hiện tại
    - o: con trỏ tại dòng dưới vị trí hiện tại
    - 0: con trỏ tại dòng trên vị trí hiện tại 
  - Save and exit: ":wq!" (nếu k muốn save thì ":q!")
  - Di chuyển con trỏ (dùng mũi tên):
    - Di chuyển nhanh hơn: k^, h< ,l>, jv
    - Di chuyển 2 từ: 2w
    - Di chuyển 4 dòng: 4$
    - Di chuyển đến đầu file: :gg
    - Di chuyển đến cuối file: :G
  - Xóa:
    - Xóa từ: dw (2 từ: d2w)
    - Xóa dòng: d$
    - Xóa kí tự: trỏ đến kí tự cần xóa + x
  - Undo: u, Redo: crt + r
  - Replace:
    - Replace kí tự: r
    - Replace từ: re
    - Replace cả dòng: c$
## 1.5 Quản lý tiến trình:
  - htop: chạy 1 giao diện giống lệnh top trên linux (show các tiến trình trên linux)
   https://www.cyberciti.biz/faq/how-to-install-htop-on-ubuntu-linux-using-apt/#:~:text=Procedure%20to%20install%20htop%20on%20Ubuntu%20Linux%201,snap%20install%20htop%205%20Launch%20htop%2C%20type%3A%20htop
    - Giải thích các cột:
      - PID: ID của tiến trình
      - PR: độ ưu tiên của tiến trình (số càng thấp ưu tiên càng cao)
      - VIRT: tổng số bộ nhớ ảo đc task sử dụng
      - USER: tên ng sở hữu task
      - %CPU: Mức sử dụng CPU
      - TIME+: CPU time
      - SHR: kích thước bộ nhớ dùng chung task sử dụng
      - NI: Nice Value của task (âm => độ ưu tiên cao hơn, dương => độ ưu tiên thấp hơn)
      - %MEM: Memory usage của task
      - RES: lượng RAM vật lý tiến trình sử dung (KB)
      - COMMAND: tên của lệnh bắt đầu tiến trình
    - 1 số câu lệnh htop:
      - htop trắng đen: htop -c hoặc htop --no-color
      - Xem tiến trình htop theo cấu trúc cây: htop -t hoặc htop --tree
      - Xem tiến trình của user có tên là vivek: htop -u vivek hoặc htop --user=vivek
      - Xem tiến trình theo PID: htop -p PID1, PID2 hoặc htop --pid = PID1, PID2
    - Shortcut keys trong htop:
      ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/022cafcd-3745-4751-bcf3-7e816d7b8f10)
- ps aux: Hiển thị tổng quan về tất cả các tiến trình đang chạy
- kill [OPTIONS] [PID]
  - [OPTIONS]: kill -l trả về danh sách mọi lệnh kill, phổ biến nhất là 3 lệnh:
    - kill -1: reload tiến trình
    - kill -9: kill tiến trình
    - kill -15: dừng tiến trình (default)
  - [PID]: PID của tiến trình
    - VD: kill -9 6263 6199 6142 6076
    - Lấy PID tiến tình bằng lệnh pidof (VD: pidof firefox) => có thể gộp câu lệnh: kill -9 $(pidof firefox)
# 2. Tìm hiểu Docker
## 2.1 Chạy rest API cơ bản bằng flask python
Tham khảo: https://www.digitalocean.com/community/tutorials/how-to-build-and-deploy-a-flask-application-using-docker-on-ubuntu-20-04#1-minor-inconsistency-in-uwsgi-ini
### Bước 1: Set up flash application
- Tạo 1 thư mục flash, bên trong nó có 1 thư mục con là app gồm 2 thư mục con là static và templates  
- Trong app, tạo file __init__.py với nội dung:  
      from flask import Flask  
      app = Flask(__name__)  
      from app import views  
- Trong app, tạo file views.py:  
       from app import app  
      @app.route('/') #đường dẫn gốc  
      def home(): #funtion home  
      return "hello world!"  
- Tạo file uwsgi.ini:  
    [uwsgi]
    module = main  
    callable = app  
    master = true  
    sudo touch uwsgi.ini #cái này để cập nhật thay đổi mà k cần phải restart lại docker  
- Tạo file requirements.txt:  
    Flask>=2.0.2
### Bước 2: Set up Docker
- Tạo Dockerfiles:  
FROM tiangolo/uwsgi-nginx-flask:python3.8-alpine  
RUN apk --update add bash nano  
ENV STATIC_URL /static  
ENV STATIC_PATH /var/www/app/static  
COPY ./requirements.txt /var/www/requirements.txt  
RUN pip install -r /var/www/requirements.txt  
- Check cổng xem có free không: sudo nc localhost 56733 < /dev/null; echo $?  (trả về 1 là đúng)
- Tạo file start.sh và thực thi:
  #!/bin/bash  
  app="docker.test"  
  docker build -t ${app} .  
  docker run -d -p 56733:80 \  
  --name=${app} \  
  -v $PWD:/app ${app}  
- Check containers: sudo docker ps
- Kết quả:  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/4bb09224-1093-4648-baf2-1eaaf75343df)
### Bước 3: Tạo template Ping - Pong
- Trong app, tạo thư mục templates gồm 1 file pong.html:
//  <!doctype html>  
//<html lang="en-us">     
//  <head>  
//    <meta charset="utf-8">  
//    <meta http-equiv="x-ua-compatible" content="ie=edge">  
//    <title>pong!</title>  
//  </head>  
  
//  <body>  
//    <h1>Home Page</h1>  
//    <p>PONG!!!!!!</p>  
//  </body>   
//</html>  
- Sửa file views.py thành:
from app import app  
from flask import render_template  

@app.route('/')  
def home():  
   return "Hello"  

@app.route('/ping')  
def ping():  
    return render_template('pong.html')  
- Chạy: sudo touch uwsgi.ini  
- Kết quả:  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/6b0e8243-7697-4189-a2c2-2f8f9df42225)

- 
