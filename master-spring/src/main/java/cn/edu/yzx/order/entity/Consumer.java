package cn.edu.yzx.order.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Consumer)实体类
 *
 * @author yzx
 * @since 2022-04-22 00:26:40
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Consumer implements Serializable {
    private static final long serialVersionUID = -55374092972256629L;
    
    private String username;
    
    private String password;
    
    private String id;


}

