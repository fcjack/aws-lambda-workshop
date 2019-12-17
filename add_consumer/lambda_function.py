import json
import boto3

def lambda_handler(event, context):
    dynamodb = boto3.resource('dynamodb', region_name='eu-west-1')
    table = dynamodb.Table('consumers-lambda')
    
    response = table.put_item(
       Item=json.loads(event['body'])
    )
    
    print("PutItem succeeded:")
    print(json.dumps(response, indent=4))
    
    return {
        'statusCode': 201,
        'body': "Consumer added"
    }
