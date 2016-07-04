package org.infobip.mobile.messaging;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import org.infobip.mobile.messaging.util.InternalMessageUtils;

/**
 * Message bundle adapter. Offers convenience methods to extract and save message data to a bundle.
 *
 * @author mstipanov
 * @since 25.03.2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class Message implements Comparable {
    private final Bundle bundle;

    public Message() {
        bundle = new Bundle();
    }

    public Message(Bundle bundle) {
        this.bundle = bundle;
    }

    public static Message copyFrom(Bundle source) {
        Message sourceMessage = new Message(source);

        Message message = new Message();

        message.setSilent(sourceMessage.isSilent());
        message.setFrom(sourceMessage.getFrom());
        message.setMessageId(sourceMessage.getMessageId());
        message.setTitle(sourceMessage.getTitle());
        message.setBody(sourceMessage.getBody());
        message.setSound(sourceMessage.getSound());
        message.setIcon(sourceMessage.getIcon());
        message.setData(sourceMessage.getData());
        message.setReceivedTimestamp(sourceMessage.getReceivedTimestamp());
        message.setSeenTimestamp(sourceMessage.getSeenTimestamp());
        message.setInternalData(sourceMessage.getInternalData());
        message.setCustomPayload(sourceMessage.getCustomPayload());

        return message;
    }

    public String getMessageId() {
        return bundle.getString(Data.MESSAGE_ID.getKey(), null);
    }

    public void setMessageId(String messageId) {
        bundle.putString(Data.MESSAGE_ID.getKey(), messageId);
    }

    public String getFrom() {
        return bundle.getString(Data.FROM.getKey(), null);
    }

    public void setFrom(String from) {
        bundle.putString(Data.FROM.getKey(), from);
    }

    public String getSound() {
        if (isSilent()) {
            return InternalMessageUtils.getSilentSound(this);
        }
        return bundle.getString(Data.SOUND.getKey(), null);
    }

    public void setSound(String sound) {
        if (isSilent()) {
            InternalMessageUtils.setSilentSound(this, sound);
        } else {
            bundle.putString(Data.SOUND.getKey(), sound);
        }
    }

    public String getIcon() {
        return bundle.getString(Data.ICON.getKey(), null);
    }

    public void setIcon(String icon) {
        bundle.putString(Data.ICON.getKey(), icon);
    }

    public String getBody() {
        if (isSilent()) {
            return InternalMessageUtils.getSilentBody(this);
        }
        return bundle.getString(Data.BODY.getKey(), null);
    }

    public void setBody(String body) {
        if (isSilent()) {
            InternalMessageUtils.setSilentBody(this, body);
        } else {
            bundle.putString(Data.BODY.getKey(), body);
        }
    }

    public String getTitle() {
        if (isSilent()) {
            return InternalMessageUtils.getSilentTitle(this);
        }
        return bundle.getString(Data.TITLE.getKey(), null);
    }

    public void setTitle(String title) {
        if (isSilent()) {
            InternalMessageUtils.setSilentTitle(this, title);
        } else {
            bundle.putString(Data.TITLE.getKey(), title);
        }
    }

    public Bundle getData() {
        return bundle.getBundle(Data.DATA.getKey());
    }

    public void setData(Bundle data) {
        bundle.putBundle(Data.DATA.getKey(), data);
    }

    public Bundle getBundle() {
        return bundle;
    }

    public boolean isSilent() {
        return "true".equals(bundle.getString(Data.SILENT.getKey()));
    }

    public void setSilent(boolean silent) {
        bundle.putString(Data.SILENT.getKey(), silent ? "true" : "false");
    }

    public long getReceivedTimestamp() {
        return bundle.getLong(Data.RECEIVED_TIMESTAMP.getKey(), System.currentTimeMillis());
    }

    public void setReceivedTimestamp(long receivedTimestamp) {
        bundle.putLong(Data.RECEIVED_TIMESTAMP.getKey(), receivedTimestamp);
    }

    public long getSeenTimestamp() {
        return bundle.getLong(Data.SEEN_TIMESTAMP.getKey(), 0);
    }

    public void setSeenTimestamp(long receivedTimestamp) {
        bundle.putLong(Data.SEEN_TIMESTAMP.getKey(), receivedTimestamp);
    }

    public String getCustomPayload() {
        return bundle.getString(Data.CUSTOM_PAYLOAD.getKey());
    }

    private void setCustomPayload(String customPayload) {
        bundle.putString(Data.CUSTOM_PAYLOAD.getKey(), customPayload);
    }

    protected String getInternalData() {
        return bundle.getString(Data.INTERNAL_DATA.getKey());
    }

    protected void setInternalData(String data) {
        bundle.putString(Data.INTERNAL_DATA.getKey(), data);
    }

    @Override
    public int compareTo(Object another) {
        if (!(another instanceof Message)) {
            return 1;
        }

        Message message = (Message) another;
        return (int) Math.signum(message.getReceivedTimestamp() - getReceivedTimestamp());
    }

    protected enum Data {
        MESSAGE_ID("gcm.notification.messageId"),
        TITLE("gcm.notification.title"),
        BODY("gcm.notification.body"),
        SOUND("gcm.notification.sound"),
        ICON("gcm.notification.icon"),
        FROM("from"),
        SILENT("gcm.notification.silent"),
        RECEIVED_TIMESTAMP("received_timestamp"),
        SEEN_TIMESTAMP("seen_timestamp"),
        DATA("data"),
        INTERNAL_DATA("internalData"),
        CUSTOM_PAYLOAD("customPayload");

        private final String key;

        Data(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}
