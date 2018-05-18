package com.example.sainisagar.project5;
/**
 * Created by Saini Sagar on 2017-12-17.
 */

public class location {
    private String m_name;
    private String m_other;
    private int m_image_id = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public location(String other, String name) {
        m_name = name;
        m_other = other;
    }
    public location(String other, String name, int id){
        this.m_name = name;
        this.m_other = other;
        this.m_image_id = id;
    }
    public String get_name() {
        return m_name;
    }
    public String getM_other() {
        return m_other;
    }
    public int getImageId(){
        return m_image_id;
    }
    public boolean hasImage() {
        return m_image_id != NO_IMAGE_PROVIDED;
    }
}
