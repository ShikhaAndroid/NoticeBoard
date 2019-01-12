package com.rajinder.noticeboard.Interface;

import com.rajinder.noticeboard.models.Category.CategoryModel;
import com.rajinder.noticeboard.models.DeletepostModel;

import java.util.List;

public interface DeletepostAction {
    void onFinishCateActions(List<DeletepostModel> userModels, String reponse);
    void onErroCaterAction(String error);
}
