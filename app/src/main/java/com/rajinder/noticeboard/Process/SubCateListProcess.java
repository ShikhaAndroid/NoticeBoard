package com.rajinder.noticeboard.Process;

import android.content.Context;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rajinder.noticeboard.Interface.OnCategoryAction;
import com.rajinder.noticeboard.Interface.OnSubCategoryAction;
import com.rajinder.noticeboard.Utils.NetUtils.Json;
import com.rajinder.noticeboard.Utils.NetUtils.NetUtils;
import com.rajinder.noticeboard.Utils.Utils;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.Category.CategoryModel;
import com.rajinder.noticeboard.models.subCategory.SubCategoryModel;

import java.lang.reflect.Modifier;
import java.util.List;

public class SubCateListProcess {

    private OnSubCategoryAction onSubCategoryAction;
    private static final String URL_SUB_CATEGORY = Utils.SERVER + "subcategory";
    private List<SubCategoryModel> subCategoryModels;

    public SubCateListProcess(OnSubCategoryAction onSubCategoryAction) {
        this.onSubCategoryAction = onSubCategoryAction;
    }


    /*call start*/
    public void startprocess(String catid, Context context) {
        NetUtils netUtils = new NetUtils(context, new NetUtils.OnNetUtilsActions() {
            @Override
            public void onInitRequest(String url) {
            }

            @Override
            public void onFinishRequest(Exception e, String response, int status) {
                if (e == null) {
                    if (status == 200) {
                        Utils.log("cate process onfinish response: " + response);//todo quitar
                        try {
                            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                                    .serializeNulls()
                                    .create();
                            TypeToken<List<SubCategoryModel>> token = new TypeToken<List<SubCategoryModel>>() {
                            };
                            try {
                                String ss = "[" + response + "]";
                                subCategoryModels = gson.fromJson(ss, token.getType());
                            } catch (JsonSyntaxException e1) {
                                e1.printStackTrace();
                            }


                            onSubCategoryAction.onFinishSubCateActions(subCategoryModels, "success");

                        } catch (Throwable t) {
                            onSubCategoryAction.onErroSubCaterAction("Could not parse malformed JSON:" + response);
                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                    } else {
                        onSubCategoryAction.onErroSubCaterAction(getClass().getName() +
                                " envioPost status: " + status);
                    }
                } else {
                    onSubCategoryAction.onErroSubCaterAction("internet");
                }
            }
        });
        Json data = Json.object();
        data.set("catid", catid);
        Utils.log("DATA: " + data.toString());
        netUtils.postRequest(URL_SUB_CATEGORY, data);
    }
}
