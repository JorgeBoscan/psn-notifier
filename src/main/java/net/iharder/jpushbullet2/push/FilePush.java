package net.iharder.jpushbullet2.push;

import com.google.gson.annotations.Expose;
import net.iharder.jpushbullet2.PushbulletException;

import java.io.File;

public class FilePush implements SendPush {

    @Expose
    private final String device_iden;
    @Expose
    private final String email;
    @Expose
    private final String channel_tag;
    @Expose
    private final String client_iden;
    @Expose
    private final String type = "file";
    @Expose
    private final String file_name;
    @Expose
    private final String file_type;
    @Expose
    private final String file_url;
    @Expose
    private final String body;

    private final File file;

    private FilePush(FilePushBuilder builder) {
        this.device_iden = builder.device_iden;
        this.email = builder.email;
        this.body = builder.body;
        this.file = builder.file;
        this.file_name = builder.file_name;
        this.file_type = builder.file_type;
        this.file_url = builder.file_url;
        this.channel_tag = builder.channel_tag;
        this.client_iden = builder.client_iden;
    }

    public String getChannel_tag() {
        return channel_tag;
    }

    public String getClient_iden() {
        return client_iden;
    }

    public File getFile() {
        return file;
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

    public String getFile_name() {
        return file_name;
    }

    public String getFile_type() {
        return file_type;
    }

    public String getFile_url() {
        return file_url;
    }

    public String getBody() {
        return body;
    }

    public static class FilePushBuilder extends BaseBuilder {
        private String body;
        private String file_name;
        private String file_type;
        private String file_url;
        private File file;

        public FilePushBuilder channelTag(final String channelTag) {
            this.channel_tag = channelTag;
            return this;
        }

        public FilePushBuilder clientIden(final String clientIden) {
            this.client_iden = clientIden;
            return this;
        }

        public FilePushBuilder fileName(final String fileName) {
            this.file_name = fileName;
            return this;
        }

        public FilePushBuilder fileType(final String fileType) {
            this.file_type = fileType;
            return this;
        }

        public FilePushBuilder fileUrl(final String fileUrl) {
            this.file_url = fileUrl;
            return this;
        }

        public FilePushBuilder file(final File file) {
            this.file = file;
            return this;
        }

        public FilePushBuilder body(final String body) {
            this.body = body;
            return this;
        }

        public FilePushBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public FilePushBuilder deviceIden(final String deviceIden) {
            this.device_iden = deviceIden;
            return this;
        }

        public SendPush create() throws PushbulletException {
            validateNumberOfTargets();
            return new FilePush(this);
        }
    }
}
