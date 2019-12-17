import json
import boto3

def lambda_handler(event, context):
    dynamodb = boto3.resource('dynamodb', region_name='eu-west-1')
    table = dynamodb.Table('consumers-lambda')
    
    table.delete_item(
        Key={
            'cid': event['pathParameters']['cid']
            }
        )
    return {
        'statusCode': 204
    }
