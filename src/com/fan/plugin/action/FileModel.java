package com.fan.plugin.action;

/**
 * 请填写类的描述
 *
 * @author maxiaoyao
 * @date 2019-08-20 18:27
 */
public enum FileModel {
    XML(".xml"),
    JAVA(".java"),
    ;
    private String postfix;

    FileModel(String postfix) {

        this.postfix = postfix;

    }

    public String getPostfix() {
        return postfix;
    }}
