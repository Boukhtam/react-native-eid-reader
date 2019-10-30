package com.reactlibrary.eidconnection;

import com.reactlibrary.eidconnection.DocumentData;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;

import net.sf.scuba.smartcards.CardService;

import org.jmrtd.PassportService;

public class EIDConnection {

    private PassportService ps;
    private int maxTranceiveLength = 65536;
    private int maxBlockSize = 256;
    private boolean isSFIEnabled = false;
    private boolean shouldCheckMAC = false;

    public PassportService openConnection (Tag tag, final DocumentData docData) throws Exception {
        try {
            IsoDep nfc = IsoDep.get(tag);
            CardService cs = CardService.getInstance(nfc);
            this.ps = new PassportService(cs, maxTranceiveLength, maxBlockSize, isSFIEnabled, shouldCheckMAC);
            this.ps.open();

            return ps;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
