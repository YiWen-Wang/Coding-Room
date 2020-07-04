package com.citi.codeOnline.service;

import com.citi.codeOnline.entity.Sender;
import com.citi.codeOnline.message.MqRequestMessage;
import com.citi.codeOnline.tools.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MQService {


    @Autowired
    Sender sender;

    @Async
    public void testAsync() {
        MqRequestMessage mqTask = new MqRequestMessage(  );
        for(int i=0;i<6;i++) {
            mqTask.setRoom( "123");
            mqTask.setSender("000");
            mqTask.setType( "2" );
            mqTask.setContent("this:"+i);
            sender.send( JsonUtils.objectToJson( mqTask ) );
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
