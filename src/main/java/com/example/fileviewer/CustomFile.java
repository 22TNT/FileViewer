package com.example.fileviewer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomFile<T>
{
    /*Data class for use in TableView and ObservableArrayList*/
    /*Thought that getters and setters would work by themselves, they didn't*/
    public T content;
    public String extension;
    public Integer size;
    public CustomFile(T content, String extension, Integer size)
    {
        this.content = content;
        this.extension = extension;
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public T getContent() {
        return content;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
