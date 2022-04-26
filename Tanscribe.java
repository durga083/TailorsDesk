package com.amazonaws.demos.polly;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//READ JSON and Get TEXT out of it
public class Tanscribe {
    String returnString = null;


    public Tanscribe() {
    }

    public String getText() {
        //Creating a JSONParser object
        JSONParser jsonParser = new JSONParser();
        try {
            //Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("/Users/kumarid/Downloads/ExperimentASKOutput.json"));
            String jobName = (String) jsonObject.get("jobName");
//            String first_name = (String) jsonObject.get("First_Name");
//            String last_name = (String) jsonObject.get("Last_Name");
//            String date_of_birth = (String) jsonObject.get("Date_Of_Birth");
//            String place_of_birth = (String) jsonObject.get("Place_Of_Birth");
//            String country = (String) jsonObject.get("Country");
            //Forming URL
            System.out.println("Contents of the JSON are: ");
            System.out.println("jobName :" + jobName);
//            System.out.println("First name: "+first_name);
//            System.out.println("Last name: "+last_name);
//            System.out.println("Date of birth: "+date_of_birth);
//            System.out.println("Place of birth: "+place_of_birth);
//            System.out.println("Country: "+country);
//            System.out.println(" ");


            jsonObject.keySet().forEach(keyStr ->
            {
                Object keyvalue = jsonObject.get(keyStr);
                System.out.println("key: " + keyStr + " value: " + keyvalue);

                if (keyStr.equals("results")) {
                    JSONObject insideJsonObject = (JSONObject) keyvalue;
                    Object keyValue1 = insideJsonObject.get("transcripts");
                    System.out.println("transcripts:::: :" + keyValue1);
                    JSONArray objects = (JSONArray) keyValue1;
                    System.out.println("2222" + objects.get(0));
                    JSONObject jsonObject5 = (JSONObject) objects.get(0);
                    Object keyvalue8 = jsonObject5.get("transcript");
                    System.out.println("keyvalue8:::" + keyvalue8);
                    returnString = (String) keyvalue8;
//                    insideJsonObject2[0].keySet().forEach(keyStr4 ->
//                    {
//                        Object keyvalue4 = insideJsonObject2[0].get(keyStr4);
//                        System.out.println("key: "+ keyStr4 + " value: " + keyvalue4);
//                    });

                }
                ;

                //for nested objects iteration if required
                //if (keyvalue instanceof JSONObject)
                //    printJsonObject((JSONObject)keyvalue);
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    /*
    public static void main(String args[]) {
        //Creating a JSONParser object
        JSONParser jsonParser = new JSONParser();
        try {
            //Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("/Users/kumarid/DurgaKumariBackUp/LatestCode/EnterpriseAudit/polly/src/main/resources/Durgaleaptesttranscribe.json"));
            String jobName = (String) jsonObject.get("jobName");
//            String first_name = (String) jsonObject.get("First_Name");
//            String last_name = (String) jsonObject.get("Last_Name");
//            String date_of_birth = (String) jsonObject.get("Date_Of_Birth");
//            String place_of_birth = (String) jsonObject.get("Place_Of_Birth");
//            String country = (String) jsonObject.get("Country");
            //Forming URL
            System.out.println("Contents of the JSON are: ");
            System.out.println("jobName :"+jobName);
//            System.out.println("First name: "+first_name);
//            System.out.println("Last name: "+last_name);
//            System.out.println("Date of birth: "+date_of_birth);
//            System.out.println("Place of birth: "+place_of_birth);
//            System.out.println("Country: "+country);
//            System.out.println(" ");




            jsonObject.keySet().forEach(keyStr ->
            {
                Object keyvalue = jsonObject.get(keyStr);
                System.out.println("key: "+ keyStr + " value: " + keyvalue);

                if(keyStr.equals("results")){
                    JSONObject insideJsonObject = (JSONObject) keyvalue;
                    Object keyValue1 = insideJsonObject.get("transcripts");
                    System.out.println("transcripts:::: :"+keyValue1);
                    JSONArray objects = (JSONArray) keyValue1;
                    System.out.println("2222"+objects.get(0));
                    JSONObject jsonObject5 = (JSONObject) objects.get(0);
                    Object keyvalue8 = jsonObject5.get("transcript");
                    System.out.println("keyvalue8:::"+keyvalue8);
//                    insideJsonObject2[0].keySet().forEach(keyStr4 ->
//                    {
//                        Object keyvalue4 = insideJsonObject2[0].get(keyStr4);
//                        System.out.println("key: "+ keyStr4 + " value: " + keyvalue4);
//                    });

                };

                //for nested objects iteration if required
                //if (keyvalue instanceof JSONObject)
                //    printJsonObject((JSONObject)keyvalue);
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    */
        return returnString;

    }
}
