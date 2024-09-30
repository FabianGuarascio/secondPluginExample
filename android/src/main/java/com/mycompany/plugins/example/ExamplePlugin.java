package com.mycompany.plugins.example;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.getcapacitor.Bridge;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginHandle;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Example")
public class ExamplePlugin extends Plugin {

    private Example implementation = new Example();
  public static Bridge staticBridge = null;

  /*codigo del repo de github */
  public NotificationManager notificationManager;


  public void load() {
    notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

    //firebaseMessagingService = new MessagingService();

    staticBridge = this.bridge;
    if (lastMessage != null) {
      fireNotification(lastMessage);
      lastMessage = null;
    }


  }

  public static JSObject lastMessage = null;
  public static ExamplePlugin getPushNotificationsInstance() {
    if (staticBridge != null && staticBridge.getWebView() != null) {
      PluginHandle handle = staticBridge.getPlugin("Example");
      if (handle == null) {
        return null;
      }
      return (ExamplePlugin) handle.getInstance();
    }
    return null;
  }
  public static void sendRemoteMessage(JSObject data2) {
    ExamplePlugin pushPlugin = ExamplePlugin.getPushNotificationsInstance();
    if (pushPlugin != null) {
      pushPlugin.fireNotification(data2);
      Log.d("ENABLE_PUSH","Push plugin no es nulo");
    } else {
      Log.d("ENABLE_PUSH","Ahora si es nulo");
      lastMessage = data2;
    }
  }

  public void fireNotification(JSObject data2) {
    JSObject data = new JSObject();
    data.put("title", "el titulo2");
    data.put("body", "el body3");
    notifyListeners("pushNotificationReceived",data2,true);
  }

  /*asdfjk*/

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");
        Log.d("PLUGIN","DESDE PLUGIN");
        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

  @PluginMethod
  public void echo2(PluginCall call) {

    JSObject data = new JSObject();
    data.put("title", "el titulo");
    data.put("body", "el body");
    notifyListeners("pushNotificationReceived",data,true);

  }

  public void echo3(){
    JSObject data = new JSObject();
    data.put("title", "el titulo");
    data.put("body", "el body");
    notifyListeners("pushNotificationReceived",data,true);
  }

  @PluginMethod
  public void getStoredNotifications(PluginCall call) {
    JSArray notifications = new JSArray();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();

      for (StatusBarNotification notif : activeNotifications) {
        JSObject jsNotif = new JSObject();

        jsNotif.put("id", notif.getId());
        jsNotif.put("tag", notif.getTag());

        Notification notification = notif.getNotification();
        if (notification != null) {
          jsNotif.put("title", notification.extras.getCharSequence(Notification.EXTRA_TITLE));
          jsNotif.put("body", notification.extras.getCharSequence(Notification.EXTRA_TEXT));
          jsNotif.put("group", notification.getGroup());
          jsNotif.put("groupSummary", 0 != (notification.flags & Notification.FLAG_GROUP_SUMMARY));

          JSObject extras = new JSObject();

          for (String key : notification.extras.keySet()) {
            extras.put(key, notification.extras.getString(key));
          }

          jsNotif.put("data", extras);
        }

        notifications.put(jsNotif);
      }
    }

    JSObject result = new JSObject();
    result.put("notifications", notifications);
    call.resolve(result);
  }





}
