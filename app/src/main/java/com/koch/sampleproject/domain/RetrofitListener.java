package com.koch.sampleproject.domain;

import com.koch.sampleproject.model.Change;

import java.util.List;


// TODO: abstract Listener -> "DataListener" -> adpater package
public interface RetrofitListener {
    void onChangesReceived(List<Change> changesList);
}
