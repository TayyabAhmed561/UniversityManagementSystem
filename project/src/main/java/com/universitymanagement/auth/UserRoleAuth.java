package com.universitymanagement.auth;

public class UserRoleAuth {

    public enum Role {
        ADMIN, STUDENT, FACULTY, GUEST
    }

    private static Role currentUserRole = Role.GUEST; // Default role is GUEST

    public static void setUserRole(Role role) {
        currentUserRole = role;
        System.out.println("User role set to: " + currentUserRole.name());
    }

    public static Role getUserRole() {
        System.out.println("Current user role: " + currentUserRole.name());
        return currentUserRole;
    }

    public static String getUserRoleAsString() {
        return currentUserRole.name();
    }
}
