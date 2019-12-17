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
import org.lambdas.model.Consumer;

import java.util.ArrayList;
import java.util.List;

public class GetConsumersFunction implements RequestHandler<Object, List<Consumer>> {

    private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "consumers-lambda";
    private Regions REGION = Regions.EU_WEST_1;

    @Override
    public List<Consumer> handleRequest(Object event, Context context) {
        this.initDynamoDbClient();
        List<Consumer> resultItems = new ArrayList<>();
        ItemCollection<ScanOutcome> items = this.dynamoDb.getTable(DYNAMODB_TABLE_NAME).scan();

        try {
            for (Item item : items) {
                resultItems.add(new Consumer(
                        item.getString("cid"),
                        item.getString("pid"),
                        item.getBoolean("known_your_customer"),
                        item.getBoolean("is_blocked")));
            }

        } catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }

        return resultItems;
    }

    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }
}
