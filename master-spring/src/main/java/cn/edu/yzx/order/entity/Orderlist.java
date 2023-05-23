package cn.edu.yzx.order.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Orderlist)实体类
 *
 * @author yzx
 * @since 2022-04-23 17:01:07
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orderlist implements Serializable {
    private static final long serialVersionUID = 890992216136811065L;
    
    private String id;
    
    private String username;
    
    private String time;
    
    private String staplefood;
    
    private String weight;
    
    private String region;
    
    private String saozi;
    
    private String ingredients;
    
    private String note;
    
    private String isshow;
    
    private String tag;
    
    private String isout;
    
    private String father;


}

