
package com.android.mh.telstra.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/**
 * Pojo class of json data
 */
public class AboutCanada {

    @SerializedName("title")
    private String title;
    @SerializedName("rows")
    private List<Row> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

}
