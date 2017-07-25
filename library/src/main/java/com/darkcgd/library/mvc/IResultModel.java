package com.darkcgd.library.mvc;


import com.szmg.zhinews.mvc.fileutil.FileBody;
import com.szmg.zhinews.util.AbJsonUtil;
import com.szmg.zhinews.util.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;


/**
 * Created by cgd on 2016/8/5.
 */
public class IResultModel {

public static final String GET="GET";
public static final String POST="POST";
public static final String POSTSTRING="POSTSTRING";
public static final String POSTFILE="POSTFILE";
public static final String UPLOADFILE="UPLOADFILE";
public static final String DOWNLOADFILE="DOWNLOADFILE";

    // OKHTTP请求方式（新加）
 //   =====================================================================================================================================================
    /***
     * GET请求
     * @param url  请求地址
     * @param params   参数params封装的是参数
     * @param resultView  resultView,用于回调数据结果
     * @param requestCode 用于识别同url 时，不同参数的请求
     */

    public void doGetRequestCode(final String url, OkRequestParams params, final IResultView resultView,
                                 String requestCode) {
        GetBuilder getBuilder = OkHttpUtils.get();//
        ConcurrentHashMap<String, String> urlParams = params.getUrlParams();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            getBuilder.addParams(entry.getKey(),entry.getValue());
        }
        /*if (BuildConfig.LOG) {
            Log.d("==========请求的相关数据", "请求地址:"+url+"\n请求的参数:"+params.getUrlParams().toString());
        }*/
        getBuilder.url(url)//
                .id(101)
                .tag(this)
                .build()//
                .execute(MyStringCallBack(requestCode,resultView));
    }
    /***
     * GET请求
     * @param url  请求地址
     * @param params   参数params封装的是参数
     * @param resultView  resultView,用于回调数据结果
     */

    public void doGetRequest(final String url, OkRequestParams params, final IResultView resultView) {
        GetBuilder getBuilder = OkHttpUtils.get();//
        ConcurrentHashMap<String, String> urlParams = params.getUrlParams();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            getBuilder.addParams(entry.getKey(),entry.getValue());
        }
        /*if (BuildConfig.LOG) {
            Log.d("==========请求的相关数据", "请求地址:"+url+"\n请求的参数:"+params.getUrlParams().toString());
        }*/
        getBuilder.url(url)//
                    .id(101)
                    .tag(this)
                    .build()//
                    .execute(MyStringCallBack(url,resultView));
        }
    /**
     *
     * POET请求
     * @param url  请求地址
     * @param params   参数params封装的是参数
     * @param resultView  resultView,用于回调数据结果
     */
    public void doPostRequestCode(final String url, OkRequestParams params, final IResultView resultView,String requestCode) {
        PostFormBuilder postBuilder = OkHttpUtils.post();
        postBuilder.url(url);
        ConcurrentHashMap<String, String> urlParams = params.getUrlParams();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            postBuilder.addParams(entry.getKey(),entry.getValue());
        }
        /*if (BuildConfig.LOG) {
            Log.d("==========请求的相关数据", "请求地址:"+url+"\n请求的参数:"+params.getUrlParams().toString());
        }*/
        String tag = urlParams.get("tag");

        postBuilder.id(101)
                .tag(tag)
                .build()//
                .execute(MyStringCallBack(requestCode,resultView));
    }
    /**
     *
     * POET请求
     * @param url  请求地址
     * @param params   参数params封装的是参数
     * @param resultView  resultView,用于回调数据结果
     */
    public void doPostRequest(final String url, OkRequestParams params, final IResultView resultView) {
        PostFormBuilder postBuilder = OkHttpUtils.post();
        postBuilder.url(url);
        ConcurrentHashMap<String, String> urlParams = params.getUrlParams();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            postBuilder.addParams(entry.getKey(),entry.getValue());
        }
        /*if (BuildConfig.LOG) {
            Log.d("==========请求的相关数据", "请求地址:"+url+"\n请求的参数:"+params.getUrlParams().toString());
        }*/
        String tag = urlParams.get("tag");

        postBuilder.id(101)
                .tag(tag)
                .build()//
                .execute(MyStringCallBack(url,resultView));
    }
    /**
     *
     * POET请求
     * @param url  请求地址
     * @param params   参数params封装的是参数
     * @param resultView  resultView,用于回调数据结果
     */
    public void doPostRequest(final String url, OkRequestParams params, final IResultView resultView,String header) {

        PostFormBuilder postBuilder = OkHttpUtils.post();
        postBuilder.url(url);
        postBuilder.addHeader("authorization",header);
        postBuilder.addHeader("Content-Type", "text/plain");
        ConcurrentHashMap<String, String> urlParams = params.getUrlParams();

        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            postBuilder.addParams(entry.getKey(),entry.getValue());
        }
        /*if (BuildConfig.LOG) {
            Log.d("==========请求的相关数据", "请求地址:"+url+"\nheader:"+header+"\n请求的参数:"+params.getUrlParams().toString());
        }*/

        String tag = urlParams.get("tag");

        postBuilder.id(101)
                .tag(tag)
                .build()//
                .execute(MyStringCallBack(url,resultView));
    }

    /**
     *
     *  Post请求（用于Psot的值为JSON数据）
     * @param url  请求地址
     * @param params   参数params封装的是参数
     * @param resultView  resultView,用于回调数据结果
     */
    public void doPostStringRequest(final String url, OkRequestParams params, final IResultView resultView) {
        OkHttpUtils
                .postString()//
                .url(url)//
                .id(101)
                .mediaType(MediaType.parse("text/plain ; application/json; charset=utf-8"))
                .tag(this)
                .build()//
                .execute(MyStringCallBack(url,resultView));

    }
    /**
     *
     *  Post请求（用于Psot的值为JSON数据）
     * @param url  请求地址
     * @param params   参数params封装的是参数
     * @param resultView  resultView,用于回调数据结果
     */
    public void doPostStringRequest(final String url, OkRequestParams params, final IResultView resultView,String header) {
        OkHttpUtils
                .postString()//
                .url(url)//
                .id(101)
                .mediaType(MediaType.parse("text/plain"))
                .addHeader("authorization",header)
                .content(AbJsonUtil.toJson(params.getUrlParams()))// 转化为JSON数据
                .tag(this)
                .build()//
                .execute(MyStringCallBack(url,resultView));

        RequestCall build = OkHttpUtils.post().build();

    }

    /**
     *      Post文件
     * @param url  请求地址
     * @param params   参数params封装的是参数
     * @param resultView  resultView,用于回调数据结果
     *
     *   image 作为key ， 记得跟传进来的时候相对应（目前来说，暂时只是图片）
     */
    public void doPostFileRequest(final String url, OkRequestParams params, final IResultView resultView) {
        // 发送文件
        ConcurrentHashMap<String, FileBody> fileParams = params.getFileParams();
        File file = fileParams.get("image").getFile();

        if (!file.exists()) {
            return;
        }
        OkHttpUtils
                .postFile()
                .url(url)
                .file(file)
                .tag(this)
                .build()
                .execute(MyStringCallBack(url,resultView));
    }
    /**
     *      Post文件
     * @param url  请求地址
     * @param params   参数params封装的是参数
     * @param resultView  resultView,用于回调数据结果
     *
     *   image 作为key ， 记得跟传进来的时候相对应（目前来说，暂时只是图片）
     */
    public void doPostFileRequest(final String url, OkRequestParams params, final IResultView resultView,String header) {
        // 发送文件
        ConcurrentHashMap<String, FileBody> fileParams = params.getFileParams();
        File file = fileParams.get("avatar").getFile();

        if (!file.exists()) {
            return;
        }
        OkHttpUtils
                .postFile()
                .url(url)
                .addHeader("authorization",header)
                .file(file)
                .tag(this)
                .build()
                .execute(MyStringCallBack(url,resultView));
    }

    /**
     *      Post文件
     * @param url  请求地址
     * @param params   参数params封装的是参数
     * @param resultView  resultView,用于回调数据结果
     *
     *   image 作为key ， 记得跟传进来的时候相对应（目前来说，暂时只是图片）
     */
    public void doPostFormRequest(final String url, Map params, File file ,final IResultView resultView, String header) {
        OkHttpUtils.post().addHeader("authorization",header).url(Constant.DO_UPLOAD_HEAD_URL).addFile("avatar", file.getName(),file).params(params).build().execute(MyStringCallBack(url,resultView));
    }

    /**
     * Post上传文件， 可支持多图上传
     *
     * @param url  请求地址
     * @param params   参数params封装的是参数
     *                 imagefilelist 为图片文件list
     * @param resultView  resultView,用于回调数据结果
     *        imageFileList 作为key ，记得跟传进来的时候相对应（目前来说，暂时只是图片的list）
     */
    public void doUpLoadFileRequest(final String url, OkRequestParams params, final IResultView resultView) {

          PostFormBuilder postBuilder = OkHttpUtils.post();
        ConcurrentHashMap<String, List<String>> fileListParams = params.getListParams();
        List<String> imagefilelist = fileListParams.get("imageFileList");

        for (int i=0;i<imagefilelist.size();i++){
      //      Uri uri = Uri.fromFile(new File(imagefilelist.get(i)));
            File imageFile=new File(imagefilelist.get(i));
            postBuilder.addFile("imagefile",imageFile.getName(),imageFile);  // 添加文件
        }
        postBuilder
                .url(url)//
                .tag(this)
                .build()//
                .execute(MyStringCallBack(url,resultView));


        // 文件直接测试
      /*  PostFormBuilder postBuilder = OkHttpUtils.post();
        File file = new File(Environment.getExternalStorageDirectory(), "0001.png");

        postBuilder.addFile("image01","image01.jpg",file);
        postBuilder.addFile("file02","image02.jpg",file);
        String url02="http://ugiant.f3322.net:51900/photo/uploadMore";
        postBuilder
                .url(url02)//
                .build()//
                .execute(MyStringCallBack(url02,resultView));
*/

    }

    /***
     *Post下载文件，支持进度回调
     *
     * @param url  请求地址
     * @param params   参数params封装的是参数
     * @param resultView  resultView,用于回调数据结果
     */
    public void doDownloadFileRequest(final String url, OkRequestParams params,String destFileDir,String destFileName, final IResultView resultView) {
        // 下载文件
        OkHttpUtils//
                .get()//
                .url(url)//
                .tag(this)
                .build()//
                .execute(new FileCallBack(destFileDir, destFileName)//
                {
                    @Override
                    public void onBefore(Request request, int id) {
                        resultView.showLoadView(url);
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        resultView.showProgressView(url, (int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        resultView.showErrorView(url, call.toString());
                    }

                    @Override
                    public void onResponse(File file, int id) {
                        resultView.showResultView(url, "返回文件处理");
                    }
                });


    }

    private StringCallback MyStringCallBack(final String url, final IResultView resultView){
          return new StringCallback(){
              @Override
              public void onBefore(Request request, int id) {
                  resultView.showLoadView(url);
              }
              @Override
              public void inProgress(float progress, long total, int id) {
                  resultView.showProgressView(url, (int) (100 * progress));
              }
              @Override
              public void onError(Call call, Exception e, int id) {
                  /*if (BuildConfig.LOG) {
                      Log.d("==========返回的错误数据", "请求地址:"+url+"\n"+"返回的数据:"+e.toString());
                  }*/

                  if(e.toString().contains("UnknownHostException")){
                      resultView.showErrorView(url,  "请检查网络");
                  }else{
                      resultView.showErrorView(url,  e.toString());
                  }

              }
              @Override
              public void onResponse(String response, int id) {
                  /*if (BuildConfig.LOG) {
                      Log.d("==========返回的数据", "请求地址:"+url+"\n"+"返回的数据:"+response);
                  }*/
                  resultView.showResultView(url, response.toString());
              }
          };
    }


}
