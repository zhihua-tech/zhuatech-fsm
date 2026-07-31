/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.fsm.model;
import jakarta.persistence.*;
@Entity @Table(name="fsm_user")
public class UserAccount extends BaseEntity {
    public enum Role { ADMIN, SERVICE_DISPATCHER, FIELD_TECHNICIAN, QUALITY }
    @Column(nullable=false,unique=true,length=32) private String username; @Column(nullable=false) private String password;
    @Column(nullable=false,length=50) private String fullName; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Role role;
    @Column(name="service_region_code",length=32) private String serviceRegionCode; @Column(nullable=false) private boolean enabled=true;
    protected UserAccount(){}
    public UserAccount(String username,String password,String fullName,Role role,String serviceRegionCode){this.username=username;this.password=password;this.fullName=fullName;this.role=role;this.serviceRegionCode=serviceRegionCode;}
    public String getUsername(){return username;} public String getPassword(){return password;} public String getFullName(){return fullName;} public Role getRole(){return role;} public String getServiceRegionCode(){return serviceRegionCode;} public boolean isEnabled(){return enabled;}
}
