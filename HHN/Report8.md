# BUILDING SCALABLE DATA PIPELINES WITH KAFKA
# Introduction
- Đc viết bằng java và scale ,ban đầu đc sử dụng để track user's action trên LinkedIn\
- Nền tảng phát trực tuyến sự kiện được phân phối cho phép bạn đọc, ghi, lưu trữ và xử lý các sự kiện (còn được gọi là bản ghi hoặc thông báo trong tài liệu) trên nhiều máy.  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/4652bf80-2032-4bf1-b874-858a6822d7b7)
- Kafka đc sử dụng chính trong building data pipelines and implementing streaming solutions.
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/7aba2bb7-b42a-4169-8ce1-7b391c058aff)
# Characteristic of Distributed Systems
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/33097ffd-401e-46d6-bf6f-5bdd0b57b8fc)
# Messaging Patterns
- Kafkalà trung gian di chuyển các records từ ứng dụng này sang ứng dụng khác và tách chúng ra khỏi nhau.
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/e4726783-1ef9-4f56-9107-b913b7ad9dcb)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/3aacb7ce-7842-4e5a-a942-ce600aa54076)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/ed4cbf41-a518-43bb-8c83-2e82e2ddfb8c)
- Producer of data kbiet consumer of data là ai, cũng kbiet khi nào data được dùng => tách rời producers và consumers (asynchronous messaging) vs 2 pattens:
- Publish subscribe (Pub-Sub): Producer tạo data và publishes lên 1 channel => message có thể đc nhiều consumers sử dụng và luôn được gửi đến theo thứ tự mà chúng đã đc publish.  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/f2a960fe-4e2f-4f92-a9e9-579ba23bf763)
