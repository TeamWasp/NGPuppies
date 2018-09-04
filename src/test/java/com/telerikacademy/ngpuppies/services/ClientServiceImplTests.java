package com.telerikacademy.ngpuppies.services;

import com.mchange.rmi.NotAuthorizedException;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.repositories.base.BillRepository;
import com.telerikacademy.ngpuppies.repositories.base.ClientRepository;
import com.telerikacademy.ngpuppies.repositories.base.SubscriberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTests {
    @Mock
    private ClientRepository repository;
    @Mock
    private BillRepository billRepository;
    @Mock
    private SubscriberRepository mockSubscriberRepositroy;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void getAllSubscribers_checkIfCountMatches(){
        List<Subscriber> subscribers = Arrays.asList(
                new Subscriber("0887161733","Test","Tester1","1234567891",null,null),
                new Subscriber("0887161734","Test","Tester2","1234567892",null,null),
                new Subscriber("0887161735","Test","Tester3","1234567893",null,null),
                new Subscriber("0887161736","Test","Tester4","1234567894",null,null),
                new Subscriber("0887161737","Test","Tester5","1234567895",null,null)

        );

        when(mockSubscriberRepositroy.getAll(1)).thenReturn(subscribers);

        List<Subscriber> actualResult = clientService.getAllSubscribers(1);

        Assert.assertEquals(5,actualResult.size());


    }

    @Test
    public void getSubscriber_checkIfCorrectSubscriber() throws NotAuthorizedException {
        Subscriber subscriber = new Subscriber("0887161737","Test","Tester5","1234567895",null,null);

        when(mockSubscriberRepositroy.getById("0887161737"))
                .thenReturn(subscriber);

        Subscriber tester = mockSubscriberRepositroy.getById("0887161737");

        Assert.assertEquals("0887161737",tester.getPhoneNumber());


    }






}
