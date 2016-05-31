package com.beyonditsm.echinfo.http.engine;

import android.os.AsyncTask;
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
import com.beyonditsm.echinfo.util.GsonUtils;
import com.beyonditsm.echinfo.util.MyLogUtils;
import com.beyonditsm.echinfo.util.SpUtils;
import com.lidroid.xutils.http.client.multipart.content.FileBody;

import org.json.JSONException;
import org.json.JSONObject;

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
        MyLogUtils.info("传入参数：" + GsonUtils.bean2Json(params));
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result = response.toString();
                        MyLogUtils.info(result);

                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            if(jsonObject.getInt("status")==200){//成功

                            }else{//失败

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                error.getMessage();
                callback.onError(VolleyErrorHelper.getMessage(error));
            }
        })

        {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap localHashMap = new HashMap();
                if (!TextUtils.isEmpty(SpUtils.getCookie(MyApplication.getInstance())))
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
                } catch (Exception e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        // 设定超时时间
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 1, 1.0f));
        MyApplication.getInstance().addToRequestQueue(request);
        MyApplication.getInstance().getRequestQueue().start();
    }

    /**
     * 不需要登录接口请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public void doPostNoLogin(final String url, final Map<String, String> params, final CallBack callback) {
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
        })

        {
            @Override
            protected Map<String, String> getParams() {
                return params;
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
                } catch (Exception e) {
                    return Response.error(new ParseError(e));
                }
            }
        }

                ;
        // 设定超时时间
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 1, 1.0f));
        MyApplication.getInstance().addToRequestQueue(request);
        MyApplication.getInstance().getRequestQueue().start();
    }


    /**
     * Volley get请求
     *
     * @param url
     * @param callback
     */
    public void doGet(String url, final CallBack callback) {
        MyLogUtils.info("地址:" + url);
        StringRequest request = new StringRequest(url,
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
        });
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap localHashMap = new HashMap();
//                if(!TextUtils.isEmpty(SpUtils.getCookie(MyApplication.getInstance())))
//                    localHashMap.put("Cookie", SpUtils.getCookie(MyApplication.getInstance()));
//                return localHashMap;
//            }
//
//            @Override
//            protected Response<String> parseNetworkResponse(
//                    NetworkResponse response) {
//                // TODO Auto-generated method stub
//                try {
//
//                    Map<String, String> responseHeaders = response.headers;
//                    String rawCookies = responseHeaders.get("Set-Cookie");
//                    String dataString = new String(response.data, "UTF-8");
//                    if (!TextUtils.isEmpty(rawCookies))
//                        SpUtils.setCooike(MyApplication.getInstance(), rawCookies);
//                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
//                } catch (UnsupportedEncodingException e) {
//                    return Response.error(new ParseError(e));
//                }
//            }
//        };
        // 设定超时时间
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 1, 1.0f));
        MyApplication.getInstance().addToRequestQueue(request);
        MyApplication.getInstance().getRequestQueue().start();

    }

    /* 上传图片
    *
    * @param url
    * @param
    * @param fileMaps
    * @param callBack
    */
    public void loadImage(final String url, final Map<String, FileBody> fileMaps, final CallBack callBack) {
        MyLogUtils.info("地址:" + url);
        final HttpManager manager = new HttpManager();
        final Map<String, String> par = new HashMap<String, String>();
        par.put("type", "img");
        par.put("uploadLableName", "file");
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return manager.upLoadFile(url, par, fileMaps);
            }

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String result) {
                MyLogUtils.info("上传图片：" + result);
                JSONObject obj = null;
                try {
                    if (result != null)
                        obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 200) {
                        callBack.onSucess(obj.getString("data"));
                    } else {
                        callBack.onError(obj.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

}
