package com.pdsu.sojacnn.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonInclude;
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
 * @since 2021-05-07
 */
@SuppressWarnings("all")
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="NewsCategory对象", description="新闻的二级类型")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类别ID， 自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "类别名")
    private String categoryName;

    @ApiModelProperty(value = "所属上级ID")
    private Integer contypeId;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    @TableField(select = false)
    private transient Integer isDelete;

}
