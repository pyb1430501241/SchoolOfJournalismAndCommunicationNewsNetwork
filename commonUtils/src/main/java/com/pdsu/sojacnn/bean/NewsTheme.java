package com.pdsu.sojacnn.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * <p>
 * 新闻主体
 * </p>
 *
 * @author 半梦
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="NewsTheme对象", description="新闻主体")
public class NewsTheme implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键，查询修改用")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "新闻标题")
    private String title;

    @ApiModelProperty(value = "主题内容，传入byte")
    private byte [] data;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除，0是未删除，1已删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty(value = "类型，比category高，是主类型")
    private Integer contypeId;

    @ApiModelProperty(value = "类别，是最细小的分类，可为null， 为null则属其他")
    private Integer categoryId;

    public NewsTheme copy() {
        try {
            return (NewsTheme) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("bean clone fail", e);
        }
    }

}
