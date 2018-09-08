package com.telerikacademy.ngpuppies.services;

import com.telerikacademy.ngpuppies.models.Admin;
import com.telerikacademy.ngpuppies.models.Client;
import com.telerikacademy.ngpuppies.repositories.base.*;
import com.telerikacademy.ngpuppies.services.base.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTests {
    @Mock
    private AdminRepository mockAdminRepository;
    @Mock
    private ServiceRepository mockServiceRepository;
    @Mock
    private ClientRepository mockClientRepository;
    @Mock
    private CurrencyRepository mockCurrencyRepository;
    @Mock
    private BillRepository mockBillRepository;
    @Mock
    private SubscriberRepository mockSubscriberRepository;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private AddressRepository mockAddressRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    public void createAdmin_checkIfFieldsChanged(){
        Admin admin = mock(Admin.class);

        adminService.create(admin);

        verify(mockAdminRepository).create(admin);
        verify(admin).setEnabled(true);
        verify(admin).setFirstLogin(true);
        verify(admin).setPassword(mockPasswordEncoder.encode(admin.getPassword()));


    }

    @Test
    public void createClient_checkIfFieldsChanged(){
        Client client = mock(Client.class);

        adminService.create(client);

        verify(mockClientRepository).create(client);
        verify(client).setEnabled(true);
        verify(client).setFirstLogin(false);
        verify(client).setPassword(mockPasswordEncoder.encode(client.getPassword()));
    }

    @Test
    public void adminChangePassword_checkIfFieldsChanged(){
        Admin admin = mock(Admin.class);

        when(mockAdminRepository.getById(1)).thenReturn(admin);

        adminService.changePassword(1,"password");
        verify(admin).setFirstLogin(false);
        verify(admin).setPassword(mockPasswordEncoder.encode("password"));
        verify(mockAdminRepository).update(1,admin);

    }



}
