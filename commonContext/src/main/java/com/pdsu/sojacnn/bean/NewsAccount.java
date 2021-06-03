package com.pdsu.sojacnn.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pdsu.sojacnn.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 半梦
 * @since 2021-05-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="NewsAccount对象", description="")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Log4j2
public class NewsAccount implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "名称")
    private String userName;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    @TableField(select = false)
    private transient Integer isDelete;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(exist = false)
    private NewsAccountRole role;

    public NewsAccount copy() {
        try {
            return (NewsAccount) clone();
        } catch (CloneNotSupportedException e) {
            if(log.isDebugEnabled()) {
                log.debug("bean copy error", e);
            }
            return null;
        }
    }

}
