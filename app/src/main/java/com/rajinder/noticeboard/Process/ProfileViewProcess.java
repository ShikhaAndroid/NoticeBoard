package com.rajinder.noticeboard.Process;

import android.content.Context;
import android.util.Log;

import com.activeandroid.query.Delete;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Interface.OnProfileViewAction;
import com.rajinder.noticeboard.Utils.NetUtils.Json;
import com.rajinder.noticeboard.Utils.NetUtils.NetUtils;
import com.rajinder.noticeboard.Utils.Utils;
import com.rajinder.noticeboard.models.UserInfo.User;
import com.rajinder.noticeboard.models.UserInfo.UserModel;
import com.rajinder.noticeboard.models.UserProfileViewModel;

import java.lang.reflect.Modifier;
import java.util.List;

public class ProfileViewProcess {

    private OnProfileViewAction onProfileViewAction;
    private static final String URL_PROFILE_VIEW = Utils.SERVER + "userprofile";
    private List<UserProfileViewModel> userModelList;

    public ProfileViewProcess(OnProfileViewAction onProfileViewAction) {
        this.onProfileViewAction = onProfileViewAction;
    }


    /*call start*/
    public void startprocess(String user_id, Context context) {
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
                            TypeToken<List<UserProfileViewModel>> token = new TypeToken<List<UserProfileViewModel>>() {
                            };
                            try {
                                userModelList = gson.fromJson(response, token.getType());
                            } catch (JsonSyntaxException e1) {
                                e1.printStackTrace();
                            }
                            catch (Exception e2){
                                e2.printStackTrace();;
                            }


                            onProfileViewAction.onFinishProfileActions(userModelList, "success");

                        } catch (Throwable t) {
                            onProfileViewAction.onErrorProfileAction("Could not parse malformed JSON:" + response);
                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                    } else {
                        onProfileViewAction.onErrorProfileAction(getClass().getName() +
                                " envioPost status: " + status);
                    }
                } else {
                    Utils.log("error request: " + e.toString());
                    onProfileViewAction.onErrorProfileAction("internet");
                }
            }
        });
        Json data = Json.object();
        data.set("user_id", user_id);
        Utils.log("DATA: " + data.toString());
        netUtils.postRequest(URL_PROFILE_VIEW, data);
    }
}
