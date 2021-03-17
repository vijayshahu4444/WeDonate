package com.app.wedonate2.datamodel;

public class RequestDatamodel {

    private String message;
    private String imgurl;

    public RequestDatamodel(String message, String imgurl) {
        this.message = message;
        this.imgurl = imgurl;
    }

    public String getMessage() {
        return message;
    }

    public String getImgurl() {
        return imgurl;
    }
}

