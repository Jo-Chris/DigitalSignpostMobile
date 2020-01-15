package com.example.digitalsignpostmobile.model;

import java.io.Serializable;

class SignData implements Serializable {

    private String target;
    private String duration;
    private String pathNumber;
    private String resOrg;

    public SignData(String target, String duration, String pathNumber, String resOrg){
        this.target = target;
        this.duration = duration;
        this.pathNumber = pathNumber;
        this.resOrg = resOrg;
    }

    public SignData(String target, String duration, String resOrg){
        this.target = target;
        this.duration = duration;
        this.resOrg = resOrg;
    }

    public String getFormattedOutput(){
        return target + " " + duration + " " + pathNumber;
    }

    public String getTarget() {
        return target;
    }


}
