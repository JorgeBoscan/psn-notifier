package net.iharder.jpushbullet2;

public class Contact {

    private boolean active;
    private String iden;
    private double created;
    private double modified;
    private String name;
    private String email;
    private String email_normalized;
    private String image_url;
    private String status;

    public boolean isActive() {
        return active;
    }

    public String getIden() {
        return iden;
    }

    public double getCreated() {
        return created;
    }

    public double getModified() {
        return modified;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail_normalized() {
        return email_normalized;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getStatus() {
        return status;
    }
}
