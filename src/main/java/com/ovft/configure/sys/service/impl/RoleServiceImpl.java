package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Permission;
import com.ovft.configure.sys.bean.Role;
import com.ovft.configure.sys.dao.RoleMapper;
import com.ovft.configure.sys.service.RoleService;
import com.ovft.configure.sys.vo.Children;
import com.ovft.configure.sys.vo.MenuConfig;
import com.ovft.configure.sys.vo.PageVo;
import com.ovft.configure.sys.vo.PermissionMenu;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName RoleServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    private RoleMapper roleMapper;

    @Override
    public HashSet<String> selectByAdminId(Integer adminId, Integer schoolId) {
        HashSet<String> set = new HashSet<>();
        List<Role> roles = roleMapper.selectByAdminIdList(adminId, schoolId);
        roles.forEach(role -> {
            List<Permission> permissions = roleMapper.selectPermissionList(role.getRoleId());
            permissions.forEach(permission -> {
                String url = permission.getUrl();
                if(!StringUtils.isEmpty(url)){
                    String[] split = url.split(",");
                    set.addAll(Arrays.asList(split));
                }
            });
        });
        return set;
    }

    @Override
    public WebResult allMenu(Integer adminId, Integer schoolId, Integer role) {
        if(role.equals(0) || role.equals(1)) {
            return new WebResult("200","", "");
        }
        List<Role> roles = roleMapper.selectByAdminIdList(adminId, schoolId);
        Map<String, MenuConfig> menuConfigs = new HashMap<>();
        for (Role role1 : roles) {
            List<Permission> permissions = roleMapper.selectPermissionList(role1.getRoleId());
            for (Permission permission : permissions) {
                MenuConfig config = null;
                HashSet<Children> childrens = null;
                String legend = permission.getRemarks();
                if(menuConfigs.get(legend) != null) {
                    config = menuConfigs.get(legend);
                    if(config.getChildren() != null) {
                        childrens = config.getChildren();
                    } else {
                        childrens = new HashSet();
                    }
                } else {
                    config = new MenuConfig();
                    config.setLegend(legend);
                    childrens = new HashSet();
                }
                Children children = new Children();
                children.setLegend(permission.getPermissionName());
                childrens.add(children);

                config.setChildren(childrens);
                menuConfigs.put(legend, config);
            }
        }
        return new WebResult("200","", menuConfigs.values());
    }

    @Override
    public WebResult roleList(PageVo pageVo) {
        if(pageVo.getPageSize() == 0) {
            List<Role> roles = roleMapper.selectBySchoolIdList(pageVo.getSchoolId());
            return new WebResult("200", "获取角色列表成功", roles);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<Role> roles = roleMapper.selectBySchoolIdList(pageVo.getSchoolId());
        PageInfo pageInfo = new PageInfo<>(roles);
        return new WebResult("200", "获取角色列表成功", pageInfo);
    }

    @Transactional
    @Override
    public WebResult createRole(Role role) {
        if(StringUtils.isBlank(role.getRoleName())) {
            return new WebResult("400", "角色名称不能为空", "");
        }
        if(role.getRoleId() == null) {
            roleMapper.createRole(role);
        } else {
            roleMapper.updateRole(role);
            roleMapper.deleteRolePermission(role.getRoleId());
        }
        Integer[] permisIds = role.getPermisIds();
        for(int i = 0, length = permisIds.length; i < length; i++) {
            roleMapper.insertByRolePermission(role.getRoleId(),permisIds[i]);
        };
        return new WebResult("200", "操作成功", role);
    }

    @Override
    public WebResult findRole(Integer roleId) {
        Role role = roleMapper.selectById(roleId);
        List<Permission> permissions = roleMapper.selectPermissionList(roleId);
        Integer[] perId = new Integer[permissions.size()];
        for(int i = 0, length = permissions.size(); i < length; i++) {
            perId[i] = permissions.get(i).getPermissionId();
        }
        role.setPermisIds(perId);
        return new WebResult("200", "查询成功", role);
    }

    @Transactional
    @Override
    public WebResult deleteRole(Integer roleId) {
        roleMapper.deleteAdminRole(null, roleId, null);
        roleMapper.deleteRolePermission(roleId);
        roleMapper.deleteRole(roleId);
        return new WebResult("200", "删除成功", "");
    }

    @Override
    public WebResult allPermission() {
        List<String> permissions = roleMapper.groupRemark();
        List<PermissionMenu> menus = new LinkedList<>();
        Integer pid = 1000;
        for (int i = 0; i < permissions.size(); i++){
            PermissionMenu menu = new PermissionMenu(pid++, permissions.get(i));
            List<Permission> children = roleMapper.selectPermissionAll(permissions.get(i));
            List<PermissionMenu> chiPer = new LinkedList<>();
            children.forEach(chi -> {
                PermissionMenu cPer = new PermissionMenu(chi.getPermissionId(), chi.getChineseName());
                chiPer.add(cPer);
            });
            menu.setChildren(chiPer);
            menus.add(menu);
            pid++;
        }

        return new WebResult("200", "查询成功", menus);
    }

    @Override
    public WebResult addAdminRole(Integer[] roleIds, Integer adminId, Integer schoolId) {
        roleMapper.deleteAdminRole(adminId, null, schoolId);
        for(int i = 0, length = roleIds.length; i < length; i++) {
            roleMapper.insertAdminRole(adminId, roleIds[i], schoolId);
        }
        return new WebResult("200", "操作成功", "");
    }

}
