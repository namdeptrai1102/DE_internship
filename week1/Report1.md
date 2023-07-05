# 1.Yêu cầu
## 1.1. Yêu cầu (1): oop
Mô tả: xây dựng chương trình java bất kì có xử dụng đầy đủ 4 tính chất của oop  
Tham khảo: http://www.w3resource.com/java-tutorial/java-object-oriented-programming.php  
Điều kiên hoàn thành: Từ chương trình đã xây dựng trình bày về đã áp dụng oop như nào, (2) hiểu các khái niệm interface,static,....  
## 1.2. Yêu cầu (2) Đọc ghi file
viết trương trình java đọc ghi file theo 2 dạng binary và text  
viết trương trình java thao tác với file và thư mục: list các file, đọc nội dung file  
# 2. Kết quả
## 2.1. Kết quả (1): oop  
**Em đã tạo package "oop" với 5 lớp:**
- Lớp trừu tượng Practice in ra loại bài tập thể dục    
- Lớp Exercise kế thừa lớp Practice và là lớp cha của 2 lớp con Pull và Push  
- Lớp Demo để chạy thử 1 số kết quả   
*4 tính chất của oop:*
- Tính đóng gói(Encapsulation): Đối tượng Exercise có các thuộc tính và phương thức được đóng gói trong lớp Excercise, khi sử dụng, ta chỉ cần gọi tên phương thức chứ không cần truy cập đến dữ liệu bên trong.  
- Tính kế thừa (Inheritance): Lớp Push và Pull kế thừa lớp Exercise, lớp Exercise kế thừa lớp Practice. Các lớp con có thể sử dụng các phương thức và thuộc tính của lớp cha (miễn sao trong phạm vi chỉ định truy cập). Điều này giúp tái sử dụng mã nguồn, tránh lỗi không đáng có và làm code ngắn gọn hơn. 
- Tính trừu tượng (Abstraction): Lớp Practice là lớp trừu tượng có một phương thức trừu tượng là practice(), từ đó ta có thể ẩn tất cả dữ liệu hoặc quy trình không liên quan, giảm độ phức tạp và tăng hiệu quả sử dụng.  
- Tính đa hình (Polymorphism): Phương thức khởi tạo của Exercise có thể được gọi bằng 1 hoặc 3 tham số đầu vào; ngoài ra lớp Exercise, Push, và Pull ghi đè lại phương thức practice() của lớp Practice cũng là 1 biểu hiện của tính đa hình.
## 2.2. Kết quả (2): Đọc ghi file
### Ghi file
- FileWritingBinary(ghi file nhị phân): em tạo 1 đối tượng FileOutputStream tên là fos, phương thức write() ghi từng byte vào file Binary.txt
- FileDirectWritingText và FileIndirectWritingText đều tạo đối tượng BufferedWrite để ghi từ bộ nhớ đệm từng kí tự vào file, FileDirectWritingText ghi từ bàn phím còn FileIndirectWritingText ghi từ dữ liệu cho sẵn.
### List danh sách, đọc file
- List danh sách: em tạo 1 đối tượng File là directory và 1 mảng File[] là files được truyền vào qua directory.listFiles(). Sau đó chạy 1 vòng lặp duyệt và in tên của các file trong mảng files.
- Đọc file: em sử dụng BufferedReader để đọc từng dòng trong file Binary.txt và in ra màn hình
