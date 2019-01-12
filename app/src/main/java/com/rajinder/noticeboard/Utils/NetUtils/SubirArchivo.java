package com.rajinder.noticeboard.Utils.NetUtils;

import android.content.Context;


import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.koushikdutta.ion.Response;
import com.koushikdutta.ion.builder.Builders;
import com.rajinder.noticeboard.Utils.Utils;

import java.io.File;
import java.util.Map;


/**
 * Created by Rajinder on 4/27/2018.
 */
public class SubirArchivo {
    private Context context;
    private OnSubirArchivoActions onSubirArchivoActions;

    public SubirArchivo(Context context, OnSubirArchivoActions onSubirArchivoActions) {
        this.context = context;
        this.onSubirArchivoActions = onSubirArchivoActions;
    }

    private void subir(File archivo, String url, String nombre, Json data, Json headerData) {
        url = NetUtils.proccessUrl(url);
        Utils.log("Subiendo a: " + url);
        onSubirArchivoActions.onStartSubirArchivo(url);
        Builders.Any.B b = Ion.with(context).load("POST", url);
        b = NetUtils.getHeaderData(b, headerData);
        b.progress(new ProgressCallback() {
            @Override
            public void onProgress(long downloaded, long total) {
                int progress = (int) ((downloaded * 100) / total);
                if (progress < 0) {
                    progress = 0;
                }
                if (progress > 100) {
                    progress = 100;
                }
                onSubirArchivoActions.onProgressSubirArchivo(progress);
            }
        });
        Builders.Any.M mb = b.setMultipartFile(nombre, archivo);
        getMultipartData(mb, data)
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {
                        sendResult(e, result);
                    }
                });
    }

    public void sendResult(Exception e, Response<String> response) {
        onSubirArchivoActions.onFinishSubirArchivo(e,
                (response == null) ? 0 : response.getHeaders().code(),
                (response == null) ? "" : response.getResult());
    }

    private Builders.Any.M getMultipartData(Builders.Any.M builder, Json data) {
        if (data != null && data.isObject()) {
            for (Map.Entry<String, Json> uno : data.asJsonMap().entrySet()) {
                builder.setMultipartParameter(uno.getKey(), uno.getValue().asString());
            }
        }
        return builder;
    }

    public void subir(File archivo, String url, String nombre, Json data) {
        subir(archivo, url, nombre, data, null);
    }

    public void subir(File archivo, String url, String nombre) {
        subir(archivo, url, nombre, null, null);
    }

    public void cancelarSubida() {
        Ion.getDefault(context).cancelAll(context);
        onSubirArchivoActions.onCancelledSubirArchivo();
    }

    public interface OnSubirArchivoActions {
        void onStartSubirArchivo(String url);

        void onFinishSubirArchivo(Exception e, int status, String response);

        void onProgressSubirArchivo(int porcentaje);

        void onCancelledSubirArchivo();
    }

}
