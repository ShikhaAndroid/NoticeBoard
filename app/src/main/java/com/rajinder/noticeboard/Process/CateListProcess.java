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
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Utils.NetUtils.Json;
import com.rajinder.noticeboard.Utils.NetUtils.NetUtils;
import com.rajinder.noticeboard.Utils.Utils;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.Category.CategoryModel;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.lang.reflect.Modifier;
import java.util.List;

public class CateListProcess {

    private OnCategoryAction onCategoryAction;
    private static final String URL_CATEGORY = Utils.SERVER + "category";
    private List<CategoryModel> categoryModelList;

    public CateListProcess(OnCategoryAction onCategoryAction) {
        this.onCategoryAction = onCategoryAction;
    }

    /*call start*/
    public void startprocess(String userid, Context context) {
        NetUtils netUtils = new NetUtils(context, new NetUtils.OnNetUtilsActions() {

            @Override
            public void onInitRequest(String url) {}

            @Override
            public void onFinishRequest(Exception e, String response, int status) {
                if (e == null) {
                    if (status == 200) {
                        Utils.log("cate process onfinish response: " + response); //todo quitar
                        try {
                            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                                    .serializeNulls()
                                    .create();
                            TypeToken<List<CategoryModel>> token = new TypeToken<List<CategoryModel>>() {};
                            try {
                                String ss = "[" + response + "]";
                                categoryModelList = gson.fromJson(ss, token.getType());
                            } catch (JsonSyntaxException e1) {
                                e1.printStackTrace();
                            }
                            try {
                                new Delete().from(Category.class).execute();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            ActiveAndroid.beginTransaction();
                            try {
                                Category category;
                                for (Category categoryloop : categoryModelList.get(0).getCategories()) {
                                    try {
                                        category = new Category();
                                        category.catid = categoryloop.catid;
                                        category.catname = categoryloop.catname;
                                        category.caticon = categoryloop.caticon;
                                        category.catselect = categoryloop.catselect;
                                        category.catnotification = categoryloop.catnotification;
                                        category.type = categoryloop.type;
                                        category.save();
                                    } catch (Exception e1) {
                                        e1.printStackTrace();
                                    }
                                }
                                ActiveAndroid.setTransactionSuccessful();
                            } finally {
                                ActiveAndroid.endTransaction();
                            }
                            onCategoryAction.onFinishCateActions(categoryModelList, "success");
                        } catch (Throwable t) {
                            onCategoryAction.onErroCaterAction("Could not parse malformed JSON:" + response);
                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                    } else {
                        onCategoryAction.onErroCaterAction(getClass().getName() + " envioPost status: " + status);
                    }
                } else {
                    Utils.log("error request: " + e.toString());
                    onCategoryAction.onErroCaterAction("internet");
                }
            }
        });
        Json data = Json.object();
        data.set("userid", userid);
        Utils.log("DATA: " + data.toString());
        netUtils.postRequest(URL_CATEGORY, data);
    }

}
