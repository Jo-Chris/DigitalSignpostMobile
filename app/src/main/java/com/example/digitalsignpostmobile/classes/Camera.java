package com.example.digitalsignpostmobile.classes;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;


public class Camera {

    public static boolean checkCamera(Activity c, int permissionCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (c.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                c.requestPermissions(permission, permissionCode);
            } else return true;

        } else return true;

        return false;
    }

    public static void openCamera(Activity c, int captureCode){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Neues Wanderschild");
        Uri image_uri = c.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        // Camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        c.startActivityForResult(cameraIntent, captureCode);
    }
}
