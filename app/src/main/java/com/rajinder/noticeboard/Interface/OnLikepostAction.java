package com.rajinder.noticeboard.Interface;

import com.rajinder.noticeboard.models.DeletepostModel;

import java.util.List;

public interface OnLikepostAction {
    void onFinishLUActions(List<DeletepostModel> userModels, String reponse);
    void onErrorLUAction(String error);
}
