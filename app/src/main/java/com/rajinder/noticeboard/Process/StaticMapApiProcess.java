package com.rajinder.noticeboard.Process;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.rajinder.noticeboard.Interface.OnCategoryAction;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Utils.NetUtils.Json;
import com.rajinder.noticeboard.Utils.NetUtils.NetUtils;
import com.rajinder.noticeboard.Utils.Utils;
import com.rajinder.noticeboard.models.Category.CategoryModel;

import java.io.InputStream;
import java.util.List;

public class StaticMapApiProcess {

    private OnLoginAction onLoginAction;
    private static final String URL_STATIC_MAP = "https://maps.googleapis.com/maps/api/staticmap";

    public StaticMapApiProcess(OnLoginAction onLoginAction) {
        this.onLoginAction = onLoginAction;
    }

    /*call start*/
    public void startprocess(Context context) {

        NetUtils netUtils = new NetUtils(context, new NetUtils.OnNetUtilsActions() {

            @Override
            public void onInitRequest(String url) {}

            @Override
            public void onFinishRequest(Exception e, String response, int status) {
                if (e == null)
                {
                    Utils.log("sign in process onfinish response: " + response);
                    onLoginAction.onFinishLoginActions(null, response);

                }
            }
        });

        Json data = Json.object();
        data.set("center", "Brooklyn+Bridge,New+York,NY");
        data.set("zoom", "13");
        data.set("size", "600x300");
        data.set("format", "jpg");
        data.set("maptype", "roadmap");
        data.set("markers", "color:blue%7Clabel:S%7C40.702147,-74.015794");
        data.set("markers", "color:green%7Clabel:G%7C40.711614,-74.012318");
        data.set("markers", "color:red%7Clabel:C%7C40.718217,-73.998284");
        data.set("key", "AIzaSyAtjDMmxkV_0Brj1JtpU5x84xwyeG55YuY");
        netUtils.getRequest(URL_STATIC_MAP, data);

    }

}
