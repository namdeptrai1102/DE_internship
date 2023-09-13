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




  
