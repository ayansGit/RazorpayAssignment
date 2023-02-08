package com.ayan.rnetwork;

import org.json.JSONObject;

public interface NetworkInterface {

    JSONObject fetchCustomUI(String url);
    byte[] fetchLogo(String url);
}
