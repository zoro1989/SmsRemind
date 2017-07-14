package com.ccbjb.tollcollector.util.http;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/5.
 */
public class TKMBaseParseBean implements Serializable {

    /**
     * @Fields serialVersionUID : TODO(版本号)
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private String httpCode;
    private String httpMessage;
    private String resultCode;
    private String messageId;
    private String messageBody;
    private String tokenId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public void setHttpMessage(String httpMessage) {
        this.httpMessage = httpMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

}
