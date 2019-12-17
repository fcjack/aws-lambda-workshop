import json
import boto3

def lambda_handler(event, context):
    dynamodb = boto3.resource('dynamodb', region_name='eu-west-1')
    table = dynamodb.Table('consumers-lambda')
    
    response = table.scan()
    return {
        'statusCode': 200,
        'headers': {
            'Content-Type': 'application/json'
        },
        'body':json.dumps(response['Items'], indent=4)
        }
