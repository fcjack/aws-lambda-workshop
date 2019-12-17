package org.lambdas;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import org.lambdas.model.ApplicationResponse;
import org.lambdas.model.Consumer;

import java.util.Map;

public class AddConsumerFunction implements RequestHandler<Map, ApplicationResponse> {

    private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "consumers-lambda";
    private Regions REGION = Regions.EU_WEST_1;

    @Override
    public ApplicationResponse handleRequest(Map event, Context context) {
        this.initDynamoDbClient();

        persistData(new Gson().fromJson(event.get("body").toString(), Consumer.class));

        ApplicationResponse consumerResponse = new ApplicationResponse();
        consumerResponse.setMessage("Saved Successfully!!!");
        return consumerResponse;
    }

    private PutItemOutcome persistData(Consumer consumer) throws ConditionalCheckFailedException {
        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
                .putItem(
                        new PutItemSpec().withItem(new Item()
                                .withString("cid", consumer.getCid())
                                .withString("pid", consumer.getPid())
                                .withBoolean("known_your_customer", consumer.getKnownYourCustomer())
                                .withBoolean("is_blocked", consumer.getBlocked())));
    }

    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }
}
