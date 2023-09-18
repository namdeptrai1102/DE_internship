package org.apache.parquet;

import org.apache.spark.sql.functions;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.api.java.UDF1;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("Generate and Save Parquet Data (Java)")
                .master("spark://10.5.94.234:7098")
                .getOrCreate();

        // Tạo schema cho DataFrame
        StructType schema = new StructType()
                .add("Name", DataTypes.StringType, false)
                .add("Birthdate", DataTypes.StringType, false)
                .add("Address", DataTypes.StringType, false)
                .add("Gender", DataTypes.StringType, false);

        List<Row> data = new ArrayList<>();

        String[] nameArray = {"Nam", "Trang", "Huy", "Kỳ"};

        // Khởi tạo mảng theo định dạng ngày (Date)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String[] dateArray = new String[4];
        try {
            dateArray[0] = "28/12/2002";
            dateArray[1] = "02/08/2002";
            dateArray[2] = "21/07/2001";
            dateArray[3] = "20/09/2001";

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] addressArray = new String[4];
        addressArray[0] = "Nam";
        addressArray[1] = "Trang";
        addressArray[2] = "Huy";
        addressArray[3] = "Kỳ";
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            String name = nameArray[random.nextInt(4)];
            String birthdate = dateArray[random.nextInt(4)];
            String address = addressArray[random.nextInt(4)];
            String gender = (random.nextInt(2) == 0) ? "Male" : "Female";
            data.add(RowFactory.create(name, birthdate, address, gender));
        }

        // Tạo DataFrame từ danh sách dữ liệu và schema
        Dataset<Row> df = spark.createDataFrame(data, schema);

        // Định nghĩa UDF để tính toán tuổi từ chuỗi ngày tháng năm
        UserDefinedFunction calculateAgeUDF = functions.udf(
                new UDF1<String, Integer>() {
                    @Override
                    public Integer call(String birthdateString) throws Exception {
                        Date birthdate = dateFormat.parse(birthdateString);
                        // Tính toán tuổi dựa trên ngày tháng năm sinh
                        // Đây là một ví dụ đơn giản, bạn có thể thay đổi cách tính tuổi theo nhu cầu của bạn
                        long ageInMillis = new Date().getTime() - birthdate.getTime();
                        int age = (int) (ageInMillis / 1000 / 60 / 60 / 24 / 365); // Tuổi tính bằng năm
                        return age;
                    }
                },
                DataTypes.IntegerType
        );

        // Sử dụng UDF để tính toán và thêm cột "Age" vào DataFrame
        df = df.withColumn("Age", calculateAgeUDF.apply(df.col("Birthdate")));

        // Lưu DataFrame thành tệp Parquet
        String parquetOutputPath = "hdfs://10.5.94.234:8023/output_java_parquet";
        df.write().mode(SaveMode.Overwrite).parquet(parquetOutputPath);

        // Đọc tệp Parquet và chuyển thành DataFrame
        String parquetFilePath = "hdfs://10.5.94.234:8023/output_java_parquet";
        Dataset<Row> readDf = spark.read().parquet(parquetFilePath);

        // Hiển thị dữ liệu trong DataFrame
        readDf.show();

        spark.stop();
    }
}
