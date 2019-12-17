exports.handler = async (event) => { 
    const response = {
        statusCode: 200,
        body: JSON.stringify(`Welcome ${event.first_name} ${event.last_name} to the world of AWS Lambda!`),
    };
    return response;
};