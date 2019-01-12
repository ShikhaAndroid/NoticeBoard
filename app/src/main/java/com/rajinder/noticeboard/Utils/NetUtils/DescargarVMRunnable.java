package com.rajinder.noticeboard.Utils.NetUtils;

import android.content.Context;
import android.os.Environment;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.rajinder.noticeboard.Utils.Utils;


import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Rajinder on 4/27/2018.
 */
public class DescargarVMRunnable implements Runnable {
    private String nombre;
    private Context context;
    private OnDescargaVMActions onDescargaVMActions;
    private static final String URL_BASE = Utils.SERVER + "/testing/VMUploads/";
    private static final String DIRECTORY = Environment.getExternalStorageDirectory() + "/"
            + Utils.NOTICE_BROAD_FILES_FOLDER ;

    public DescargarVMRunnable(Context context, String nombre,
                               OnDescargaVMActions onDescargaVMActions) {
        this.context = context;
        this.nombre = nombre;
        this.onDescargaVMActions = onDescargaVMActions;
    }

    @Override
    public void run() {
        String url = URL_BASE + nombre;
        Utils.log("va a descargar: " + url);
        Ion.with(context)
                .load(url)
                .progress(new ProgressCallback() {
                    @Override
                    public void onProgress(long downloaded, long total) {
                        int progress = (int) ((downloaded * 100) / total);
                        if (progress < 0) {
                            progress = 0;
                        }
                        if (progress > 100) {
                            progress = 100;
                        }
                    }
                })
                .write(getFile())
                .setCallback(new FutureCallback<File>() {
                    @Override
                    public void onCompleted(Exception e, File file) {
                        if (e == null) {
                            mandarABorrarDelServer();
                        } else {
                        }
                    }
                });
    }

    private void mandarABorrarDelServer() {
        NetUtils netUtils = new NetUtils(context, new NetUtils.OnNetUtilsActions() {
            @Override
            public void onInitRequest(String url) {
            }

            @Override
            public void onFinishRequest(Exception e, String response, int status) {
                Utils.log("TERMINO DE BORRAR EL VM: " + response);
                onDescargaVMActions.onCompleted(nombre);
            }
        });

        netUtils.postRequest(Utils.SERVER + "/testing/deleteVM.php", Json.object("name", nombre));
    }




    private File getFile() {
        File dir = new File(DIRECTORY);
        if (!dir.isDirectory()) {
            if (!dir.mkdirs()) {
                throw new RuntimeException("Error creando el directorio de audio");
            }
        }
        return new File(dir, nombre);
    }

    interface OnDescargaVMActions {
        void onCompleted(String nombre);
    }
}
