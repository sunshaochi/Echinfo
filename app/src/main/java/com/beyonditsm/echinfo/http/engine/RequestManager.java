package com.beyonditsm.echinfo.http.engine;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.beyonditsm.echinfo.MyApplication;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.impl.EchinfoEngine;
import com.beyonditsm.echinfo.http.util.VolleyErrorHelper;
import com.beyonditsm.echinfo.util.MyLogUtils;
import com.beyonditsm.echinfo.util.SpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据请求
 * Created by wangbin on 16/4/12.
 */
public class RequestManager {
    public static EchinfoEngine echinfoEngine;
    public static EchinfoEngine getCommManager() {
        if (echinfoEngine == null) {
            echinfoEngine = new EchinfoEngine();
        }
        return echinfoEngine;
    }


    /* Volley Post请求
     *
     * @param url
     * @param callback
     */
    public void doPost(final String url, final Map<String, String> params, final CallBack callback) {
        MyLogUtils.info("地址:" + url);
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result = response.toString();
                        MyLogUtils.info(result);
                        try {
                            JSONObject obj = new JSONObject(result);
                            int status = obj.getInt("status");
                            if (status == 200) {
                                callback.onSucess(result);
                            } else {
                                callback.onError(obj.getString("message"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(VolleyErrorHelper.getMessage(error));
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap localHashMap = new HashMap();
                localHashMap.put("Cookie", SpUtils.getCookie(MyApplication.getInstance()));
                return localHashMap;
            }

            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                // TODO Auto-generated method stub
                try {
                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    String dataString = new String(response.data, "UTF-8");
                    if (!TextUtils.isEmpty(rawCookies))
                        SpUtils.setCooike(MyApplication.getInstance(), rawCookies);
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        // 设定超时时间
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 1, 1.0f));
        MyApplication.getInstance().addToRequestQueue(request);
        MyApplication.getInstance().getRequestQueue().start();
    }

}
