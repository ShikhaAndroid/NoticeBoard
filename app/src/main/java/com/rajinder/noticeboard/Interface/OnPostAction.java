package com.rajinder.noticeboard.Interface;

import com.rajinder.noticeboard.models.PostCreateModel;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.util.List;

public interface OnPostAction {

    void onFinishPostActions(List<PostCreateModel> postCreateModels, String reponse);
    void onErrorPostAction(String error);

}
