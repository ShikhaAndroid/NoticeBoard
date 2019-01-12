package com.rajinder.noticeboard.Interface;

import com.rajinder.noticeboard.models.UserInfo.UserModel;
import com.rajinder.noticeboard.models.UserProfileViewModel;

import java.util.List;

public interface OnProfileViewAction {

    void onFinishProfileActions(List<UserProfileViewModel> userModels, String reponse);
    void onErrorProfileAction(String error);

}
