import org.apache.spark.sql.api.java.UDF1;

public class TripleUdf implements UDF1<Double, Double> {

    @Override
    public Double call(Double aDouble) throws Exception {
        return Multiple.triple(aDouble);
    }
}