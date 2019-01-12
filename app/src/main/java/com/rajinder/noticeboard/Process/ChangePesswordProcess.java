package com.rajinder.noticeboard.Process;

import android.content.Context;
import android.util.Log;

import com.activeandroid.query.Delete;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Utils.NetUtils.Json;
import com.rajinder.noticeboard.Utils.NetUtils.NetUtils;
import com.rajinder.noticeboard.Utils.Utils;
import com.rajinder.noticeboard.models.UserInfo.User;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.lang.reflect.Modifier;
import java.util.List;

public class ChangePesswordProcess {

    private OnLoginAction onLoginAction;
    private static final String URL_C_PASSWORD = Utils.SERVER + "changepassword";
    private List<UserModel> userModelList;

    public ChangePesswordProcess(OnLoginAction loginAction) {
        this.onLoginAction = loginAction;
    }


    /*call start*/
    public void startprocess(String login_, String oldpassword, String newpassword, Context context) {
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
                            try {
                                new Delete().from(User.class).execute();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            if (userModelList.get(0).success) {
                                onLoginAction.onFinishLoginActions(userModelList, "success");
                            } else {
                                onLoginAction.onErrorLoginAction(getClass().getName() +
                                        " envioPost status: " + status);
                            }

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
        //   Parameters: phone_no/emailid,password 
        Json data = Json.object();
        data.set("userid", login_);
        data.set("oldpassword", oldpassword);
        data.set("newpassword", newpassword);

        Utils.log("DATA: " + data.toString());
        netUtils.postRequest(URL_C_PASSWORD, data);
    }
}
