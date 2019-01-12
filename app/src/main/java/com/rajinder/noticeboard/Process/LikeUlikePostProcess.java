package com.rajinder.noticeboard.Process;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rajinder.noticeboard.Interface.DeletepostAction;
import com.rajinder.noticeboard.Interface.OnLikepostAction;
import com.rajinder.noticeboard.Utils.NetUtils.Json;
import com.rajinder.noticeboard.Utils.NetUtils.NetUtils;
import com.rajinder.noticeboard.Utils.Utils;
import com.rajinder.noticeboard.models.DeletepostModel;

import java.lang.reflect.Modifier;
import java.util.List;

public class LikeUlikePostProcess {

    private OnLikepostAction onLikepostAction;
    private static final String URL_DELETE_POST = Utils.SERVER + "postlike";
    private List<DeletepostModel> deletepostModels;

    public LikeUlikePostProcess(OnLikepostAction onLikepostAction) {
        this.onLikepostAction = onLikepostAction;
    }

    /*call start*/
    public void startprocess(int userid, int postid, String type, Context context) {
        NetUtils netUtils = new NetUtils(context, new NetUtils.OnNetUtilsActions() {
            @Override
            public void onInitRequest(String url) {}

            @Override
            public void onFinishRequest(Exception e, String response, int status) {
                if (e == null) {
                    if (status == 200) {
                        Utils.log("confirm  process onfinish response: " + response);//todo quitar
                        try {
                            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                                    .serializeNulls()
                                    .create();
                            TypeToken<List<DeletepostModel>> token = new TypeToken<List<DeletepostModel>>() {
                            };
                            try {
                                String ss = "[" + response + "]";
                                deletepostModels = gson.fromJson(ss, token.getType());
                            } catch (JsonSyntaxException e1) {
                                e1.printStackTrace();
                            }
                            onLikepostAction.onFinishLUActions(deletepostModels, "success");

                        } catch (Throwable t) {
                            onLikepostAction.onErrorLUAction("Could not parse malformed JSON:" + response);
                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                    } else {
                        onLikepostAction.onErrorLUAction(getClass().getName() + " envioPost status: " + status);
                    }
                } else {
                    Utils.log("error request: " + e.toString());
                    onLikepostAction.onErrorLUAction("internet");
                }
            }
        });
        //user_id,post_id
        Json data = Json.object();
        data.set("user_id",userid);
        data.set("post_id",postid);
        data.set("type",type);
        Utils.log("DATA: " + data.toString());
        netUtils.postRequest(URL_DELETE_POST, data);
    }
}
