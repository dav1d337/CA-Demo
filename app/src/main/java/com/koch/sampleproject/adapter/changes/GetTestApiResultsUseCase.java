package com.koch.sampleproject.adapter.changes;

import com.koch.sampleproject.model.Change;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.inject.Inject;


public class GetTestApiResultsUseCase implements ChangesReceivedCallback {

    private Set<ChangeDataListener> responseListeners = new CopyOnWriteArraySet<>();

    private ServerChangeListProvider serverChangeListProvider;

    @Inject
    public GetTestApiResultsUseCase(ServerChangeListProvider serverChangeListProvider) {
        this.serverChangeListProvider = serverChangeListProvider;
    }

    public void execute() {
        serverChangeListProvider.fetchChanges(this);
    }

    public void registerListener(ChangeDataListener changeDataListener) {
        responseListeners.add(changeDataListener);
    }

    public void unregisterListener(ChangeDataListener changeDataListener) {
        responseListeners.remove(changeDataListener);
    }

    private void notifyListener(List<Change> changesList) {
        responseListeners.forEach(listener -> listener.onChangesReceived(changesList));
    }

    @Override
    public void changesReceived(List<Change> changes) {
        notifyListener(changes);
    }
}
