package com.citi.codeOnline.microServer.provider.Chat.message;

public class MqResponseMessage {

        private String sender;
        private String type;
        private String content;


        public MqResponseMessage(){

        }


        public MqResponseMessage(String sender, String type, String content) {
            this.sender = sender;
            this.type = type;
            this.content = content;
        }

        public String getSender() {
            return sender;
        }

        public String getType() {
            return type;
        }

        public String getContent() {
            return content;
        }


        public void setSender(String sender) {
            this.sender = sender;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }
