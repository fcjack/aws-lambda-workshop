package org.lambdas;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lambdas.model.ApplicationResponse;
import org.lambdas.model.Consumer;

import java.util.ArrayList;
import java.util.List;

public class GetConsumersFunction implements RequestHandler<Object, ApplicationResponse> {

    private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "consumers-lambda";
    private Regions REGION = Regions.EU_WEST_1;
    private static final Logger LOGGER = LogManager.getLogger(GetConsumersFunction.class);

    @Override
    public ApplicationResponse handleRequest(Object event, Context context) {
        this.initDynamoDbClient();
        List<Consumer> resultItems = new ArrayList<>();
        ItemCollection<ScanOutcome> items = this.dynamoDb.getTable(DYNAMODB_TABLE_NAME).scan();

        try {
            LOGGER.info("Received data from DynamoDB - Total elements", items.getAccumulatedItemCount());
            for (Item item : items) {
                resultItems.add(new Consumer(
                        item.getString("cid"),
                        item.getString("pid"),
                        item.getBoolean("known_your_customer"),
                        item.getBoolean("is_blocked")));
            }

            LOGGER.info("PROCESSED ITEMS");
        } catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }

        return new ApplicationResponse(200, new Gson().toJson(resultItems));
    }

    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }
}
