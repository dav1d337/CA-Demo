package com.koch.sampleproject;

import com.koch.sampleproject.adapter.movies.GetTrendingMoviesUseCase;
import com.koch.sampleproject.model.Movie;
import com.koch.sampleproject.model.MovieResponse;
import com.koch.sampleproject.ui.movies.MoviesViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class MoviesViewModelTests {

    MoviesViewModel viewModel;

    @Mock
    GetTrendingMoviesUseCase useCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new MoviesViewModel(useCase);
    }

    @Test
    public void testRegistry() {
        viewModel.init();
        Mockito.verify(useCase).registerListener(viewModel);
        viewModel.unregisterListener();
        Mockito.verify(useCase).unregisterListener(viewModel);
    }

    @Test
    public void testCallback() throws InterruptedException {

        viewModel.init();
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("test", "test"));
        MovieResponse expectedResponse = new MovieResponse(1, expected, 2, 2);

        viewModel.callApiForTrendingMovies();
        Mockito.verify(useCase).getTrending();




        // TODO
        Thread.sleep(3500);

//        Mockito.doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                return null;
//            }
//        }).when(useCase).trendingMoviesReceived(any());

       // Mockito.verify(useCase).trendingMoviesReceived(any());
        assertEquals(null, viewModel.errorMessage.getValue());
        assertEquals(expectedResponse.getResults(),viewModel.movies.getValue());
    }

    @Test
    public void testError()  {

        viewModel.callApiForTrendingMovies();

        Mockito.doAnswer(invocation -> {
            useCase.trendingMoviesReceived(null);
            return null;
        }).when(useCase).getTrending();

        //TODO

        assertNotEquals(viewModel.errorMessage.getValue(), null);
    }

}
