package com.rajinder.noticeboard.Utils.NetUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.webkit.URLUtil;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.body.Part;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.koushikdutta.ion.builder.Builders;
import com.rajinder.noticeboard.Utils.Utils;


import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Rajinder on 4/27/2018.
 */
public class NetUtils {

    public static final String TAG ="NetUtils";
    private Context context;
    private OnNetUtilsActions onNetUtilsActions;
    private static final String BASE_URL = Utils.SERVER;
    private static final String POST = "POST", GET = "GET", PUT = "PUT", DELETE = "DELETE";

    public NetUtils(Context context, OnNetUtilsActions onNetUtilsActions) {
        this.context = context;
        this.onNetUtilsActions = onNetUtilsActions;
    }

    //WITH BODY:
    private Future<Response<String>> wBodyRequest(String url, Json data, Json header, final String request) {
        url = proccessUrl(url);
        onNetUtilsActions.onInitRequest(url);
        Utils.log("init " + request + " request to: " + url);
        Builders.Any.B b = Ion.with(context).load(request, url);
        b = getHeaderData(b, header);
        return getData(b, data)
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {
                        if (e != null) {
                            Log.d(TAG, "onCompleted: exception: "+e.getMessage());
                        }
                        Log.d(TAG, "onCompleted: "+new Gson().toJson(result));
                        sendResult(e, result);
                    }
                });
    }

    // Post multipart/form-data
    private Future<Response<String>> uploadImagesandData(String url, Json data, Json header, final String request, List<Part> files){ //File file
        url = proccessUrl(url);
        onNetUtilsActions.onInitRequest(url);
        Utils.log(TAG + request + " request to: " + url);
        Builders.Any.B b = Ion.with(context).load(request, url);
        b = getHeaderData(b, header);
        return getMData(b, data, files)
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {
                        Log.d(TAG, "onCompleted: "+new Gson().toJson(result));
                        sendResult(e, result);
                    }
                });
    }

    /*JSON OBJECT */
    public Future<Response<JsonObject>> postRequests(String url, Json data) {
        return postRequests(url, data, null);
    }

    private Future<Response<JsonObject>> postRequests(String url, Json data, Json header) {
        return wBodyRequests(url, data, header, POST);
    }

    private Future<Response<JsonObject>> wBodyRequests(String url, Json data, Json header, final String request) {
        url = proccessUrl(url);
        onNetUtilsActions.onInitRequest(url);
        Utils.log("init " + request + " request to: " + url);
        Builders.Any.B b = Ion.with(context).load(request, url);
        b = getHeaderData(b, header);
        return getData(b, data)
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        sendResultobj(e, result);
                    }
                });
    }

    private void sendResultobj(Exception e, Response<JsonObject> result) {
        onNetUtilsActions.onFinishRequest(e, (result == null) ? "" : result.toString(),
                (result == null) ? 0 : result.getHeaders().code());
    }

/*********************************************************************************************************************************************/

    private Future<Response<String>> postRequest(String url, Json data, Json header) {
        return wBodyRequest(url, data, header, POST);
    }

    public Future<Response<String>> postRequest(String url, Json data) {
        return postRequest(url, data, null);
    }

    public Future<Response<String>> postMultiPartRequest(String url, Json data, List<Part> files ) { //File file
        return uploadImagesandData(url, data, null, POST, files);
    }

    private Future<Response<String>> putRequest(String url, Json data, Json header, boolean resent) {
        return wBodyRequest(url, data, header, PUT);
    }

    public Future<Response<String>> putRequest(String url, Json data, Json header) {
        return putRequest(url, data, header, false);
    }

    public Future<Response<String>> putRequest(String url, Json data) {
        return putRequest(url, data, null);
    }

    //WITHOUT BODY:
    private Future<Response<String>> woBodyRequest(String url, Json data, Json headerData, final String request) {
        url = proccessUrl(url);
        onNetUtilsActions.onInitRequest(url);
        url = getUrlWithData(url, data);
        Utils.log("init " + request + " request to: " + url);
        Builders.Any.B b = Ion.with(context).load(request, url);
        b = getHeaderData(b, headerData);
        return b.asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {
                        sendResult(e, result);
                    }
                });
    }

    private Future<Response<String>> getRequest(String url, Json data, Json headerData) {
        return woBodyRequest(url, data, headerData, GET);
    }

    public Future<Response<String>> getRequest(String url, Json data) {
        return getRequest(url, data, Json.object());
    }

    public Future<Response<String>> getRequest(String url) {
        return getRequest(url, Json.object(), Json.object());
    }

    private Future<Response<String>> deleteRequest(String url, Json data, Json headerData) {
        return woBodyRequest(url, data, headerData, DELETE);
    }

    public Future<Response<String>> deleteRequest(String url, Json data) {
        return deleteRequest(url, data, Json.object());
    }

    private Builders.Any.B getData(Builders.Any.B builder, Json data) {
        if (data != null && data.isObject()) {
            for (Map.Entry<String, Json> uno : data.asJsonMap().entrySet()) {
                if (uno.getValue().isArray()) {
                    String keyArr = uno.getKey() + "[]";
                    for (Json dos : uno.getValue().asJsonList()) {
                        builder.setBodyParameter(keyArr, dos.asString());
                    }
                } else {
                    builder.setBodyParameter(uno.getKey(), uno.getValue().asString());
                }
            }
        }
        return builder;
    }

    private Builders.Any.B getMData(Builders.Any.B builder, Json data, List<Part> files ) { // File file
        if (data != null && data.isObject()) {
            for (Map.Entry<String, Json> uno : data.asJsonMap().entrySet()) {
                if (uno.getValue().isArray()) {
                    String keyArr = uno.getKey() + "[]";
                    for (Json dos : uno.getValue().asJsonList()) {
                        builder.setMultipartParameter(keyArr, dos.asString());
                    }
                } else {
                    builder.setMultipartParameter(uno.getKey(), uno.getValue().asString());
                }
            }
            Log.d(TAG, "getMData: size: "+files.size());
            builder.addMultipartParts(files);
//            builder.setMultipartFile("file_url", file);
        }
        return builder;
    }

    public static Builders.Any.B getHeaderData(Builders.Any.B builder, Json headerData) {
        if (headerData != null && headerData.isObject()) {
            for (Map.Entry<String, Json> uno : headerData.asJsonMap().entrySet()) {
                builder.setHeader(uno.getKey(), uno.getValue().asString());
            }
        }
        return builder;
    }

    private String getUrlWithData(String url, Json data) {
        if (data != null && data.isObject()) {
            boolean first = true;
            for (Map.Entry<String, Json> uno : data.asJsonMap().entrySet()) {
                if (first) {
                    url += "?";
                    first = false;
                } else {
                    url += "&";
                }
                if (uno.getValue().isArray()) {
                    String keyArr = uno.getKey() + "[]";
                    for (Json dos : uno.getValue().asJsonList()) {
                        url += (Uri.encode(keyArr) + "=" + Uri.encode(dos.asString()));
                    }
                } else {
                    url += (Uri.encode(uno.getKey()) + "=" + Uri.encode(uno.getValue().asString()));
                }
            }
        }
        return url;
    }

    public interface OnNetUtilsActions {
        void onInitRequest(String url);
        void onFinishRequest(Exception e, String response, int status);
    }

    public void sendResult(Exception e, Response<String> response) {
        onNetUtilsActions.onFinishRequest(e, (response == null) ? "" : response.getResult(), (response == null) ? 0 : response.getHeaders().code());
    }

    public static boolean hayInternet(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        return i != null && i.isConnected() && i.isAvailable();
    }

    public static String proccessUrl(String url) {
        if (!URLUtil.isValidUrl(url)) {
            url = BASE_URL + url;
        }
        return url;
    }

}