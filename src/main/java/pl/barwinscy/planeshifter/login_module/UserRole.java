package pl.barwinscy.planeshifter.login_module;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.barwinscy.planeshifter.login_module.UserPermission.*;

public enum UserRole {

    USER(Sets.newHashSet(PLAYER)),
    USER_PREMIUM(Sets.newHashSet(PLAYER, PREMIUM_PLAYER)),
    ADMIN(Sets.newHashSet(UserPermission.PLAYER, PREMIUM_PLAYER, ADMIN_PERMISSION, ACCESS_H2));

    final private Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
