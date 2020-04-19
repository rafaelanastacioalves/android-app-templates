package com.example.rafaelanastacioalves.moby.vo;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MainEntityTest {


    @Test
    public void should_giveBasicProperties_WhenMainEntityCreatedOnlyWithTitle(){

        String testeString = "Teste";
        MainEntity testedMainEntitty = new MainEntity(testeString);
        assertThat(testedMainEntitty.getId(), is(MainEntity.defaultId));
        assertThat(testedMainEntitty.getTitle(), is(equalTo(testeString)));
        assertThat(testedMainEntitty.getImage_url(), is(MainEntity.defaultUrlDeclaration));
    }



}