package com.yjhh.common.listener;

import com.amap.api.maps2d.model.LatLng;

public interface LocationLatlng {
    //返回定位结果
    void locatinmLatlng(LatLng latLng, String address);

    void searchResult(LatLng latLng);
}

