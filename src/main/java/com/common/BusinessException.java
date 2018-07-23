package com.common;

public class BusinessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BusinessException(String frdMessage) {
        super(createFriendlyErrMsg(frdMessage));
    }

    public BusinessException(String frdMessage, String mes) {
        super(frdMessage);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String frdMessage, Throwable throwable) {
        super(createFriendlyErrMsg(frdMessage), throwable);
    }

    private static String createFriendlyErrMsg(String msgBody) {
        String prefixStr = "抱歉，";
        String suffixStr = " 请稍后再试或与管理员联系！";
        StringBuffer friendlyErrMsg = new StringBuffer("");
        friendlyErrMsg.append(prefixStr);
        friendlyErrMsg.append(msgBody);
        friendlyErrMsg.append(suffixStr);
        return friendlyErrMsg.toString();
    }

}
