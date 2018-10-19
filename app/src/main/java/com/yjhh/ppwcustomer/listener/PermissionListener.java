package com.yjhh.ppwcustomer.listener;

import java.util.List;

public interface PermissionListener {
    void onGranted();
    void onDenied(List<String> deniedPermission);
}
