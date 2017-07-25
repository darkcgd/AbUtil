package com.darkcgd.library.mvc;


import java.io.File;
import java.util.Map;

/**
 * Created by chenjun on 2016/8/5.
 */
public class BaseController {

    private IResultView resultView;
    private IResultModel resultModel;

    /**
     * 获得一个实例(不是单例)
     *
     * @return
     */
    public static BaseController getInstance(IResultView resultView) {
        return new BaseController(resultView);
    }


    public BaseController(IResultView resultView) {
        this.resultView = resultView;
        resultModel = new IResultModel();
    }

    /***OKHttp的get请求***/
    public void doGetRequest(String url, OkRequestParams params) {
        resultModel.doGetRequest(url, params, resultView);
    }

    /***OKHttp的get请求***/
    public void doGetRequestCode(String url, OkRequestParams params, String requestCode) {
        resultModel.doGetRequestCode(url, params, resultView, requestCode);
    }

    /***OKHttp的post请求***/
    public void doPostRequest(String url, OkRequestParams params) {
        resultModel.doPostRequest(url, params, resultView);
    }

    /***OKHttp的post请求***/
    public void doPostRequestCode(String url, OkRequestParams params, String requestCode) {
        resultModel.doPostRequestCode(url, params, resultView, requestCode);
    }

    /***OKHttp的post请求***/
    public void doPostRequest(String url, OkRequestParams params, String header) {
        resultModel.doPostRequest(url, params, resultView, header);
    }

    /***OKHttp的post请求（JSON格式发送）***/
    public void doPostStringRequest(String url, OkRequestParams params) {
        resultModel.doPostStringRequest(url, params, resultView);
    }

    /***OKHttp的post请求（JSON格式发送）***/
    public void doPostStringRequest(String url, OkRequestParams params, String header) {
        resultModel.doPostStringRequest(url, params, resultView, header);
    }

    /***OKHttp的post请求（文件发送）***/
    public void doPostFileRequest(String url, OkRequestParams params) {
        resultModel.doPostFileRequest(url, params, resultView);
    }

    /***OKHttp的post请求（文件发送）***/
    public void doPostFileRequest(String url, OkRequestParams params, String header) {
        resultModel.doPostFileRequest(url, params, resultView, header);
    }

    /***OKHttp的post请求（文件发送）***/
    public void doPostFormRequest(String url, Map params, File file, String header) {
        resultModel.doPostFormRequest(url, params, file, resultView, header);
    }


    /***OKHttp的post请求（上传文件， 支持单个，多个文件）***/
    public void doUpLoadFileRequest(String url, OkRequestParams params) {
        resultModel.doUpLoadFileRequest(url, params, resultView);
    }

    /**
     * OKHttp的post请求（下载文件文件）
     *
     * @param url
     * @param params
     * @param destFileDir  文件存储的路径
     * @param destFileName 文件名
     */
    public void doDownloadFileRequest(String url, OkRequestParams params, String destFileDir, String destFileName) {
        resultModel.doDownloadFileRequest(url, params, destFileDir, destFileName, resultView);
    }


}
