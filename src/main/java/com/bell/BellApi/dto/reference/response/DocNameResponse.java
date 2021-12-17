package com.bell.BellApi.dto.reference.response;

import com.bell.BellApi.model.Country;
import com.bell.BellApi.model.DocName;

import java.util.ArrayList;
import java.util.List;

public class DocNameResponse {

    private String name;

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static DocNameResponse mapToDto(DocName docName){
        DocNameResponse docNameResponse = new DocNameResponse();
        docNameResponse.setCode(docName.getDocCode());
        docNameResponse.setName(docName.getDocName());
        return docNameResponse;
    }

    public static List<DocNameResponse> mapToListDto(List<DocName> docName){
        List<DocNameResponse> list = new ArrayList<>();
        for(DocName d : docName){
            list.add(mapToDto(d));
        }
        return list;
    }
}
