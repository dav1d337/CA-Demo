package com.koch.sampleproject.adapter.changes;

public interface ServerChangeListProvider {
    void fetchChanges(ChangesReceivedCallback useCaseCallback);
}
