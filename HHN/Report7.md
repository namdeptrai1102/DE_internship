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
- Up load 3 quyển sách lên hdfs:
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/f2004af1-5962-4e59-8137-acaccf7b0a32)
- Chạy mapreduce với câu lệnh: yarn jar ~/hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.2.jar wordcount "/user/hadoop/books/*" output
- Kết quả:
  ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/98109b70-cef1-4e63-85f3-69e0cf43b757) ![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/13e46479-5951-4c3b-8123-9e0b8b98d2e1)


  
