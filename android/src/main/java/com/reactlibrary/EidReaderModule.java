package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.content.Context;

import android.nfc.NfcAdapter;

public class EidReaderModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    private Context context;

    public EidReaderModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "EidReader";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    @ReactMethod
    public void isEnabled(Callback callback) {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        if(NfcAdapter != null) {
            callback.invoke(null, nfcAdapter.isEnabled());
        } else {
            callback.invoke(null, false)
        }
    }
}
