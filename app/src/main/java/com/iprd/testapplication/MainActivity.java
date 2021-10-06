package com.iprd.testapplication;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.json.JSONException;
import org.json.JSONObject;
import com.iprd.intent_proto.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 0 ;
    public static int NEW_REQUEST_CODE = 401;
    public static int EDIT_REQUEST_CODE = 402;
    public static final String BUNDLE_INPUT_JSON = "input_json";
    public static final String BUNDLE_OUTPUT_JSON = "output_json";
    public static final String BUNDLE_KEY_USERNAME = "username";
    public static final String BUNDLE_KEY_SHORT_ID = "shortId";
    public static final String BUNDLE_KEY_DEMOGRAPHIC = "demographics";
    public static final String BUNDLE_KEY_NUMBER_OF_FACES = "noFaces";
    public static final String BUNDLE_KEY_NUMBER_OF_HOUSES = "noHouses";
    public static final String BUNDLE_KEY_LOCATION_CAPTURED = "locationCaptured";

    private String shortId = "51GY4AWC5L76";
    private String name = "apra2793@gmail.com";
    String inputJson = "{\"familyId\": \"789\",\"hcwUserName\": \"nks@apra.in\",\"primaryContactPhone\": \"\",\"openCampLinkId\":\"\",\"familySurveyResponse\":\"\",\"familyMembers\": [{\"memberId\": \"23\",\"name\": \"Matt\",\"dob\": \"1970-05-26\",\"gender\": \"M\",\"status\": \"New\"},{\"memberId\": \"12\",\"name\": \"Roma\",\"dob\": \"1997-07-26\",\"gender\": \"F\",\"status\": \"New\"}]};\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNew = findViewById(R.id.btnNew);
        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnRecall = findViewById(R.id.btnRecall);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
//
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CODE);
//        } else {
//
//        }
        btnNew.setOnClickListener(v -> {
            openSmartHealthAppWithCampaignDetailsUsingMessagingProtocolBloodDraw();
        });

        btnEdit.setOnClickListener(v -> {
            openSmartHealthAppInEditMode();
        });

        btnRecall.setOnClickListener(v -> {
            openSmartHealthAppInRecallMode();
        });

    }

    void openSmartHealthApp() {
       /* Intent intent = new Intent("HOME_SCREEN_IPRD");
        intent.putExtra(BUNDLE_INPUT_JSON, inputJson);
        intent.setComponent(new ComponentName("com.iprd.opencamplink", "com.iprd.opencamplink.records.OpenCampLinkHomeActivity"));
        startActivityForResult(intent, NEW_REQUEST_CODE);*/

        Intent sendIntent = new Intent();
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        sendIntent.putExtra(BUNDLE_INPUT_JSON, inputJson);
        sendIntent.setAction("HOME_SCREEN_IPRD");
        sendIntent.setComponent(new ComponentName("com.iprd.opencamplink", "com.iprd.opencamplink.records.OpenCampLinkHomeActivity"));
        Intent chooser = Intent.createChooser(sendIntent, "IPRD OCL");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(chooser, NEW_REQUEST_CODE);
        }
    }


    void openSmartHealthAppWithCampaignDetailsUsingMessagingProtocolBloodDraw() {
        KeyTypeValue keyTypeValue = new KeyTypeValue("key", "type", "value");
        ArrayList<KeyTypeValue> udf = new ArrayList<>();
        udf.add(keyTypeValue);
        ArrayList<Integer> verticals = new ArrayList<>();
        verticals.add(2);
        CampaignDataClass campaignDataClass =
                new CampaignDataClassBuilder()
                        .setId("6ecb0566-7006-4382-9cdc-202d9010858a")
                        .setName("Oyo State June 2021 Health Campaign")
                        .setUrl("https://health.oyostate.gov.ng/tomotiya/")
                        .setVerticals(verticals)
                        .setLocationPrecision(2)
                        .setTimePrecision(5)
                        .setUdf(udf)
                        .build();

        FamilyMemberDataClass[] familyMemberDataClasses = new FamilyMemberDataClass[]{
                new FamilyMemberDataBuilder()
                        .setDob("1993-09-12")
                        .setGender("M")
                        .setHead(true)
                        .setInputOpenCampLinkId("ABCDEFGH")
                        .setMemberID("1234")
                        .setFirstName("kash")
                        .setLastName("last")
                        .setStatus(FamilyMemberDataClass.Status.New)
                        .build()
        };

        BloodDrawMessageRequest bloodDrawMessageRequest =
                new BloodDrawMessageRequestBuilder()
                        .setCampaign(campaignDataClass)
                        .setFamilyID("tempID")
                        .setHcwUserName("tempUser")
                        .setCountryCode("+91")
                        .setPhoneNumber("9712528223")
                        .setVerificationMethod("BIOMETRIC")
                        .setFamilyMembers(familyMemberDataClasses)
                        .build();

        Intent sendIntent = new Intent();
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        sendIntent.putExtra(BUNDLE_INPUT_JSON, bloodDrawMessageRequest.toJsonString());
        sendIntent.setAction("HOME_SCREEN_IPRD");
        sendIntent.setComponent(new ComponentName("com.iprd.opencamplink", "com.iprd.opencamplink.records.OpenCampLinkHomeActivity"));
        Intent chooser = Intent.createChooser(sendIntent, "IPRD OCL");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(chooser, NEW_REQUEST_CODE);
        }
    }

    void openSmartHealthAppWithCampaignDetailsUsingMessagingProtocol() {
        KeyTypeValue keyTypeValue = new KeyTypeValue("key", "type", "value");
        ArrayList<KeyTypeValue> udf = new ArrayList<>();
        udf.add(keyTypeValue);
        ArrayList<Integer> verticals = new ArrayList<>();
        verticals.add(2);
        CampaignDataClass campaignDataClass =
                new CampaignDataClassBuilder()
                        .setId("6ecb0566-7006-4382-9cdc-202d9010858a")
                        .setName("Oyo State June 2021 Health Campaign")
                        .setUrl("https://health.oyostate.gov.ng/tomotiya/")
                        .setVerticals(verticals)
                        .setLocationPrecision(2)
                        .setTimePrecision(5)
                        .setUdf(udf)
                        .build();

        FamilyMemberDataClass[] familyMemberDataClasses = new FamilyMemberDataClass[]{
                new FamilyMemberDataBuilder()
                        .setDob("1993-09-12")
                        .setGender("M")
                        .setHead(true)
                        .setInputOpenCampLinkId("ABdsad")
                        .setMemberID("1234")
                        .setFirstName("kash")
                        .setLastName("last")
                        .setStatus(FamilyMemberDataClass.Status.New)
                        .build()
        };

        FamilySurveyMessageRequest familySurveyMessageRequest =
                new FamilySurveyMessageRequestBuilder()
                        .setCampaign(campaignDataClass)
                        .setFamilyID("tempID")
                        .setHcwUserName("tempUser")
                        .setPrimaryContactPhone("123456")
                        .setVerificationMethod("BIOMETRIC")
                        .setFamilyMembers(familyMemberDataClasses)
                        .build();

        Intent sendIntent = new Intent();
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        sendIntent.putExtra(BUNDLE_INPUT_JSON, familySurveyMessageRequest.toJsonString());
        sendIntent.setAction("HOME_SCREEN_IPRD");
        sendIntent.setComponent(new ComponentName("com.iprd.opencamplink", "com.iprd.opencamplink.records.OpenCampLinkHomeActivity"));
        Intent chooser = Intent.createChooser(sendIntent, "IPRD OCL");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(chooser, NEW_REQUEST_CODE);
        }
    }

    void openSmartHealthAppWithCampaignDetails() {
        inputJson = "{\n" +
                "  \"campaign\": {\n" +
                "    \"id\": \"6ecb0566-7006-4382-9cdc-202d9010858a\",\n" +
                "    \"name\": \"Oyo State June 2021 Health Campaign\",\n" +
                "    \"verticals\": [\n" +
                "      2,\n" +
                "      5\n" +
                "    ],\n" +
                "    \"url\": \"https://health.oyostate.gov.ng/tomotiya/\",\n" +
                "    \"location_prec\": 3,\n" +
                "    \"time_prec\": 4,\n" +
                "    \"udf\": [\n" +
                "      {\n" +
                "        \"k\": \"PHC ID\",\n" +
                "        \"v\": \"30/25/1/1/1/0020\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"k\": \"PHC Name\",\n" +
                "        \"v\": \"Ifelodun Primary Health Centre\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"k\": \"PHC Location\",\n" +
                "        \"t\": \"url\",\n" +
                "        \"v\": \"https://goo.gl/maps/AyondpDQRZPNJxUi7\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"k\": \"PHC Phone No\",\n" +
                "        \"t\": \"tel\",\n" +
                "        \"v\": \"+234 (0) 803 852 1693\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"familyId\": \"789\",\n" +
                "  \"hcwUserName\": \"nks@apra.in\",\n" +
                "  \"primaryContactPhone\": \"\",\n" +
                "  \"openCampLinkId\": \"\",\n" +
                "  \"familyMembers\": [\n" +
                "    {\n" +
                "      \"memberId\": \"11\",\n" +
                "      \"name\": \"Matt\",\n" +
                "      \"dob\": \"1996-09-25\",\n" +
                "      \"gender\": \"M\",\n" +
                "      \"status\": \"New\",\n" +
                "      \"head\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"memberId\": \"12\",\n" +
                "      \"name\": \"Roma\",\n" +
                "      \"dob\": \"1997-06-12\",\n" +
                "      \"gender\": \"F\",\n" +
                "      \"status\": \"New\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Intent sendIntent = new Intent();
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        sendIntent.putExtra(BUNDLE_INPUT_JSON, inputJson);
        sendIntent.setAction("HOME_SCREEN_IPRD");
        sendIntent.setComponent(new ComponentName("com.iprd.opencamplink", "com.iprd.opencamplink.records.OpenCampLinkHomeActivity"));
        Intent chooser = Intent.createChooser(sendIntent, "IPRD OCL");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(chooser, NEW_REQUEST_CODE);
        }
    }

    void openSmartHealthAppInEditMode() {
        String inputForEdit = "{\"familyId\": \"789\",\"hcwUserName\": \"nks@apra.in\",\"primaryContactPhone\": \"+918923645896\",\"openCampLinkId\":\""+shortId+"\",\"familySurveyResponse\":\"\",\"familyMembers\": [{\"memberId\": \"13\",\"name\": \"Paul\",\"dob\": \"1998-05-03\",\"gender\": \"M\",\"status\": \"New\"},{\"memberId\": \"12\",\"name\": \"Roma\", \"dob\": \"1997-06-12\",\"gender\": \"F\",\"status\": \"Delete\"},{\"memberId\": \"11\",\"name\": \"Matt\",\"dob\": \"1993-09-25\",\"gender\": \"M\",\"status\": \"Update\"}]};\n";

        Intent sendIntent = new Intent();
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        sendIntent.setAction("HOME_SCREEN_IPRD");
        sendIntent.putExtra(BUNDLE_INPUT_JSON,inputForEdit);
        sendIntent.setComponent(new ComponentName("com.iprd.opencamplink", "com.iprd.opencamplink.records.OpenCampLinkHomeActivity"));
        Intent chooser = Intent.createChooser(sendIntent, "IPRD OCL");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(chooser, NEW_REQUEST_CODE);
        }
    }

    void openSmartHealthAppInRecallMode() {
        String inputForRecall = "{\n" +
                "  \"openCampLinkId\": \"\",\n" +
                "  \"hcwUserName\": \"nitink@apra.in\",\n" +
                "  \"primaryContactPhone\": \"+919812345678\",\n" +
                "  \"verificationMethod\": \"ALL\",\n" +
                "  \"familyMembers\": [" +
                "  ]\n" +
                "}";

        Intent sendIntent = new Intent();
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        sendIntent.setAction("RECALL_SCREEN_IPRD");
        sendIntent.putExtra(BUNDLE_INPUT_JSON,inputForRecall);
        sendIntent.setComponent(new ComponentName("com.iprd.opencamplink", "com.iprd.opencamplink.records.OpenCampLinkRecallActivity"));
        Intent chooser = Intent.createChooser(sendIntent, "IPRD OCL");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(chooser, NEW_REQUEST_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Toast.makeText(this,
                    "Output Json : " +
                            data.getExtras().getString(BUNDLE_OUTPUT_JSON),
                    Toast.LENGTH_LONG).show();
            try {
                JSONObject jObject = new JSONObject(data.getExtras().getString(BUNDLE_OUTPUT_JSON));
                if(jObject.getString("resultCode").equals("0")){
                    shortId = jObject.getString("openCampLinkId");
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }

        }

    }
}