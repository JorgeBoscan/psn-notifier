package net.iharder.jpushbullet2.push;

import com.google.gson.annotations.Expose;
import net.iharder.jpushbullet2.PushbulletException;

public class LinkPush implements SendPush {

    @Expose
    private final String device_iden;
    @Expose
    private final String email;
    @Expose
    private final String channel_tag;
    @Expose
    private final String client_iden;
    @Expose
    private final String type = "link";
    @Expose
    private final String title;
    @Expose
    private final String body;
    @Expose
    private final String url;

    private LinkPush(LinkPushBuilder builder) {
        this.device_iden = builder.device_iden;
        this.email = builder.email;
        this.title = builder.title;
        this.body = builder.body;
        this.url = builder.url;
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

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUrl() {
        return url;
    }

    public static class LinkPushBuilder extends BaseBuilder {
        private String title;
        private String body;
        private String url;

        public LinkPushBuilder channelTag(final String channelTag) {
            this.channel_tag = channelTag;
            return this;
        }

        public LinkPushBuilder clientIden(final String clientIden) {
            this.client_iden = clientIden;
            return this;
        }

        public LinkPushBuilder url(final String url) {
            this.url = url;
            return this;
        }

        public LinkPushBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public LinkPushBuilder body(final String body) {
            this.body = body;
            return this;
        }

        public LinkPushBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public LinkPushBuilder deviceIden(final String deviceIden) {
            this.device_iden = deviceIden;
            return this;
        }

        public SendPush create() throws PushbulletException {
            validateNumberOfTargets();
            return new LinkPush(this);
        }
    }
}
