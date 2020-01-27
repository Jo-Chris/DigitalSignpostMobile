package com.example.digitalsignpostmobile.classes;

import android.content.Context;

import com.example.digitalsignpostmobile.database.DAOs.SignDAO;
import com.example.digitalsignpostmobile.database.DAOs.SignDataDAO;
import com.example.digitalsignpostmobile.database.DAOs.SignImageDAO;
import com.example.digitalsignpostmobile.database.SignDatabase;
import com.example.digitalsignpostmobile.models.Sign;
import com.example.digitalsignpostmobile.models.SignData;
import com.example.digitalsignpostmobile.models.SignImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Responsible for handling JSON Response returned by the Flask Server
 */

public class SignMapper {

    private int signAmount = 0;
    private JSONArray signs = null;
    private Context context;
    private SignDAO signDAO;
    private SignDataDAO signDataDAO;
    private SignImageDAO signImageDAO;

    public SignMapper(Context context){
        this.context = context;

        initDAOs();
    }

    private void initDAOs(){
        signDAO = SignDatabase.getInstance(context).signDao();
        signDataDAO = SignDatabase.getInstance(context).signDataDAO();
        signImageDAO = SignDatabase.getInstance(context).signImageDAO();
    }


    public void prepareJSONData(String data) throws JSONException {
        System.out.println("MAPPER GOT " + data);

        JSONObject jsonObject = new JSONObject(data);

        // get the top signs
        signs = (JSONArray) jsonObject.get("signs");
        // this will get us the first Sign!
        //System.out.println(signs.getJSONObject(0));
        // determine, how many objects there are!
        signAmount = countJSONObjects(signs);

    }

    public int getAndSaveSigns() throws JSONException {

        signImageDAO.insert(new SignImage("Neues Wanderschild", 4, 123, 123));

        for (int i = 0; i < signAmount; i++) {
            JSONObject targetRow = signs.getJSONObject(i);
            signDAO.insert(new Sign("Wanderschild " + i, "test", 3, "test", signImageDAO.getAll().size()));

            JSONArray lineArray = targetRow.getJSONArray("lines");

            for (int k = 0; k < lineArray.length(); k++) {
                JSONObject singleRow = lineArray.getJSONObject(k);
                signDataDAO.insert(new SignData(
                        singleRow.get("target").toString(),
                        singleRow.get("duration").toString(),
                        singleRow.get("pathNumber").toString(),
                        signDAO.getAll().size()));
            }
        }

        return signImageDAO.getAll().size();
    }

    private int countJSONObjects(JSONArray obj){
        int counter = 0;
        for (int i = 0; i < 10; i++) {
            try {
                obj.getJSONObject(i);
                counter++;
            }catch (JSONException e){
                return counter;
            }
        }
        return counter;
    }

    public int getAmountOfDetectedSigns(){
        return signAmount;
    }

}
