package com.sys.enums;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/04/18:41
 * @Description:
 */
public enum SessionEnum {
    SESSION_EMAIL_CODE_NAME("sessionEmailCodeName"),
    SESSION_PIC_CODE_NAME("sessionPicCodeName"),
    SESSION_LOGIN_NAME("sessionLoginName");

    private String value;

    SessionEnum() {
    }

    SessionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
