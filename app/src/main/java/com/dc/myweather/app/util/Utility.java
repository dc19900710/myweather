package com.dc.myweather.app.util;

import android.text.TextUtils;

import com.dc.myweather.app.db.MyWeatherDB;
import com.dc.myweather.app.model.City;
import com.dc.myweather.app.model.County;
import com.dc.myweather.app.model.Province;

/**
 * Created by DC on 2015/4/14.
 */
public class Utility {
    /**
     * 解析和处理服务器返回的省数据
     * @param myWeatherDB
     * @param response
     * @return
     */
    public synchronized static boolean handleProvincesResponse(MyWeatherDB myWeatherDB,String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if(allProvinces!=null&&allProvinces.length>0){
                for(String p :allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceName(array[0]);
                    province.setProvinceCode(array[1]);
                    myWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市数据
     * @param myWeatherDB
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCitiesResponse(MyWeatherDB myWeatherDB,String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if(allCities!=null&&allCities.length>0){
                for(String c:allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityName(array[0]);
                    city.setCityCode(array[1]);
                    city.setProvinceId(provinceId);
                    myWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县数据
     * @param myWeatherDB
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountyResponse(MyWeatherDB myWeatherDB,String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if(allCounties!=null&&allCounties.length>0){
                for(String c:allCounties){
                    String[] array=c.split("\\|");
                    County county = new County();
                    county.setCountyName(array[0]);
                    county.setCountyCode(array[1]);
                    county.setCityId(cityId);
                    myWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
