package com.example.digitalsignpostmobile.classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapSerializer {

    public static String saveToInternalStorage(Context c, Bitmap bitmapImage, String filename){
        FileOutputStream fos = null;
        try {
            fos = c.openFileOutput(filename, Context.MODE_PRIVATE);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "saved as: " + filename;
    }


    public static Bitmap loadImageFromStorage(Context c, String filename) {
        try {
            File filePath = c.getFileStreamPath(filename);
            FileInputStream fi = new FileInputStream(filePath);
            return BitmapFactory.decodeStream(fi);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return null;

    }
}

