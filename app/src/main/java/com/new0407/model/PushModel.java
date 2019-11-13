package com.new0407.model;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PushModel {
        public String display_type;
        public Map<String, String> extra;
        public String msg_id;
        public Map<String, String> body;
        public String message_id;
        public String task_id;

        public String alias;
        public String ticker;
        public String title;
        public String text;
        public boolean play_vibrate;
        public boolean play_lights;
        public boolean play_sound;
        public boolean screen_on;
        public String after_open;
        public String custom;
        public String url;
        public String sound;
        public String img;
        public String icon;
        public String activity;
        public String recall;
        public String bar_image;
        public String expand_image;
        public boolean isAction;
        public String pulled_service;
        public String pulled_package;
        public String pulledWho;
        public int builder_id;
        public String largeIcon;
        public long random_min;
        public boolean clickOrDismiss;

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isPlay_vibrate() {
        return play_vibrate;
    }

    public void setPlay_vibrate(boolean play_vibrate) {
        this.play_vibrate = play_vibrate;
    }

    public boolean isPlay_lights() {
        return play_lights;
    }

    public void setPlay_lights(boolean play_lights) {
        this.play_lights = play_lights;
    }

    public boolean isPlay_sound() {
        return play_sound;
    }

    public void setPlay_sound(boolean play_sound) {
        this.play_sound = play_sound;
    }

    public boolean isScreen_on() {
        return screen_on;
    }

    public void setScreen_on(boolean screen_on) {
        this.screen_on = screen_on;
    }

    public String getAfter_open() {
        return after_open;
    }

    public void setAfter_open(String after_open) {
        this.after_open = after_open;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getRecall() {
        return recall;
    }

    public void setRecall(String recall) {
        this.recall = recall;
    }

    public String getBar_image() {
        return bar_image;
    }

    public void setBar_image(String bar_image) {
        this.bar_image = bar_image;
    }

    public String getExpand_image() {
        return expand_image;
    }

    public void setExpand_image(String expand_image) {
        this.expand_image = expand_image;
    }

    public boolean isAction() {
        return isAction;
    }

    public void setAction(boolean action) {
        isAction = action;
    }

    public String getPulled_service() {
        return pulled_service;
    }

    public void setPulled_service(String pulled_service) {
        this.pulled_service = pulled_service;
    }

    public String getPulled_package() {
        return pulled_package;
    }

    public void setPulled_package(String pulled_package) {
        this.pulled_package = pulled_package;
    }

    public String getPulledWho() {
        return pulledWho;
    }

    public void setPulledWho(String pulledWho) {
        this.pulledWho = pulledWho;
    }

    public int getBuilder_id() {
        return builder_id;
    }

    public void setBuilder_id(int builder_id) {
        this.builder_id = builder_id;
    }

    public String getLargeIcon() {
        return largeIcon;
    }

    public void setLargeIcon(String largeIcon) {
        this.largeIcon = largeIcon;
    }

    public long getRandom_min() {
        return random_min;
    }

    public void setRandom_min(long random_min) {
        this.random_min = random_min;
    }

    public boolean isClickOrDismiss() {
        return clickOrDismiss;
    }

    public void setClickOrDismiss(boolean clickOrDismiss) {
        this.clickOrDismiss = clickOrDismiss;
    }
}
