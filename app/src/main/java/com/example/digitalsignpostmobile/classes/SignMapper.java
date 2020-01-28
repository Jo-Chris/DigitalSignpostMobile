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

        signs = (JSONArray) jsonObject.get("signs");

        signAmount = countJSONObjects(signs);
    }

    public void getAndSaveSigns() throws JSONException {

        int counter = signImageDAO.getAll().size();
        counter++;

        signImageDAO.insert(new SignImage("Bild " + counter, signAmount, 123, 123));

        System.out.println(signs);
        System.out.println(signs.get(0));
        System.out.println(signs.getJSONObject(0).getString("direction"));

        for (int i = 0; i < signAmount; i++) {
            JSONObject targetRow = signs.getJSONObject(i);
            String resOrg = signs.getJSONObject(0).getString("responsibleOrganisation");
            int direction = Integer.parseInt(signs.getJSONObject(0).getString("direction"));
            signDAO.insert(
                    new Sign("Wanderschild " + (i+1),
                            direction == 0 ? "Links" : "Rechts",
                            targetRow.getJSONArray("lines").length(),
                            resOrg.equals("") ? "Nein" : "Ja",
                            signImageDAO.getAll().size()
                    )
            );

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
