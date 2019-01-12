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

import org.json.JSONObject;

import java.lang.reflect.Modifier;
import java.util.List;

public class SignUpProcess {

    private OnLoginAction onLoginAction;
    private static final String URL_SIGN_UP = Utils.SERVER + "signup";
    private List<UserModel> userModelList;

    public SignUpProcess(OnLoginAction loginAction) {
        this.onLoginAction = loginAction;
    }

    /*call start*/
    public void startprocess(Context context, String hex_data, String iv) {
        NetUtils netUtils = new NetUtils(context, new NetUtils.OnNetUtilsActions() {
            @Override
            public void onInitRequest(String url) {}

            @Override
            public void onFinishRequest(Exception e, String response, int status) {
                if (e == null) {
                    if (status == 200) {
                        Utils.log("sign in process onfinish response: " + response);    //todo quitar
                        try {
                            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                                    .serializeNulls()
                                    .create();
                            TypeToken<List<UserModel>> token = new TypeToken<List<UserModel>>() {};
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
                    }  else {
                        onLoginAction.onErrorLoginAction(getClass().getName() + " envioPost status: " + status);
                    }
                } else {
                    Utils.log("error request: " + e.toString());
                    onLoginAction.onErrorLoginAction("internet");
                }
            }
        });

        //Parameters: username,phone_no,emailid,password 
        Json data = Json.object();
//        data.set("username", name);
//        data.set("phone_no", phone);
//        data.set("emailid", email);
//        data.set("password", password);

        data.set("data", hex_data);
        //   data.set("auth_for_post", "pkregister");
        data.set("iv", iv);
        Utils.log("DATA: " + data.toString());
        netUtils.postRequest(URL_SIGN_UP, data);

    }

}
