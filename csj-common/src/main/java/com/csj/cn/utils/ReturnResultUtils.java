package com.csj.cn.utils;

public class ReturnResultUtils {

    private static final Integer SUCCESS_CODE = 200;
    private static final String SUCCESS_MESSAGE = "success";
    private static final Integer Fail_CODE = 110;

    /**
     * 不带data的成功
     *
     * @return
     */
    public static ReturnResult returnSucess() {
        return returnSucess(null);
    }

    /**
     * 带data的成功
     *
     * @param data
     * @return
     */
    public static ReturnResult returnSucess(Object data) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(SUCCESS_CODE);
        returnResult.setMessage(SUCCESS_MESSAGE);
        returnResult.setData(data);
        return returnResult;
    }

    /**
     * 带code message的失败
     *
     * @param code
     * @param message
     * @return
     */
    public static ReturnResult returnFail(Integer code, String message) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(code);
        returnResult.setMessage(message);
        return returnResult;
    }

    /**
     * 带全部参数的成功
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ReturnResult returnSucess(Integer code, String message, Object data) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(code);
        returnResult.setMessage(message);
        returnResult.setData(data);
        return returnResult;
    }

    /**
     * 带message的失败
     *
     * @param message
     * @return
     */
    public static ReturnResult returnFail(String message) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(Fail_CODE);
        returnResult.setMessage(message);
        return returnResult;
    }

}
