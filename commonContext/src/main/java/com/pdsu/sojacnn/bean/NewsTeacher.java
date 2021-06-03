package com.pdsu.sojacnn.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 半梦
 * @since 2021-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="NewsTeacher对象", description="")
public class NewsTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "老师姓名")
    private String name;

    @ApiModelProperty(value = "老师简介")
    private String introduction;

    @ApiModelProperty(value = "老师电话")
    private Integer phoneNumber;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private transient Integer isDelete;


}
