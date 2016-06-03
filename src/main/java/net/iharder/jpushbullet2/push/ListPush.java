package net.iharder.jpushbullet2.push;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ListPush implements SendPush {

    @Expose
    private final String device_iden;
    @Expose
    private final String email;
    @Expose
    private final String channel_tag;
    @Expose
    private final String client_iden;
    @Expose
    private final String type = "list";
    @Expose
    private final String title;
    @Expose
    private final List<String> items;

    public ListPush(ListPushBuilder builder) {
        this.device_iden = builder.device_iden;
        this.email = builder.email;
        this.title = builder.title;
        this.items = builder.items;
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

    public List<String> getItems() {
        return items;
    }

    public static class ListPushBuilder {
        private String device_iden;
        private String email;
        private String channel_tag;
        private String client_iden;
        private String title;
        private List<String> items = new ArrayList<String>();

        public ListPushBuilder channelTag(final String channelTag) {
            this.channel_tag = channelTag;
            return this;
        }

        public ListPushBuilder clientIden(final String clientIden) {
            this.client_iden = clientIden;
            return this;
        }

        public ListPushBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public ListPushBuilder item(final String item) {
            items.add(item);
            return this;
        }

        public ListPushBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public ListPushBuilder deviceIden(final String deviceIden) {
            this.device_iden = deviceIden;
            return this;
        }

        public SendPush create() {
            return new ListPush(this);
        }
    }
}
