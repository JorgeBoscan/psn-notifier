package net.iharder.jpushbullet2.push;

import net.iharder.jpushbullet2.PushbulletException;

public abstract class BaseBuilder {

    protected String device_iden;
    protected String email;
    protected String channel_tag;
    protected String client_iden;

    protected void validateNumberOfTargets() throws PushbulletException {
        int count = 0;
        if (device_iden != null)
            count++;
        if (email != null)
            count++;
        if (channel_tag != null)
            count++;
        if (client_iden != null)
            count++;
        if (count > 1)
            throw new PushbulletException("At most one target (email, device_iden, client_iden, channel_tag) can be specified for a push.");
    }

    public abstract SendPush create() throws PushbulletException;
}
