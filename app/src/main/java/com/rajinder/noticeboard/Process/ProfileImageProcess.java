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

public class ProfileImageProcess {

    private OnLoginAction onLoginAction;
    private static final String URL_PROFILE_UPLOAD = Utils.SERVER + "picchange";
    private List<UserModel> userModelList;

    public ProfileImageProcess(OnLoginAction loginAction) {
        this.onLoginAction = loginAction;
    }


    /*call start*/
    public void startprocess(int userid, String image,String image_name, Context context) {
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
                            try {
                                new Delete().from(User.class).execute();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
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
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                ;
                            }
                            try {
                                new Delete().from(User.class).execute();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            if (userModelList.get(0).success) {
                                User user = new User();
                                user.userid = userModelList.get(0).user.userid;
                                user.username = userModelList.get(0).user.username;
                                user.phoneNumber = userModelList.get(0).user.phoneNumber;
                                user.email = userModelList.get(0).user.email;
                                user.user_img = userModelList.get(0).user.user_img;
                                user.address = userModelList.get(0).user.address;
                                user.post_total = userModelList.get(0).user.post_total;
                                try {
                                    user.save();
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
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
        data.set("user_id",userid);
        data.set("img64",image);
        data.set("image_name",image_name);
        Utils.log("DATA: " + data.toString());
        netUtils.postRequest(URL_PROFILE_UPLOAD, data);
    }
}

//d,img64,image_name
