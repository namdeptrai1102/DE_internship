# 1. Yêu cầu
## 1.1. Exception
- Nắm được cách xử lí exception, hệ thống các exception trong java, lấy ví dụ về ít nhất 2 loại exception(Checked Exception và Unchecked Exception) và cách fix nó.
- Tham khảo: http://www.journaldev.com/1696/java-exception-handling-tutorial-with-examples-and-best-practices
## 1.2 Concurrency (optional):3.2 Concurrency (optional):
- Yêu cầu: (1) viết 1 luồng chạy ngầm kế thừa Runnable sử dụng java, (2) viết chương trình sử dụng threadpool bằng ngôn ngữ java
- Tham khảo: http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/ (cái này nó viết bằng syntax của java 8)
- Tìm hiểu lock, atomic integer, concurrent hashmap, safe thread, unsafe thread, daenmon thread.
- Seminar: thực tập sinh trong phần này nếu có nhu cầu có thể làm silde thuyết trình về những tìm hiểu của mình với team platform.
## 1.3. json3.3. json
- Yêu cầu: Dựa trên Serialize ở java tại mục 2.2, viết code có sử dụng json (yêu cầu có sử dụng maven)
- Tham khảo: (1)https://www.tutorialspoint.com/json/json_overview.htm, (2) http://www.tutorialspoint.com/json/json_java_example.htm
- Nắm được json là gì, sử dụng java parse json, lấy giá trị, chuyển jsonobject thành string
- Sử dụng thư viện gson để parse trực tiếp 1 string sang 1 object tương ứng
# 2. Kết quả
## 2.1 Exception
### 2.1.1 Tìm hiểu
- Xử lý ngoại lệ (Exception Handling) trong Java là một cơ chế cho phép các lỗi (Exceptions) xảy ra trong quá trình thực thi chương trình được kiểm soát và xử lý một cách cẩn thận, tránh làm gián đoạn chương trình hoặc gây ra tình trạng không ổn định.
- Hệ thống phân cấp các lớp Exception:
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/aa5b067b-4dda-4b48-9ee3-911bb6baed79)
- Phân loại exception:
  - Checked Exception (Ngoại lệ kiểm tra): Các lớp kế thừa trực tiếp lớp Throwable ngoại trừ RuntimeException và Error được gọi là các ngoại lệ được kiểm tra. Ví dụ: IOException, SQLException, v.v. Các ngoại lệ đã kiểm tra được kiểm tra tại thời điểm biên dịch.
  - Unchecked Exception (Ngoại lệ không đượckiểm tra): Các lớp kế thừa RuntimeException được gọi là ngoại lệ không được kiểm tra. Ví dụ: ArithmeticException, NullPulumException, ArrayIndexOutOfBoundsException, v.v. Các ngoại lệ không được kiểm tra không được kiểm tra tại thời điểm biên dịch, nhưng chúng được kiểm tra trong thời gian chạy.
- Các từ khóa  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/0512ed89-fe87-4ffe-9215-56322a24303d)
- Xử lý ngoại lệ với ghi đè:
  - Nếu phương thức của lớp cha không khai báo một ngoại lệ thì phương thức bị ghi đè của lớp con không thể khai báo ngoại lệ được kiểm tra nhưng nó có thể khai báo ngoại lệ không được kiểm tra.
  - Nếu phương thức của lớp cha khai báo một ngoại lệ, phương thức bị ghi đè của lớp con có thể khai báo ngoại lệ của lớp con tương tự hoặc không có ngoại lệ nhưng không thể khai báo ngoại lệ cha.
### 2.1.2 Thực hiện [package Exception]
- Checked Exception: file IoException.java
- Unchecked Exception: file Calc.java và file ArithemeticEx.java
