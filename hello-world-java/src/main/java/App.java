import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class App implements RequestHandler<Map, String> {

    @Override
    public String handleRequest(Map map, Context context) {
        return String.format("Welcome %s %s to the world of AWS Lambda!", map.get("firstName"), map.get("lastName"));
    }
}
