package com.internshipProject.SkillsOverflowBackend.enums;

public enum UsersRoles {

    ADMIN {
        public String toString(){
            return "admin";
        }
    },

    APPROVED_USER {
        public String toString(){
            return "approved user";
        }
    },

    PENDING_USER {
        public String toString(){
            return "pending user";
        }
    },

    BLOCKED_USER {
        public String toString(){
            return "blocked user";
        }
    },

    DECLINED_USER {
        public String toString(){
            return "declined user";
        }
    }
}
