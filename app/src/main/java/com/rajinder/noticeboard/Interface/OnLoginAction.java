package com.rajinder.noticeboard.Interface;

import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.util.ArrayList;
import java.util.List;

public interface OnLoginAction {

    void onFinishLoginActions(List<UserModel> userModels, String reponse);
    void onErrorLoginAction(String error);

}
