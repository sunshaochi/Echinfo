package com.beyonditsm.echinfo.http.engine;

import com.beyonditsm.echinfo.ConstantValue;
import com.beyonditsm.echinfo.MyApplication;
import com.beyonditsm.echinfo.util.MyLogUtils;
import com.beyonditsm.echinfo.util.SpUtils;
import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.lidroid.xutils.http.client.multipart.content.StringBody;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * 上传图片
 * Created by wangbin on 16/3/14.
 */
public class HttpManager {
    private HttpClient client;
    private HttpPost post;

    private static Header[] headers;

    static {
        headers = new Header[1];
        headers[0] = new BasicHeader("cookie", SpUtils.getCookie(MyApplication.getInstance()));
    }

    public HttpManager() {
        // 初始化client
        // 如果是wap方式联网，需要设置代理信息
        client = new DefaultHttpClient();
        // 响应超时时间为5秒
        client.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
    }

    /**
     * 文件上传 post
     *
     * @param uri
     * @param params
     * @param fileMaps
     * @return
     */
    public String upLoadFile(String uri, Map<String, String> params,
                             Map<String, FileBody> fileMaps) {
        post = new HttpPost(uri);
        post.setHeaders(headers);
        try {
            MultipartEntity mpEntity = new MultipartEntity();
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, String> item : params.entrySet()) {
                    StringBody par = new StringBody(item.getValue());
                    mpEntity.addPart(item.getKey(), par);
                }

            }

            if (fileMaps != null && fileMaps.size() > 0) {
                for (Map.Entry<String, FileBody> entry : fileMaps.entrySet()) {
                    mpEntity.addPart(entry.getKey(), entry.getValue());
                }
            }
            post.setEntity(mpEntity);// 设置需要传递的数据
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(),
                        ConstantValue.ENCODING);
            } else {
                MyLogUtils.error("访问失败--状态码："
                        + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyLogUtils.error("访问异常：" + e.getMessage());
        }
        return null;
    }

}
