package ru.android_studio.gibdd_servicedit;


//public class RequestResult {
//
//    public static String jsonResult = "";
//
//    private static RequestResult instance;
//
//    public static RequestResult getInstance() {
//        if (instance == null) return new RequestResult();
//        else return instance;
//    }
//
//    private RequestResult() {
//    }
//
//
//}

public enum RequestResult {
    INSTANCE;

    public String jsonResult = "";

    public void refreshResult() {
        jsonResult = "";
    }

}