package com.rajinder.noticeboard.Process;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Utils.NetUtils.Json;
import com.rajinder.noticeboard.Utils.NetUtils.NetUtils;
import com.rajinder.noticeboard.Utils.Utils;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.lang.reflect.Modifier;
import java.util.List;

public class ForgotProcess {

    private OnLoginAction onLoginAction;
    private static final String URL_FORGOT_PASSWORD = Utils.SERVER + "forgotpassword";
    private List<UserModel> userModelList;

    public ForgotProcess(OnLoginAction loginAction) {
        this.onLoginAction = loginAction;
    }


    /*call start*/
    public void startprocess(String type, String emailphone, Context context) {
        NetUtils netUtils = new NetUtils(context, new NetUtils.OnNetUtilsActions() {
            @Override
            public void onInitRequest(String url) {
            }

            @Override
            public void onFinishRequest(Exception e, String response, int status) {
                if (e == null) {
                    if (status == 200) {
                        Utils.log("sign in process onfinish response: " + response);//todo quitar
                        try {
                            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                                    .serializeNulls()
                                    .create();
                            TypeToken<List<UserModel>> token = new TypeToken<List<UserModel>>() {
                            };
                            try {
                                String ss = "[" + response + "]";
                                userModelList = gson.fromJson(ss, token.getType());
                            } catch (JsonSyntaxException e1) {
                                e1.printStackTrace();
                            }
                            onLoginAction.onFinishLoginActions(userModelList, "success");

                        } catch (Throwable t) {
                            onLoginAction.onErrorLoginAction("Could not parse malformed JSON:" + response);
                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                    } else {
                        onLoginAction.onErrorLoginAction(getClass().getName() +
                                " envioPost status: " + status);
                    }
                } else {
                    Utils.log("error request: " + e.toString());
                    onLoginAction.onErrorLoginAction("internet");
                }
            }
        });
        Json data = Json.object();
        if(type.equals("EMAIL"))
        data.set("emailid", emailphone);
        else
        data.set("password", emailphone);
        Utils.log("DATA: " + data.toString());
        netUtils.postRequest(URL_FORGOT_PASSWORD, data);
    }
}
