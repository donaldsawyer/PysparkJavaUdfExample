# PysparkJavaUdfExample
Example of wrapping a java function in a java spark udf, then calling from pyspark

## Purpose
The purpose was to demonstrate the use case of calling a java library in pyspark through a UDF.
1. Start with Java function (`triple()`) in a library (`Multiples`)
1. Wrap the Java function in a Java Spark UDF (`TripleUdf`)
1. Call the UDF from pyspark.

## Steps to Build and Run
This is a quickly-built POC, so it's not ready for repeatable execution. Follow the steps below to run the example and see how the functionality works.

1. Build and install the `Multiples` library as a jar. `mvn package install`
1. `Multiples` is referenced by the UDF in the UDF's `pom.xml`, so `mvn install` is required.  
  ```xml
  <dependency>
    <groupId>org.example.functions</groupId>
    <artifactId>Multiples</artifactId>
    <version>1.0-SNAPSHOT</version>
  </dependency>
  ```
1. Build the `MultipleUdf` project, which will create a fat jar in the `target` directory, `MultipleUdf-1.0-SNAPSHOT.jar`.
1. Open up the pyspark shell `, referencing the additional `jar` in the classpath.  
  `pyspark --jars /Users/donaldsawyer/git/PysparkJavaUdfExample/MultipleUdf/target/MultipleUdf-1.0-SNAPSHOT.jar`
1. Run the pyspark commands that are in `executePythonJavaUdf.py`
   ```python
	from pyspark.sql import functions as F
	from pyspark.sql.types import DoubleType
		
	spark.udf.registerJavaFunction("triple", "TripleUdf")
	df = spark.createDataFrame([0.0, 4.111, -4.5], DoubleType()).toDF("value")
	df.withColumn("tripled", F.expr("triple(value)")).show()
   ```
 
  
