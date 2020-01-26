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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

        createSigns();
    }

    /**
     *
     * for 1 Sign:
     *
     * create that sign and create that signdata!
     *
     * @throws JSONException
     */
    private void createSigns() throws JSONException {
        // System.out.println((arr.getJSONObject(0))); -- Gets target line number ONE!
        // System.out.println((signs.getJSONObject(0))); -- Gets the whole sign number one

        signImageDAO.insert(new SignImage("Fotzenweg", 4, 123, 123));

        // iterate over all signs (f.e - 4)
        for (int i = 0; i < signAmount; i++) {
            // for every sign, create a Sign and the corresponding signdata!
            JSONObject targetRow = signs.getJSONObject(i);

            signDAO.insert(new Sign("Sign Number" + i, "test", 3, "test", signImageDAO.getAll().size()));

            for (int j = 0; j < targetRow.length(); j++) {
                System.out.println("Target-Row has length of" + targetRow.length());
                //now get the target line number X
                JSONArray lineArray = targetRow.getJSONArray("lines");
                System.out.println("Line-Array has length of" + lineArray.length());
                for (int k = 0; k < lineArray.length(); k++) {
                    JSONObject singleRow = lineArray.getJSONObject(k);
                    signDataDAO.insert(new SignData(
                            singleRow.get("target").toString(),
                            singleRow.get("duration").toString(),
                            singleRow.get("pathNumber").toString(),
                            i+1));
                }
            }
        }



    }






    /**
     *             signDAO.insert((new Sign(
     *                     "Kufsteiner Scheißweg",
     *                     "duraton",
     *                     arr.length(),
     *                     "Should be res org, but fuck this shit",
     *                     signImageDAO.getAll().size()-1)));
     * @param obj
     * @return
     */


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

}
