package net.iharder.jpushbullet2.push;

import com.google.gson.annotations.Expose;
import net.iharder.jpushbullet2.PushbulletException;

public class AddressPush implements SendPush {
    @Expose
    private final String device_iden;
    @Expose
    private final String email;
    @Expose
    private final String type = "address";
    @Expose
    private final String name;
    @Expose
    private final String address;
    @Expose
    private final String channel_tag;
    @Expose
    private final String client_iden;

    public AddressPush(AddressPushBuilder builder) {
        this.device_iden = builder.device_iden;
        this.email = builder.email;
        this.name = builder.name;
        this.address = builder.address;
        this.channel_tag = builder.channel_tag;
        this.client_iden = builder.client_iden;
    }

    public String getChannel_tag() {
        return channel_tag;
    }

    public String getClient_iden() {
        return client_iden;
    }

    public String getDevice_iden() {
        return device_iden;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public static class AddressPushBuilder extends BaseBuilder {
        private String name;
        private String address;

        public AddressPushBuilder channelTag(final String channelTag) {
            this.channel_tag = channelTag;
            return this;
        }

        public AddressPushBuilder clientIden(final String clientIden) {
            this.client_iden = clientIden;
            return this;
        }

        public AddressPushBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public AddressPushBuilder address(final String address) {
            this.address = address;
            return this;
        }

        public AddressPushBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public AddressPushBuilder deviceIden(final String deviceIden) {
            this.device_iden = deviceIden;
            return this;
        }

        public SendPush create() throws PushbulletException {
            validateNumberOfTargets();
            return new AddressPush(this);
        }


    }
}
