package com.amazonaws.lambda.demo;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {
	final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
    @Override
    public String handleRequest(Object input, Context context) {
    	String table_name="Person";
        context.getLogger().log("Input: This is triggering the finctionT ");
        Map<String,AttributeValue> map = new HashMap<String,AttributeValue>();
        String firstName = System.getenv("firstName");
        String secondName = System.getenv("secondName");
        map.put("FirstName", new AttributeValue(firstName));
        map.put("LastName", new AttributeValue(secondName));
        context.getLogger().log("The request map is-------->"+map.toString());
       PutItemResult result  =  ddb.putItem(table_name,map,ReturnValue.ALL_OLD.toString());
       context.getLogger().log("Executed Successfully from Lambda--->"+result.getAttributes());
        // TODO: implement your handler
        return "result.getAttributes().toString()";
    }

}
