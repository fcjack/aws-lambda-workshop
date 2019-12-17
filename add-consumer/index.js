const AWS = require('aws-sdk');
AWS.config.update({ region: `eu-west-1` });
const dynamodb = new AWS.DynamoDB.DocumentClient();

exports.handler = async (event) => {
    try {
        var params = {
            TableName: "consumers-lambda",
            Item: JSON.parse(event.body)
        };

        await dynamodb.put(params).promise();
    } catch (err) {
        console.log(err);
    }

    return {
        'statusCode': 201
    };
};
