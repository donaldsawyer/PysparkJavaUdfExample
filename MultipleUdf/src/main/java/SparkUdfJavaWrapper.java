import org.apache.spark.SparkConf;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.StructType;
import static org.apache.spark.sql.functions.*;
import static org.apache.spark.sql.types.DataTypes.*;


import java.util.Arrays;
import java.util.List;

public class SparkUdfJavaWrapper {
    public static void main(String [] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("Java UDF Wrapper");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();

        List<Row> rows = Arrays.asList(
                RowFactory.create(1, 0.0, 0.0),
                RowFactory.create(2, 4.111, 12.333),
                RowFactory.create(3, -4.5, -13.5));

        StructType schema = createStructType(
                Arrays.asList(
                        createStructField("test_id", IntegerType, false),
                        createStructField("value", DoubleType, false),
                        createStructField("expected", DoubleType, false)));

        spark.udf().register("triple", new TripleUdf(), DoubleType);
        Dataset<Row> df = spark.createDataFrame(rows, schema).
                withColumn("actual", callUDF("triple", col("value")));
        df.show();
    }
}
