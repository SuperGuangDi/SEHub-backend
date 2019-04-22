package com.scut.se.sehubbackend.Security.Authorization.interfaces;

import com.scut.se.sehubbackend.Enumeration.Department;
import com.scut.se.sehubbackend.Enumeration.DynamicDetail;
import com.scut.se.sehubbackend.Enumeration.Position;
import com.scut.se.sehubbackend.Security.Authorization.HashAuthorityMapper;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * 由字符串构建权限的服务<br/>
 * 应当实现一个由输入字符串返回相应权限的功能<br/>
 * <b>注意，所有权限都应当通过{@code mapper}提供的方法获取而不是手动创建，这样可以实现权限描述的规范化</b><br/>
 * 提供了一个基本实现{@link HashAuthorityMapper}
 */
public interface AuthorityMapper {

    /**
     * 直接由字符串构建权限，自由度较大
     * @param authority 输入的字符串
     * @return 构建出的权限
     */
    GrantedAuthority map(String authority);

    /**
     * 根据职位和部门生成对应的权限，一般来说生成的权限是描述职位、部门这些固定信息，是不可动态修改的
     * @param position 权限对应的职位
     * @param department 权限对应的部门
     * @return 构造出的权限
     */
    GrantedAuthority map(Position position,Department department);

    /**
     * 用于构建动态权限<br/>
     * 这里末尾的{@code detail}是为了后续的扩展。因为目前每个部门至多只有一种动态权限
     * 如接受其他部门的申请，所以默认传入{@code null}即可，但若后续一个部门有多于一种
     * 的动态权限时，扩展{@link DynamicDetail}即可
     * @param department 动态权限所属的部门
     * @param detail 用于标志同一部门的不同权限，目前因每个部门只有至多一种动态权限，传入{@code null}即可
     * @return 构造出的动态权限
     */
    GrantedAuthority mapDynamic(Department department, DynamicDetail detail);

    Set<GrantedAuthority> mapAllDynamic(Department department);
}
