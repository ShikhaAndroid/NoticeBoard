package com.rajinder.noticeboard.Process;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.http.body.FilePart;
import com.koushikdutta.async.http.body.Part;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Interface.OnPostAction;
import com.rajinder.noticeboard.Utils.NetUtils.Json;
import com.rajinder.noticeboard.Utils.NetUtils.NetUtils;
import com.rajinder.noticeboard.Utils.Utils;
import com.rajinder.noticeboard.models.PostCreateModel;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.io.File;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PostCreateProcess {

    public static final String TAG = "PostCreateProcess";
    private OnPostAction onPostAction;
    private static final String URL_CREATE_POST = Utils.SERVER + "newpost";
    private List<PostCreateModel> postCreateModels;

    public PostCreateProcess(OnPostAction onPostAction) {
        this.onPostAction = onPostAction;
    }

    /*call start*/
    public void startprocess(List<String> images, String cat_id, String subcat_id, String location, Double location_lat, Double location_long, String title_post, String detail_post, String start_time,
                             String end_time, String start_date, String end_date, String post_type, Double price, Double rating, Integer user_id, Context context) {
        NetUtils netUtils = new NetUtils(context, new NetUtils.OnNetUtilsActions() {
            @Override
            public void onInitRequest(String url) {}

            @Override
            public void onFinishRequest(Exception e, String response, int status) {

                if (e == null) {
                    if (status == 200) {
                        Utils.log("sign in process onfinish response: " + response);//todo quitar
                        try {
                            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                                    .serializeNulls()
                                    .create();
                            TypeToken<List<PostCreateModel>> token = new TypeToken<List<PostCreateModel>>() {
                            };
                            try {
                                String ss = "[" + response + "]";
                                postCreateModels = gson.fromJson(ss, token.getType());
                            } catch (JsonSyntaxException e1) {
                                e1.printStackTrace();
                            }
                            onPostAction.onFinishPostActions(postCreateModels, "success");

                        } catch (Throwable t) {
                            onPostAction.onErrorPostAction("Could not parse malformed JSON:" + response);
                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                    } else {
                        onPostAction.onErrorPostAction(getClass().getName() + " envioPost status: " + status);
                    }
                } else {
                    Utils.log("error request: " + e.toString());
                    onPostAction.onErrorPostAction("internet");
                }
            }
        });
//Parameters: ,,,,,,,,,,,,,,
        Calendar calendarimage = Calendar.getInstance();
        Date currentdate = calendarimage.getTime();
        SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy");
        String imagename = String.valueOf(user_id).concat(df.format(currentdate));
        Float ratingfloat = null;

        Log.d(TAG, "startprocess: "+ cat_id+","+subcat_id+","+location+","+location_lat+","+location_long+","+title_post+","+detail_post+","+start_time+","+end_time+","+start_date+","+end_date
        +","+post_type+","+price+","+user_id);

        Json data = Json.object();
        data.set("cat_id", cat_id);
        data.set("subcat_id", subcat_id);
        data.set("location", location);
        data.set("location_lat", location_lat);
        data.set("location_long", location_long);
        data.set("title_post", title_post);
        data.set("detail_post", detail_post);
        data.set("start_time", start_time);
        data.set("end_time", end_time);
        data.set("start_date", start_date);
        data.set("end_date", end_date);
        data.set("post_type", post_type);
        data.set("price", price);
        if (rating != 0.0) {
            ratingfloat = Float.valueOf(String.valueOf(rating));
            data.set("rating", ratingfloat);
        } else
            data.set("rating", 0);
        data.set("user_id", user_id);
//        if(image!=null) {
//            data.set("img64", image);
//            data.set("image_name", "abc.JPEG");
//        }     Utils.log("DATA: " + data.toString());
//        netUtils.postRequest(URL_CREATE_POST, data);
        List <Part> files = new ArrayList();
        for (int i=0; i<images.size(); i++){
            files.add(new FilePart("file_url[" + i + "]", new File(images.get(i))));
        }
//        File fileToUpload = new File(image);
        netUtils.postMultiPartRequest(URL_CREATE_POST, data, files);
    }

    public String rename (String path){
        Log.d(TAG, "rename: "+path);
        File currentFile = new File(path);

        File from = new File(currentFile.getParent(),currentFile.getName());
        File to = new File(currentFile.getParent(),currentFile.getName().substring(0,4));
        if(from.exists()) {
            if(from.renameTo(to)){
                Log.d(TAG, "rename: success");
                return  to.getAbsolutePath();
            }
        }
        Log.d(TAG, "rename: "+from);
        Log.d(TAG, "rename: "+to);
        return "";
    }

}
