# 1. Cài đặt hadoop theo mô hình:  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/1c403a12-a052-4411-9c0f-5d6fcbb42d6f)
- Node master (94-234):  
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/f4884057-1505-44a6-9960-8288229a3642)
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/4ec72224-4bb4-4437-98ee-6c2ac6af3a31)
- Data node (93-113):  
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/cb49d0bf-e6df-41c3-b8ca-0275694e928e)
- Data node (92-26):  
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/14e06512-a17f-409e-af38-49a5a3c608d1)
- Web interface:  
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/5f23f807-89aa-49c2-a1b2-c43e42c15527)
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/eca67662-6ac5-4ee1-8e53-a8fc919b5214)
# 2. Chạy wordcount với hadoop mapreduce
- Up load sách alice.txt lên hdfs:  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/7296619e-b792-48a0-82d1-e68a6238875c)
- Chạy mapreduce với câu lệnh: hadoop jar word_count/MapReduceExample-1.0-SNAPSHOT.jar demo.WC_Runner hdfs://10.5.94.234:8023/alice.txt hdfs://10.5.94.234:8023/r_output
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/587be445-97f5-4921-81d0-8f6e0a6fab9b)
- Kết quả (1 phần):  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/e55f8bd6-49da-4c90-a9b8-9fbb71763784)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/aeb3c9a2-d349-4d1a-a645-74215796b27c)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/130af97e-915c-43d2-b46c-a4237b13326d)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/db702d1b-f2ed-47df-b3db-823d67075d83)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/9829f36b-fbbd-4352-99ad-938db21be1ca)
# 3. Chạy wordcount với spark
- Tạo maven project:  
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/6abaf494-030c-4ad7-84bd-f5f70195cace)
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/fe29d15a-7934-44b9-98ab-91c4e79582ac)
- Submit job:  
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/8c4625df-4216-4950-a272-73c61e9f5186)
- Kết quả:  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/c9d28054-3795-44ec-aa99-0a31353eaeb4)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/39934609-e6da-433c-a3a7-f7c096e28fd1)
### Sử dụng spark-shell cho kết quả tương tự
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/e2381c2e-3eb3-47f7-b493-7e04d4f3edaf)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/a0cd2d0f-085e-411d-beea-c9efc1663564)
# 4. Sinh dữ liệu người dùng với pyspark
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/3c4f28eb-4935-4d9b-b777-22614abf418a)  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/ad9ae7cd-dec4-4a89-bc48-ea5193183c1b)
# 5. Thống kê và visualize
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/abf22686-6de7-447f-a19e-351e062ec906)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/582ab3ed-51ea-4033-b762-26d7dd99ce7d)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/b7c11028-7134-4d88-abaf-eb5d42cc7e73)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/f97b6e48-7267-4c97-a303-5ba1d53cc140)
# 6. Đọc ghi file parquet với java spark submit  
- Build maven project:  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/cfd61eb0-a41b-481c-92f7-dced792413d8)
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/7156e0a5-9f2d-4d40-9e14-4f7f873aa0d4)
- Submit job:  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/f4ab66a2-42f7-4708-a49f-448aa0731ab4)
- Result:  
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/c0b16ae7-30f4-4c2e-b2e3-5ac664fe10c6)




  
