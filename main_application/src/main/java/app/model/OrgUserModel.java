package app.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Класс, описывающий объект OrgUserModel
 */
@ApiModel(value = "Пользователь организации")
public class OrgUserModel {
    @ApiModelProperty(name = "id", required = true, value = "Идентификатор пользователя")
    private Integer id;
    @ApiModelProperty(name = "user_name", required = true, value = "Имя пользователя")
    private String user_name;
    @ApiModelProperty(name = "organization_id", required = true, value = "Инедтификатор организации к которой относится пользователь")
    private Integer organization_id;

    public OrgUserModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    @Override
    public String toString() {
        return this.getClass().getAnnotation(ApiModel.class).value() + " [id=" + id + "]";
    }
}
