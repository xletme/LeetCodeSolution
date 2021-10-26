package Leetcode.Tree;

import java.util.List;

/**
 * @Author maoXin
 * @Description
 * @Date 11:07 2021/10/20
 */
public class QueryCwPayRecordResp {

    private Integer code;

    private Boolean result;

    private String message;

    private List<QueryCwPayRecordDO> data;

    private String errors;

    private String status;

    private String sysDate;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<QueryCwPayRecordDO> getData() {
        return data;
    }

    public void setData(List<QueryCwPayRecordDO> data) {
        this.data = data;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
}
