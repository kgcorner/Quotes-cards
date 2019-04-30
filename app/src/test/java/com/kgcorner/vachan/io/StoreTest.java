package com.kgcorner.vachan.io;

import com.kgcorner.sdk.models.Quote;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.internal.OsResults;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StoreTest {

    private Realm mockedRealm;
    private Store store;

    @Before
    public void setUp() throws Exception {
        mockedRealm = Mockito.mock(Realm.class);
        store = new Store(mockedRealm);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isFavourite() {
        Quote quote = new Quote();
        quote.setId(1);

        RealmQuery<QuoteRealMObject> query = Mockito.mock(RealmQuery.class);
        RealmQuery<QuoteRealMObject> filteredQuery = Mockito.mock(RealmQuery.class);
        Mockito.when(mockedRealm.where(QuoteRealMObject.class)).thenReturn(query);
        Mockito.when(query.equalTo(QuoteRealMObject.ID, 1)).thenReturn(filteredQuery);
        Mockito.when(filteredQuery.findFirst()).thenReturn(new QuoteRealMObject(quote));
        boolean isFavourite = store.isFavourite(quote);
        Assert.assertTrue("quote is not favourite, when it is set as favourite",
                isFavourite);

        Mockito.when(mockedRealm.where(QuoteRealMObject.class)).thenReturn(query);
        Mockito.when(query.equalTo(QuoteRealMObject.ID, 1)).thenReturn(filteredQuery);
        Mockito.when(filteredQuery.findFirst()).thenReturn(null);
        isFavourite = store.isFavourite(quote);
        Assert.assertFalse("quote is favourite when its not set as favourite",
                isFavourite);
    }

    @Test
    public void getFavouriteQuotes() {
        List<QuoteRealMObject> objects = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            QuoteRealMObject object = new QuoteRealMObject(new Quote());
            objects.add(object);
        }
        Iterator<QuoteRealMObject> resultIterator = objects.iterator();
        RealmResults<QuoteRealMObject>result = Mockito.mock(RealmResults.class);
        RealmQuery<QuoteRealMObject> query = Mockito.mock(RealmQuery.class);
        RealmQuery<QuoteRealMObject> filteredQuery = Mockito.mock(RealmQuery.class);
        Mockito.when(mockedRealm.where(QuoteRealMObject.class)).thenReturn(query);
        Mockito.when(query.findAll()).thenReturn(result);
        Mockito.when(result.iterator()).thenReturn(resultIterator);
        List<Quote> favQuotes  = store.getFavouriteQuotes();
        Assert.assertEquals("Fav quote's coutn is not matching",
                objects.size(), favQuotes.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullQuoteToFavourite() {
        store.addToFavourite(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addQuoteWithNegativeIdToFavourite() {
        Quote quot = new Quote();
        quot.setId(-1);
        store.addToFavourite(quot);
    }

    @Test
    public void addToFavourite() {
        Quote quot = new Quote();
        quot.setId(1);
        store.addToFavourite(quot);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullQuoteFromFavourite() {
        store.removeFromFavourite(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeQuoteWithNegativeIdFromFavourite() {
        Quote quot = new Quote();
        quot.setId(-1);
        store.removeFromFavourite(quot);
    }

    /*@Test
    public void removeFromFavourite() throws Exception {
        Quote quot = new Quote();
        quot.setId(1);
        RealmQuery<QuoteRealMObject> query = Mockito.mock(RealmQuery.class);
        RealmQuery<QuoteRealMObject> filteredQuery = Mockito.mock(RealmQuery.class);
        Mockito.when(mockedRealm.where(QuoteRealMObject.class)).thenReturn(query);
        Mockito.when(query.equalTo(QuoteRealMObject.ID, 1)).thenReturn(filteredQuery);
        QuoteRealMObject mockedObject = Mockito.mock(QuoteRealMObject.class);
        Mockito.when(filteredQuery.findFirst()).thenReturn(mockedObject);
        PowerMockito.doNothing().when(store, "deleteObject", mockedObject);
        PowerMockito.verifyPrivate(store).invoke("deleteObject", mockedObject);
        store.removeFromFavourite(quot);
    }*/
}