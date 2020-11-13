package com.koch.sampleproject;

import com.koch.sampleproject.adapter.changes.GetTestApiResultsUseCase;
import com.koch.sampleproject.adapter.changes.ServerChangeListProvider;
import com.koch.sampleproject.adapter.changes.ChangeDataListener;
import com.koch.sampleproject.model.Change;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleUnitTests {

    private GetTestApiResultsUseCase getTestApiResultsUseCase;

    @Mock
    ServerChangeListProvider serverChangeListProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUseCase() {
        getTestApiResultsUseCase = new GetTestApiResultsUseCase(serverChangeListProvider);
        List<Change> expected = new ArrayList<>();
        expected.add(new Change("test"));

        // TODO: not neccessary?!
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                getTestApiResultsUseCase.changesReceived(expected);
                return null;
            }
        }).when(serverChangeListProvider).fetchChanges(Mockito.any());


        ChangeDataListener testListener = new ChangeDataListener() {
            @Override
            public void onChangesReceived(List<Change> changesList) {
                assertEquals(expected.get(0).getSubject(), changesList.get(0).getSubject());
                assertEquals(expected, changesList);
            }
        };

        getTestApiResultsUseCase.registerListener(testListener);
        getTestApiResultsUseCase.execute();
        Mockito.verify(serverChangeListProvider).fetchChanges(getTestApiResultsUseCase);
    }

}
