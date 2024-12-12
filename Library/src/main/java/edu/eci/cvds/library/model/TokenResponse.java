package edu.eci.cvds.library.model;

public class TokenResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public boolean isValid() {
        long currentTime = System.currentTimeMillis()/1000;
        return currentTime < data.getExp();
    }
    
    public static class Data {
        private String username;
        private String role;
        private String id;
        private long iat;
        private long exp;

        // Getters y setters para los campos
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getIat() {
            return iat;
        }

        public void setIat(long iat) {
            this.iat = iat;
        }

        public long getExp() {
            return exp;
        }

        public void setExp(long exp) {
            this.exp = exp;
        }


    }
}
