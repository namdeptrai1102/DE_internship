# Tìm hiểu shell linux
## Các command cơ bản:
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
## Các command liên quan đến quyền:
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
## Các command thực hiện song song:
- cat test.txt | wc -l: in ra số dòng của file
  - nếu chỉ có wc thì default là: số dòng - số từ - số kí tự
- cat test.txt | grep "a": in ra tất cả các dòng chứa kí tự "a"
- cat test.txt | head: in ra các dòng đầu tiên của file, defaut là 10
- echo "aabb" > test.txt: ghi mới "aabb" vào file test.txt 
- echo "cc" >> test.txt: nối tiếp "cc" vào file test.txt
## Sử dụng vim:
Vim là 1 text editor nâng cao và có cấu hình cao được xây dựng để cho phép chỉnh sửa văn bản hiệu quả, nỗ trợ hầu hết các loại tệp => vim còn được gọi là trình soạn thảo của lập trình viên 
**Chạy: vim**
  - Tạo file mới:
  - Sửa file
  - Save
## Quản lý tiến trình:
  - htop
  - ps aux
  - kill -9
