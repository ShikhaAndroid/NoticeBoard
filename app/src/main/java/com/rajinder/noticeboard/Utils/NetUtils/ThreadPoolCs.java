package com.rajinder.noticeboard.Utils.NetUtils;

import android.content.Context;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Rajinder on 4/27/2018.
 */
public class ThreadPoolCs implements DescargarVMRunnable.OnDescargaVMActions {
    private ExecutorService executorService;
    private OnThreadPoolActions onThreadPoolActions;
    private static final int MAX_THREADS = 5;
    private ArrayList<String> works = new ArrayList<>();
    private Context context;

    public ThreadPoolCs(Context context, OnThreadPoolActions onThreadPoolActions) {
        this.context = context;
        this.onThreadPoolActions = onThreadPoolActions;
        executorService = Executors.newFixedThreadPool(MAX_THREADS);
    }

    public void dowloadVM(String nombre) {
        works.add(nombre);
        executorService.submit(new DescargarVMRunnable(context, nombre, this));
    }

    public void finish() {
        executorService.shutdownNow();
    }

    @Override
    public void onCompleted(String nombre) {
        works.remove(nombre);
        if (works.isEmpty()) {
            onThreadPoolActions.onFinishAllTasks();
        }
    }

    public interface OnThreadPoolActions {
        void onFinishAllTasks();
    }
}
