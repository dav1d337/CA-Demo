package com.koch.sampleproject.adapter.changes;

import com.koch.sampleproject.model.Change;

import java.util.List;

public interface ChangesReceivedCallback {
    void changesReceived(List<Change> changes);
}
