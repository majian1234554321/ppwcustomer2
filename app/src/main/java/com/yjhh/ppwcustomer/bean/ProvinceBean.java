package com.yjhh.ppwcustomer.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class ProvinceBean implements IPickerViewData {

    public String provinceName;
    public String provinceCode;

    public List<CityBean> cityBeans;

    public ProvinceBean(String provinceName, String provinceCode, List<CityBean> cityBeans) {
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
        this.cityBeans = cityBeans;
    }


    public static class CityBean {
        public String cityName;
        public String cityCode;
        public List<AreaBean> areaBean;

        public CityBean(String cityName, String cityCode, List<AreaBean> areaBean) {
            this.cityName = cityName;
            this.cityCode = cityCode;
            this.areaBean = areaBean;
        }

        public CityBean(String cityName, String cityCode) {
            this.cityName = cityName;
            this.cityCode = cityCode;

        }

        public static class AreaBean {
            public String areaName;
            public String areaCode;

            public AreaBean(String areaName, String areaCode) {
                this.areaName = areaName;
                this.areaCode = areaCode;
            }
        }
    }


    @Override
    public String getPickerViewText() {
        return provinceName;
    }
}