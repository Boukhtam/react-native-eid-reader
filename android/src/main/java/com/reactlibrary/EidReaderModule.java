package com.reactlibrary;

import com.reactlibrary.eidconnection.DocumentData;
import com.reactlibrary.eidconnection.EIDConnection;

import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;

import android.app.Activity;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import android.provider.Settings;

import android.nfc.NfcAdapter;
import android.nfc.Tag;

import org.jmrtd.PassportService;

public class EidReaderModule extends ReactContextBaseJavaModule implements ActivityEventListener, LifecycleEventListener {

    private Context context;
    private final ReactApplicationContext reactContext;

    private final Intent mIntent = new Intent();
    private final List<IntentFilter> intentFilters = new ArrayList<IntentFilter>();

    private Tag currentTag;

    private boolean isForegroundEnabled = false;
    private boolean isResumed = false;

    private final ArrayList<String[]> techLists = new ArrayList<String[]>();

    private DocumentData documentData;
    private EIDConnection eidcon;
    private PassportService pservice;

    public EidReaderModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
        this.reactContext = reactContext;
        reactContext.addActivityEventListener(this);
        reactContext.addLifecycleEventListener(this);
    }

    @Override
    public String getName() {
        return "EidReader";
    }


    // NOTE: onNewIntent!! & Activity thing!!!
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

    }

    // NOTE: onNewIntent!! & Activity thing!!!
    @Override
    public void onHostPause() {}

    // NOTE: onNewIntent!! & Activity thing!!!
    @Override
    public void onHostResume() {
        isResumed = true;
        if (isForegroundEnabled) {
            enableDisableForegroundDispatch(true);
        }
    }

    // NOTE: onNewIntent!! & Activity thing!!!
    @Override
    public void onHostDestroy() {}

    // NOTE: onNewIntent!! & Activity thing!!!
    @Override
    public void onNewIntent(Intent intent) {
        //super.onNewIntent(intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        
        /*
         * WARNING: must write an approtiate code
         * [https://www.programcreek.com/java-api-examples/?code=Dnet3/CustomAndroidOneSheeld/CustomAndroidOneSheeld-master/oneSheeld/src/main/java/com/integreight/onesheeld/shields/controller/NfcShield.java]
         */
        setCurrentTag(tag);

        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("gotATag", "wow!");
    }

    private void setCurrentTag(Tag tag) {
        this.currentTag = tag;
    }

    
    private void enableDisableForegroundDispatch(boolean enable) {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        Activity currentActivity = getCurrentActivity();

        if (nfcAdapter != null && currentActivity != null && !currentActivity.isFinishing()) {
            try {
                if (enable) {
                    nfcAdapter.enableForegroundDispatch(currentActivity, getPendingIntent(), getIntentFilters(), getTechLists());
                } else {
                    nfcAdapter.disableForegroundDispatch(currentActivity);
                }
            } catch (IllegalStateException | NullPointerException e) {
                Log.w("Illegae state Exception...", "The seong arg!");
            }
        }
    }

    private PendingIntent getPendingIntent() {
        Activity activity = getCurrentActivity();
        Intent intent = new Intent(activity, activity.getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return PendingIntent.getActivity(activity, 0, intent, 0);
    }

    private IntentFilter[] getIntentFilters() {
        return intentFilters.toArray(new IntentFilter[intentFilters.size()]);
    }

    private String[][] getTechLists() {
        return techLists.toArray(new String[0][0]);
    }
    

    @ReactMethod
    public void isEnabled(Callback callback) {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(reactContext);
        if(nfcAdapter != null) {
            callback.invoke(null, nfcAdapter.isEnabled());
        } else {
            callback.invoke(null, false);
        }
    }

    @ReactMethod
    public void openNfcSettings(Callback callback) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            callback.invoke("Failed to get current activity \'Java/Android stuff!");
            return;
        } 

        currentActivity.startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
        callback.invoke();
    }

    @ReactMethod
    public void createDocument(ReadableMap args, Promise promise) {
        try {
            documentData = new DocumentData();
            documentData.setExpiryDate(args.getString("expiryDate"));
            documentData.setDocumentNumber(args.getString("documentNumber"));
            documentData.setDateOfBirth(args.getString("dateOfBirth"));

            if (!documentData.isValid()) {
                promise.resolve("Error, One or more provided document data is inValid!");
                return;
            }

            promise.resolve("DocumentData instance successfulyy created!: " + documentData.toString());
        } catch (Exception ex) {
            promise.reject("Unable to create a document instance!:" + ex.toString());
        }
    }

    @ReactMethod
    public void registerEIDListener(Promise promise) {

        try {
            intentFilters.add(new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED));

            // NOTE/TODO: see if you can make a better code!
            isForegroundEnabled = true;
            if (isResumed) {
                enableDisableForegroundDispatch(true);
            }

            promise.resolve("done!");
        } catch (Exception ex) {
            promise.reject("not okay!", ex.toString());
        }
    }

    @ReactMethod
    public void connect(Promise promise) {
        eidcon = new EIDConnection();

        

        try {
            pservice = eidcon.openConnection(currentTag, documentData);
            promise.resolve("Access to the card granted!");
        } catch (Exception ex) {
            promise.reject(ex.toString());
        }
    }
}
