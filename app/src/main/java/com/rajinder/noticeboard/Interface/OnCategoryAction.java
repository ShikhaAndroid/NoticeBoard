package com.rajinder.noticeboard.Interface;

import com.rajinder.noticeboard.models.Category.CategoryModel;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.util.List;

public interface OnCategoryAction {

    void onFinishCateActions(List<CategoryModel> userModels, String reponse);
    void onErroCaterAction(String error);

}
