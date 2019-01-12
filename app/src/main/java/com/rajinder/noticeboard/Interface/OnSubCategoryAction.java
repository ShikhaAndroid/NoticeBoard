package com.rajinder.noticeboard.Interface;

import com.rajinder.noticeboard.models.Category.CategoryModel;
import com.rajinder.noticeboard.models.subCategory.SubCategoryModel;

import java.util.List;

public interface OnSubCategoryAction {

    void onFinishSubCateActions(List<SubCategoryModel> subCategoryModels, String reponse);
    void onErroSubCaterAction(String error);

}
