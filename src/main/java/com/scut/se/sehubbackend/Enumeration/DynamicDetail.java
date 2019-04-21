package com.scut.se.sehubbackend.Enumeration;

/**
 * 用于动态权限的扩展<br/>
 * 在每个部门只有一种动态权限的时候，只需要{@link Department}即可区分不同的动态权限，而一旦有部门需要多种动态权限，就需要添加其他
 * 的区分信息，这也是此枚举的作用，部分描述可见{@link com.scut.se.sehubbackend.Security.Authorization.interfaces.AuthorityMapper#mapDynamic(Department, DynamicDetail)}.
 * 这里的{@code General}只是用于测试，并没有什么其他用处<br/>
 * <b>注意，新添加其他动态权限时，除在本枚举下添加外，还应该在{@link com.scut.se.sehubbackend.Security.Authorization.interfaces.AuthorityMapper}中添加具体的权限映射</b>
 */
public enum DynamicDetail {
    General,
    ReporterApplication,
    NewMediaApplication
}
