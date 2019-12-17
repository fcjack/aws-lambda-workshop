const AWS = require('aws-sdk');
AWS.config.update({ region: `eu-west-1` });
const dynamodb = new AWS.DynamoDB.DocumentClient();

exports.handler = async (event) => {
    const params = {
        TableName: "consumers-lambda"
    }

    const result = await dynamodb.scan(params).promise();
    return {
        "statusCode": 200,
        "headers": {
            "Content-Type": "application/json"
        },
        "body": JSON.stringify(result.Items)
    };
};
