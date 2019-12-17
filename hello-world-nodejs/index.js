exports.handler = async (event) => { 
    const response = {
        statusCode: 200,
        body: JSON.stringify(`Welcome ${event.firstName} ${event.lastName} to the world of AWS Lambda!`),
    };
    return response;
};