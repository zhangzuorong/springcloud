package springcloud.servicehi.enumerate;

/**
 * 开发公司：青岛上朝信息科技有限公司
 * 版权：青岛上朝信息科技有限公司
 * <p>
 * 类功能描述
 *
 * @author zhangzuorong
 * @created 2018/9/19.
 */
public enum ResultEnum {
    OK(200, "ok"),
    PARAMETER_ERROR(10001, "parameter error"),
    UPDATE_FAILED(10002, "update failed"),
    ADD_FAILED(10003, "add failed"),
    DELETE_FAILED(10005, "delete failed"),
    DATA_FAILED(10004, "data failed"),
    BUSINESS_INFO_FIND_FAILED(10006,"business info find failed");
    private Integer code;
    private String msg;


    ResultEnum(int i, String ok) {
        this.code = i;
        this.msg = ok;
    }

    public Integer code(){
        return this.code;
    }

    public String msg(){
        return this.msg;
    }
}
