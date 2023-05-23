package cn.edu.yzx.order.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 返回前端格式消息类
 *
 * @author yzx
 * @version V1.0
 * @package cn.edu.transportsys.utils
 * @date 2022/3/30 20:13
 */
@Component
@Data
public class ResultMap implements Serializable {

    //返回状态码，成功为0，失败为-1,数据格式错误为-2,系统异常为-3
    private int resultCode = -1;

    //返回消息
    private String resultMsg = "待处理";

    //返回的数据
    private Object data = null;

    /**
     * 系统异常时返回的消息,状态码为500
     *
     * @return
     */
    public static ResultMap error() {
        return new ResultMap(500, "系统内部异常");
    }

    /**
     * 请求成功时返回的消息,状态码为200
     *
     * @param data
     * @return
     */
    public static ResultMap success(Object data) {
        return new ResultMap(200, "请求成功", data);
    }

    /**
     * 请求成功时返回的消息,状态码为200
     *
     * 不传数据到前端
     * @return
     */
    public static ResultMap success() {
        return new ResultMap(200, "请求成功");
    }

    /**
     * 请求失败时返回的消息,状态码为400
     *
     * @return
     */
    public static ResultMap fail() {
        return new ResultMap(400, "请求失败", null);
    }

    /**
     * 请求失败时返回的消息，可以携带数据返回,状态码为-1
     *
     * @param data
     * @return
     */
    public static ResultMap fail(Object data) {
        return new ResultMap(-1, "请求失败", data);
    }


    public ResultMap() {
    }

    public ResultMap(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ResultMap(int resultCode, String resultMsg, Object data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }
}
