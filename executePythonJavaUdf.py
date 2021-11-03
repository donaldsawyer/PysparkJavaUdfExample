from pyspark.sql import functions as F
from pyspark.sql.types import DoubleType

spark.udf.registerJavaFunction("triple", "TripleUdf")
df = spark.createDataFrame([0.0, 4.111, -4.5], DoubleType()).toDF("value")
df.withColumn("tripled", F.expr("triple(value)")).show()
