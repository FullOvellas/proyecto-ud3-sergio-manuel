package com.vaultapp.tests.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.vaultapp.login.UserSession;
import com.vaultapp.model.entities.Book;
import com.vaultapp.model.entities.BookVault;
import com.vaultapp.model.entities.User;
import com.vaultapp.model.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class CommandControllerTest {

    private UserSession userSession;
    private User user;

    @Before
    public void setup() {
        userSession = UserSession.getInstance();
        user = new User("root", "root");
        UserRepository.getInstance().add(user);
    }

    @Test
    public void testActionChangeBookStatus() {
        user = UserSession.getInstance().getLoggedUser();
        Book book = new Book("book");
        BookVault bookVault = new BookVault("My Books");
        bookVault.addElement(book);
        user.addVault(bookVault);
        UserSession.getInstance().login(user);
        assertFalse(book.isStatus());

        book.changeStatus();
        UserSession.getInstance().login(user);
        assertTrue(book.isStatus());
    }

}
