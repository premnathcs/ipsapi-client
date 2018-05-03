package com.abc.s7.client;

import com.scene7.ipsapi.AuthenticationFaultException;
import com.scene7.ipsapi.AuthorizationFaultException;
import com.scene7.ipsapi.IpsApiFaultException;
import com.scene7.ipsapi.IpsApiPortType;
import com.scene7.ipsapi.xsd.AuthHeader;
import com.scene7.ipsapi.xsd._2013_02_15.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IpsApiClient {

    @Autowired
    private IpsApiPortType ipsApiServiceProxy;

    @Value("${scene7.abc.companyHandle}")
    private String companyHandle;

    @Value("${scene7.abc.userid}")
    private String userid;

    @Value("${scene7.abc.password}")
    private String password;

    private AuthHeader createAuthHeader() {
        AuthHeader header = new AuthHeader();
        header.setUser(this.userid);
        header.setPassword(this.password);
        return header;
    }

    public SearchAssetsByMetadataReturn searchAssetsByMetadata(String folder) throws AuthenticationFaultException, AuthorizationFaultException, IpsApiFaultException {
        ObjectFactory factory = new ObjectFactory();
        SearchAssetsByMetadataParam searchAssetsByMetadataParam = factory.createSearchAssetsByMetadataParam();
        searchAssetsByMetadataParam.setCompanyHandle(companyHandle);
        SearchFilter searchFilter = factory.createSearchFilter();
        searchFilter.setFolder(folder);
        searchFilter.setIncludeSubfolders(false);
        StringArray array = factory.createStringArray();
        array.getItems().add("Image");
        searchFilter.setAssetTypeArray(array);
        searchAssetsByMetadataParam.setFilters(searchFilter);
        return ipsApiServiceProxy.searchAssetsByMetadata(searchAssetsByMetadataParam, createAuthHeader());
    }

    public GetFolderTreeReturn getFolderTree(String folderPath, int depth) throws AuthenticationFaultException, AuthorizationFaultException, IpsApiFaultException {
        ObjectFactory factory = new ObjectFactory();
        GetFolderTreeParam getFolderTree = factory.createGetFolderTreeParam();
        getFolderTree.setCompanyHandle(companyHandle);
        getFolderTree.setFolderPath(folderPath);
        getFolderTree.setDepth(depth);
        return ipsApiServiceProxy.getFolderTree(getFolderTree, createAuthHeader());
    }
}
