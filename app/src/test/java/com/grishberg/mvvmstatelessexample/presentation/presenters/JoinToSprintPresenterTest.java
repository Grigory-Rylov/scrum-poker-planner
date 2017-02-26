package com.grishberg.mvvmstatelessexample.presentation.presenters;

import com.grishberg.mvpstatelibrary.framework.state.StateObserver;
import com.grishberg.mvvmstatelessexample.domain.exceptions.AppException.WrongAccessTokenException;
import com.grishberg.mvvmstatelessexample.domain.model.rest.RestError;
import com.grishberg.mvvmstatelessexample.domain.repository.AuthRepository;
import com.grishberg.mvvmstatelessexample.presentation.state.auth.JoinToSprintState.*;
import com.grishberg.mvvmstatelessexample.presentation.state.auth.JointToSprintViewState.*;
import com.grishberg.mvvmstatelessexample.utils.BaseTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by grishberg on 26.02.17.
 */
@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class JoinToSprintPresenterTest extends BaseTestCase {

    private static final String SPRINT_TOKEN = "1234";
    public static final String NAME = "name";
    @Mock
    AuthRepository repository;

    @Mock
    StateObserver stateObserver;

    private JoinToSprintPresenter presenter;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        presenter = new JoinToSprintPresenter();
        presenter.authRepository = repository;
        presenter.subscribe(stateObserver);
    }

    @Test
    public void testOnAuthorizationRequest() {

        presenter.updateState(new JointToSprintRequest(NAME, SPRINT_TOKEN));

        verify(stateObserver, times(1)).onModelUpdated(any(JoinToSprintModelStateProgress.class));
        verify(repository, times(1)).registerMemberToSprint(NAME, SPRINT_TOKEN, presenter);
    }

    @Test
    public void testOnSuccessAuthorization() {

        presenter.updateState(new JointToSprintSuccessResponse());

        verify(stateObserver, never()).onModelUpdated(any(JoinToSprintModelStateProgress.class));
        verify(stateObserver, never()).onModelUpdated(any(JoinToSprintModelStateError.class));
        verify(stateObserver, times(1)).onModelUpdated(any(JoinToSprintModelStateSuccess.class));
    }

    @Test
    public void testNotSuccessAuthorization() {

        RestError error = mock(RestError.class);
        when(error.getMessage()).thenReturn("message");
        when(error.getCode()).thenReturn(1000);
        WrongAccessTokenException exception = new WrongAccessTokenException(error);

        presenter.updateState(new JointToSprintFailResponse(exception));

        verify(stateObserver, never()).onModelUpdated(any(JoinToSprintModelStateProgress.class));
        verify(stateObserver, never()).onModelUpdated(any(JoinToSprintModelStateSuccess.class));
        verify(stateObserver, times(1)).onModelUpdated(any(JoinToSprintModelStateError.class));
    }
}