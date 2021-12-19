package com.ecms.ndmecms;

public class CardItems {


    private String mTextResource;
    private String[] mTitleResource;

    public CardItems(String[] title, String text) {
        mTitleResource = title;
        mTextResource = text;
    }


    public String getText() {
        return mTextResource;
    }
    public String[] getTitle(){
        return mTitleResource;
    }
}
