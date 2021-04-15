package com.rgu.keyboard_detection_plugin

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Build
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.startActivity
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** KeyboardDetectionPlugin */
class KeyboardDetectionPlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private lateinit var context: Context

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "keyboard_detection_plugin")
        channel.setMethodCallHandler(this)
        context = flutterPluginBinding.applicationContext
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else if (call.method.equals("isThirdPartyKeyboard", true)) {
            result.success(isThirdPartyKeyboardUsing())
        } else if (call.method.equals("openKeyboardChangeManager", true)) {
            openSystemKeyboardManager()
        }
    }

    /*
    * Get the status of third party keyboard being used by the user.
    *
    * tru: If the user is using third party keyboard
    * false: If the user is using system default keyboard
    * */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    fun isThirdPartyKeyboardUsing(): Boolean {
        val inputMethodMngr = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //Get installed input methods of device
        val inputMethodList = inputMethodMngr.enabledInputMethodList
        for (i in 0 until inputMethodList.size) {
            //Get the user's default input method
            val imi = inputMethodList[i]
            if (imi.id == Settings.Secure.getString(
                            context.contentResolver,
                            Settings.Secure.DEFAULT_INPUT_METHOD
                    )
            ) {
                //FLAG_SYSTEM - indicate whether application is installed in the device's system image
                if ((imi.serviceInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 1) {
                    return true
                }
            }
        }
        return false
    }

    /*
    * Open the System Keyboard Manager.
    * */
    fun openSystemKeyboardManager() {
        val intent = Intent("android.settings.INPUT_METHOD_SETTINGS")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(context, intent, null)
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
