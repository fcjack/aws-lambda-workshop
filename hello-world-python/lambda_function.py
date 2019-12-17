import json

def lambda_handler(event, context):
    message = 'Hello {} {}, congrats on your first lambda fn!'.format(event['first_name'], event['last_name'])
    return {
        'statusCode': 200,
        'body': json.dumps(message)
    }