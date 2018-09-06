package com.telerikacademy.ngpuppies.services;

import com.mchange.rmi.NotAuthorizedException;
import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Client;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.repositories.base.BillRepository;
import com.telerikacademy.ngpuppies.repositories.base.ClientRepository;
import com.telerikacademy.ngpuppies.repositories.base.SubscriberRepository;
import com.telerikacademy.ngpuppies.security.services.base.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTests {
    @Mock
    private ClientRepository mockRepository;
    @Mock
    private BillRepository mockBillRepository;
    @Mock
    private SubscriberRepository mockSubscriberRepositroy;
    @Mock
    private TokenService<HttpServletRequest> mockTokenService;



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
        Assert.assertEquals(subscribers.get(2).getPhoneNumber(),actualResult.get(2).getPhoneNumber());


    }

    @Test
    public void getSubscriber_checkIfCorrectSubscriber(){
        Subscriber subscriber = new Subscriber("0887161737","Test","Tester5","1234567895",null,null);

        when(mockSubscriberRepositroy.getById("0887161737"))
                .thenReturn(subscriber);

        Subscriber tester = mockSubscriberRepositroy.getById("0887161737");

        Assert.assertEquals("0887161737",tester.getPhoneNumber());


    }
    @Test
    public void payBill_checkIfUsernameMatches() throws NotAuthorizedException {
        HttpServletRequest req = null;
        Bill bill = mock(Bill.class);
        Subscriber subscriber = mock(Subscriber.class);
        Client client = mock(Client.class);
        when(mockTokenService.getUsernameFromToken(req)).thenReturn("dsk_bank");
        when(bill.getSubscriber()).thenReturn(subscriber);
        when(subscriber.getBank()).thenReturn(client);
        when(client.getUsername()).thenReturn("dsk_bank");
        when(mockBillRepository.getById(1)).thenReturn(bill);

        clientService.payBill(1,req);

        verify(mockRepository).payBill(1);
        verify(mockTokenService, times(1)).getUsernameFromToken(req);
        verify(mockBillRepository, times(1)).getById(1);

    }

    @Test(expected = NotAuthorizedException.class)
    public void payBill_checkIfThrowsNotAuthorizedException() throws NotAuthorizedException {
        HttpServletRequest req = null;
        Bill bill = mock(Bill.class);
        Subscriber subscriber = mock(Subscriber.class);
        Client client = mock(Client.class);
        when(mockTokenService.getUsernameFromToken(req)).thenReturn("dsk_bank");
        when(bill.getSubscriber()).thenReturn(subscriber);
        when(subscriber.getBank()).thenReturn(client);
        when(client.getUsername()).thenReturn("not_dsk_bank");
        when(mockBillRepository.getById(1)).thenReturn(bill);

        clientService.payBill(1,req);

        verify(mockRepository,never()).payBill(1);


    }

    @Test
    public void getUnpaidBills_testIfIdIsEqual(){
        clientService.getUnpaidBills(1);
        verify(mockBillRepository, times(1)).getUnpaidBills(1);
    }

    @Test
    public void getPaymentHistory_testIfIdIsEqual(){
        clientService.getPaymentHistory(1);
        verify(mockBillRepository).getPaymentHistory(1);
    }
















}
