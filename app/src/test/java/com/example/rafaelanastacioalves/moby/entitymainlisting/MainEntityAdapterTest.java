package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.content.Context;

import com.example.rafaelanastacioalves.moby.vo.MainEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class MainEntityAdapterTest {

    @Mock
    private Context context;
    @Spy
    private MainEntityAdapter adapter = new MainEntityAdapter(context);

    @Test
    public void should_UpdateMainEntityList_WhenSetItemList() {
        doNothing().when(adapter).updateList();

        List<MainEntity> mockedMainEntityList = new ArrayList<>(Arrays.asList(
                new MainEntity("Entity 1"),
                new MainEntity("Entity 2")
        ));

        adapter.setItems(mockedMainEntityList);

        verify(adapter).updateList();
        int itemCount = adapter.getItemCount();
        assertThat(itemCount, is(2));
    }
}