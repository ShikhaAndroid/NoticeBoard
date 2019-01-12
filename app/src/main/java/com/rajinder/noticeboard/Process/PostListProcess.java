package com.rajinder.noticeboard.Process;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rajinder.noticeboard.Interface.OnPostListAction;
import com.rajinder.noticeboard.MyApplication;
import com.rajinder.noticeboard.Utils.NetUtils.Json;
import com.rajinder.noticeboard.Utils.NetUtils.NetUtils;
import com.rajinder.noticeboard.Utils.Utils;
import com.rajinder.noticeboard.models.PostListModel;
import java.lang.reflect.Modifier;
import java.util.List;

public class PostListProcess {

    public static final String TAG = "PostListProcess";
    private OnPostListAction onPostAction;
    private static final String URL_CATEGORY = Utils.SERVER + "catwise";
    private static final String URL_USER = Utils.SERVER + "userwise";
    private static final String URL_AROUNDME = Utils.SERVER + "rangewise";
    private static final String URL_FAV = Utils.SERVER + "fav-list";
    private List<PostListModel> postListModels;

    public PostListProcess(OnPostListAction onPostAction) {
        this.onPostAction = onPostAction;
    }

    /*call start*/
    public void startprocess(String cat_id, String user_id, Context context, String type, double lng, double lat) {

        NetUtils netUtils = new NetUtils(context, new NetUtils.OnNetUtilsActions() {

            @Override
            public void onInitRequest(String url) {}

            @Override
            public void onFinishRequest(Exception e, String response, int status) {
                if (e == null) {
                    if (status == 200) {
                        Log.d(TAG, "onFinishRequest: "+response);   // todo quitar
                        try {
                            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                                    .serializeNulls()
                                    .create();
                            TypeToken<List<PostListModel>> token = new TypeToken<List<PostListModel>>() {};
                            try {
                                postListModels = gson.fromJson(response, token.getType());
                            } catch (JsonSyntaxException e1) {
                                e1.printStackTrace();
                            }
                            if (postListModels != null && postListModels.size()!=0)
                                onPostAction.onFinishPostActions(postListModels, "success");
                            else
                                onPostAction.onErrorPostAction("null");
                        } catch (Throwable t) {
                            onPostAction.onErrorPostAction("Could not parse malformed JSON:" + response);
                            Log.d(TAG, "Could not parse malformed JSON: \"" + response + "\"");
                        }
                    } else {
                        onPostAction.onErrorPostAction(getClass().getName() + " envioPost status: " + status);
                    }
                } else {
                    Log.d(TAG, "onFinishRequest: "+e.getMessage());
                    onPostAction.onErrorPostAction("internet");
                }
            }
        });

        Json data = Json.object();
        if (cat_id.equals("-1")) {
            MyApplication myApplication = ((MyApplication) context.getApplicationContext());
            data.set("nblatitude", lat);
            data.set("nblongitude", lng);
            data.set("range", myApplication.getAroundmekm());
            if (myApplication.getFilterCat() != null)
                data.set("main_catid", myApplication.getFilterCat().catid);
            if (myApplication.getFiterSubCat() != null)
                data.set("sub_catid", myApplication.getFiterSubCat().subcatid);
            data.set("user_id", user_id);
            Utils.log("DATA: " + data.toString());
            netUtils.postRequest(URL_AROUNDME, data);
        } else if (type.equals("catid")) {
            data.set("catid", cat_id);
            data.set("user_id", user_id);
            Utils.log("DATA: " + data.toString());
            netUtils.postRequest(URL_CATEGORY, data);
        } else if (type.equals("userid")) {
            data.set("user_id", user_id);
            Utils.log("DATA: " + data.toString());
            netUtils.postRequest(URL_USER, data);
        } else if (type.equals("fav")) {
            data.set("user_id", user_id);
            Utils.log("DATA: " + data.toString());
            netUtils.postRequest(URL_FAV, data);
        }

    }

}
