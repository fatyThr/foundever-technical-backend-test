package com.founderever.technical.backend.infrastructure.utils.enums;

public enum ChannelEnum {
    EMAIL,SMS,FACEBOOK,TWITTER,UNKNOWN;
    public static ChannelEnum fromValue(String value) {
        for (ChannelEnum channelEnum : values()) {
            if (channelEnum.name().equalsIgnoreCase(value)) {
                return channelEnum;
            }
        }
        return UNKNOWN;
    }
}
