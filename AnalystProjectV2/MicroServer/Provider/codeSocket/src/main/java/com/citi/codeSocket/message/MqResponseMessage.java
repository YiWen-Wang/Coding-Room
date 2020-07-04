package com.citi.codeSocket.message;

public class MqResponseMessage {

        private String language;
        private String type;
        private String content;


        public MqResponseMessage(){

        }


        public MqResponseMessage(String language, String type, String content) {
            this.language = language;
            this.type = type;
            this.content = content;
        }


        public String getType() {
            return type;
        }

        public String getContent() {
            return content;
        }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

        public void setType(String type) {
            this.type = type;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }
