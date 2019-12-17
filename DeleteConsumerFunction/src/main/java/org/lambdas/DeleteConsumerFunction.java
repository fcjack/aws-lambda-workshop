package org.lambdas;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.KeyAttribute;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lambdas.model.ApplicationResponse;

import java.util.HashMap;
import java.util.Map;

public class DeleteConsumerFunction implements RequestHandler<Map, ApplicationResponse> {

    private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "consumers-lambda";
    private Regions REGION = Regions.EU_WEST_1;
    private static final Logger LOGGER = LogManager.getLogger(DeleteConsumerFunction.class);

    public ApplicationResponse handleRequest(Map event, Context context) {
        this.initDynamoDbClient();

        LOGGER.info("==========================");
        LOGGER.info(event.get("pathParameters"));
        LOGGER.info("==========================");
        String pathParameters = new Gson().fromJson(event.get("pathParameters").toString(), Map.class).get("cid").toString();

        LOGGER.info("==========================");
        LOGGER.info(pathParameters);
        LOGGER.info("==========================");
        deleteConsumer(pathParameters);
        ApplicationResponse consumerResponse = new ApplicationResponse();
        consumerResponse.setStatusCode(204);
        return consumerResponse;
    }

    private void deleteConsumer(String cid) {
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                .withPrimaryKey(new PrimaryKey("cid", cid));
        this.dynamoDb.getTable(DYNAMODB_TABLE_NAME).deleteItem(deleteItemSpec);
    }

    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }
}
