package com.boscan.psnnotifier.notification;

import com.boscan.psnnotifier.config.Configuration;
import net.iharder.jpushbullet2.PushbulletClient;
import net.iharder.jpushbullet2.push.NotePush;
import net.iharder.jpushbullet2.push.SendPush;

public class PushBulletNotification implements Notification {

    public void sendNotification(String to, String message) {
        try {
            PushbulletClient pushbulletClient = new PushbulletClient(Configuration.getInstance().getPushbulletAppId());
            SendPush notePush = new NotePush.NotePushBuilder()
                    .email(to)
                    .title("PSN Service")
                    .body(message)
                    .create();
            pushbulletClient.sendPush(notePush);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO
        }
    }
}
