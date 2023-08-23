# Tìm hiểu shell linux
- Các command cơ bản:
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
  - head
- Các command liên quan đến quyền : chmod, chown, ls -l
- Các command thực hiện song song:
cat test.txt | wc -l
cat test.txt | grep "a"
cat test.txt | head
echo "aabb" > test.txt
echo "cc" >> test.txt
- Sử dụng vim: tạo file mới, sửa file, save,vvv
- Quản lý tiến trình: htop, ps aux, kill -9
