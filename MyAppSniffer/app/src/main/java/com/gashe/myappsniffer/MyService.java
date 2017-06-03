package com.gashe.myappsniffer;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class MyService extends NotificationListenerService {

    private String bundle2string (Bundle bundle){
        String strdev = null;
        strdev = "Inicio Bundle {";
        for(String key : bundle.keySet()){
            strdev = strdev + " " + key + " " + bundle.get(key)+";";
        }
        strdev = strdev + "} FIN Bundle";
        return strdev;
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        log("LISTENER CONECTADO");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        log("NOTIFICACIÓN RECIBIDA");
        log("NOMBRE PAQUEETE " + sbn.getPackageName());

        Notification notification = sbn.getNotification();
        Bundle bundle = notification.extras;

        String mensaje_noti = bundle2string(bundle);
        log(mensaje_noti);

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        log("APLICACION ELIMINADA");
        log("NOMBRE PAQUEETE " + sbn.getPackageName());
    }

    public void log(String s){
        Log.d(getClass().getCanonicalName(), s);
    }

}
