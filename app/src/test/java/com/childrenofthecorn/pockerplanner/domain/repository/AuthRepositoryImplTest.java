package com.childrenofthecorn.pockerplanner.domain.repository;

import com.childrenofthecorn.pockerplanner.domain.model.UserContainer;
import com.childrenofthecorn.pockerplanner.domain.model.rest.RestResponse;
import com.childrenofthecorn.pockerplanner.domain.storage.AuthTokenStorage;
import com.childrenofthecorn.pockerplanner.presentation.state.auth.JointToSprintViewState.JoinToSprintModelStateSuccess;
import com.childrenofthecorn.pockerplanner.utils.BaseTestCase;
import com.childrenofthecorn.pockerplanner.utils.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by grishberg on 26.02.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthRepositoryImplTest extends BaseTestCase {

    private static final String SPRINT_TOKEN = "1245";
    private static final String NAME = "name";
    @Rule
    // Must be added to every test class that targets app code that uses RxJava
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();
    @Mock
    Api api;
    @Mock
    AuthTokenStorage authTokenStorage;
    @Mock
    RestResponse<UserContainer> response;
    AuthRepository authRepository;
    private CountDownLatch lock = new CountDownLatch(1);

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        authRepository = new AuthRepositoryImpl(api, authTokenStorage);
    }

    @Test
    public void testRequestAuthorizationWithValidToken() throws Exception{
        UserContainer userContainer = mock(UserContainer.class);
        when(response.getData()).thenReturn(userContainer);
        when(api.registerMember(NAME, SPRINT_TOKEN))
                .thenReturn(Observable.just(response));

        authRepository.registerMemberToSprint(NAME, SPRINT_TOKEN,
                presenterState -> {
                    assertTrue(presenterState instanceof JoinToSprintModelStateSuccess);
                    lock.countDown();
                });

        lock.await(1, TimeUnit.SECONDS);
    }
}