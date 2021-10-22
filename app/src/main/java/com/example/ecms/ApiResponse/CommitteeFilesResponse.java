package com.example.ecms.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommitteeFilesResponse {

    @SerializedName("FileSys")
    @Expose
    public List<FileSy> fileSys;

    public List<FileSy> getFileSys() {
        return fileSys;
    }

    public void setFileSys(List<FileSy> fileSys) {
        this.fileSys = fileSys;
    }


    public class FileSy{

        @SerializedName("Name")
        @Expose
        public String name;
        @SerializedName("Value")
        @Expose
        public String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
