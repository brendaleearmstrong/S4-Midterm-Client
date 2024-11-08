package com.misight.client.model;

public class UserPrivilege {
    private int id;
    private int userId;
    private int privilegeId;

    public UserPrivilege() {}

    public UserPrivilege(int userId, int privilegeId) {
        this.userId = userId;
        this.privilegeId = privilegeId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getPrivilegeId() { return privilegeId; }
    public void setPrivilegeId(int privilegeId) { this.privilegeId = privilegeId; }
}

