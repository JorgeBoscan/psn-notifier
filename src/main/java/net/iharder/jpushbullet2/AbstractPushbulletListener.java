package net.iharder.jpushbullet2;


/**
 *
 * @author Robert.Harder
 */
public class AbstractPushbulletListener implements PushbulletListener {

    public void pushReceived(PushbulletEvent pushEvent) {}

    public void devicesChanged( PushbulletEvent pushEvent ){}

    public void websocketEstablished(PushbulletEvent pushEvent) {}

    
}
