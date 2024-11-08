package com.misight.client.auth;

import com.misight.client.model.Privileges;
import com.misight.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleBasedMenuManager {
    private final AuthenticationService authService;
    private final Map<String, Set<String>> privilegeMenuMap;

    @Autowired
    public RoleBasedMenuManager(AuthenticationService authService) {
        this.authService = authService;
        this.privilegeMenuMap = initializeMenuMap();
    }

    private Map<String, Set<String>> initializeMenuMap() {
        Map<String, Set<String>> menuMap = new HashMap<>();

        // Admin privileges
        menuMap.put("ADMIN", new HashSet<>(Arrays.asList(
                "user-management",
                "mine-management",
                "monitoring-management",
                "reporting-all",
                "system-settings"
        )));

        // Mine Admin privileges
        menuMap.put("MINE_ADMIN", new HashSet<>(Arrays.asList(
                "mine-data",
                "environmental-data",
                "safety-data",
                "mine-reporting"
        )));

        // Stakeholder privileges
        menuMap.put("STAKEHOLDER", new HashSet<>(Arrays.asList(
                "view-mine-data",
                "view-environmental-data",
                "view-safety-data",
                "view-reports"
        )));

        return menuMap;
    }

    public Set<String> getAuthorizedMenuItems() {
        Optional<User> currentUser = authService.getCurrentUser();
        if (!currentUser.isPresent()) {
            return Collections.emptySet();
        }

        Set<String> authorizedMenus = new HashSet<>();
        for (Privileges privilege : currentUser.get().getPrivileges()) {
            Set<String> menus = privilegeMenuMap.get(privilege.getName());
            if (menus != null) {
                authorizedMenus.addAll(menus);
            }
        }

        return authorizedMenus;
    }

    public boolean hasAccess(String menuItem) {
        return getAuthorizedMenuItems().contains(menuItem);
    }

    public boolean hasAnyPrivilege(String... privileges) {
        Optional<User> currentUser = authService.getCurrentUser();
        if (!currentUser.isPresent()) {
            return false;
        }

        Set<String> userPrivileges = new HashSet<>();
        for (Privileges privilege : currentUser.get().getPrivileges()) {
            userPrivileges.add(privilege.getName());
        }

        for (String privilege : privileges) {
            if (userPrivileges.contains(privilege)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAllPrivileges(String... privileges) {
        Optional<User> currentUser = authService.getCurrentUser();
        if (!currentUser.isPresent()) {
            return false;
        }

        Set<String> userPrivileges = new HashSet<>();
        for (Privileges privilege : currentUser.get().getPrivileges()) {
            userPrivileges.add(privilege.getName());
        }

        return Arrays.stream(privileges).allMatch(userPrivileges::contains);
    }
}