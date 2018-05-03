package com.abc.s7.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IpsApiClientApplicationTest {

    @Autowired
    private IpsApiClient ipsApiClient;

    @Test
    public void searchAssetsByMetadata() {
        try {
            assertThat(ipsApiClient.searchAssetsByMetadata("ABC/Site Assets/"))
                    .isNotNull();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFolderTree() {
        try {
            assertThat(ipsApiClient.getFolderTree("/", -1))
                    .isNotNull();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
