/*
 * DocDoku, Professional Open Source
 * Copyright 2006, 2007, 2008, 2009 DocDoku SARL
 *
 * This file is part of DocDoku.
 *
 * DocDoku is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DocDoku is distributed in the hope that it will be useful,  
 * but WITHOUT ANY WARRANTY; without even the implied warranty of  
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
 * GNU General Public License for more details.  
 *  
 * You should have received a copy of the GNU General Public License  
 * along with DocDoku.  If not, see <http://www.gnu.org/licenses/>.  
 */
package com.docdoku.core.entities.keys;

import java.io.Serializable;

/**
 *
 * @author Florent GARIN
 */
public class WorkspaceUserGroupMembershipKey implements Serializable {

    private String memberWorkspaceId;
    private String memberId;
    private String workspaceId;

    public WorkspaceUserGroupMembershipKey() {
    }

    public WorkspaceUserGroupMembershipKey(String pWorkspaceId, String pMemberWorkspaceId, String pMemberId) {
        workspaceId = pWorkspaceId;
        memberWorkspaceId = pMemberWorkspaceId;
        memberId = pMemberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberWorkspaceId() {
        return memberWorkspaceId;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberWorkspaceId(String memberWorkspaceId) {
        this.memberWorkspaceId = memberWorkspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    

    @Override
    public String toString() {
        return workspaceId + "/" + memberWorkspaceId + "-" + memberId;
    }

    @Override
    public boolean equals(Object pObj) {
        if (this == pObj) {
            return true;
        }
        if (!(pObj instanceof WorkspaceUserGroupMembershipKey)) {
            return false;
        }
        WorkspaceUserGroupMembershipKey key = (WorkspaceUserGroupMembershipKey) pObj;
        return ((key.workspaceId.equals(workspaceId)) && (key.memberWorkspaceId.equals(memberWorkspaceId)) && (key.memberId.equals(memberId)));
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + workspaceId.hashCode();
        hash = 31 * hash + memberWorkspaceId.hashCode();
        hash = 31 * hash + memberId.hashCode();

        return hash;
    }
}