const AWS = require('aws-sdk');
AWS.config.update({ region: `eu-west-1` });
const dynamodb = new AWS.DynamoDB.DocumentClient();

exports.handler = async (event) => {
    console.log(event.path)
    try {
        var params = {
            TableName: "consumers-lambda",
            Key: {
                "cid": event.pathParameters.cid
            }
        };
        await dynamodb.delete(params).promise()
    } catch (err) {
        console.log(err)
        return { "statusCode": 500 }
    }

    return { "statusCode": 204 };
};
