package com.rajinder.noticeboard.Interface;

import com.rajinder.noticeboard.models.Category.CategoryModel;
import com.rajinder.noticeboard.models.PostListModel;

import java.util.List;

public interface OnPostListAction {

    void onFinishPostActions(List<PostListModel> userModels, String reponse);
    void onErrorPostAction(String error);

}
