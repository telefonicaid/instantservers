/*

(c) Copyright 2011 Telefonica, I+D. Printed in Spain (Europe). All Rights
Reserved.

The copyright to the software program(s) is property of Telefonica I+D.
The program(s) may be used and or copied only with the express written
consent of Telefonica I+D or in accordance with the terms and conditions
stipulated in the agreement/contract under which the program(s) have
been supplied.

 */
package com.tdigital.instantservers.api.customer;

import com.tdigital.instantservers.api.BaseAPIClient;
import com.tdigital.instantservers.api.InstantServersApiException;
import com.tdigital.instantservers.model.Empty;
import com.tdigital.instantservers.model.customer.Customer;
import com.tdigital.instantservers.model.customer.CustomerKey;
import com.tdigital.instantservers.utils.JSONUtils;

import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * CAPI.
 */
public class CustomerAPIClient extends BaseAPIClient {

    public CustomerAPIClient(String login, String password, String endpoint) {
        super(login, password, endpoint);
    }

    /**
     * Obtains all the customers. Use with caution because it can be very slow.
     */
    public Customer[] listCustomers() throws InstantServersApiException {
        return get("/customers", Customer[].class).getValue();
    }

    public Customer getCustomer(String customerId) throws InstantServersApiException {
        return get("/customers/{id}", Customer.class, customerId).getValue();
    }

    /**
     * Must send at least login, email_address, password and password_confirmation.
     * http://sdcdoc.joyent.com/sdcdoc/display/sdc/Working+with+Customers+Using+CAPI
     * @param data
     * @return
     * @throws com.tdigital.instantservers.api.InstantServersApiException
     */
    public Customer createCustomer(Customer data) throws InstantServersApiException {
        if (data.getPassword() == null || data.getPassword().length() < 6) {
            throw new IllegalArgumentException("Customer passwords must be at least six characters long");
        } else {
            if (data.getPassword().equals(data.getPasswordConfirmation()) == false) {
                throw new IllegalArgumentException("Customer passwords must match");
            }
        }

        Map<String, Object> formParams = new HashMap<String, Object>();
        try {
            tryAddParam("customer", JSONUtils.serialize(data), formParams);
        } catch (IOException e) {
            throw new IOError(e); // not recoverable error
        }

        return post("/customers", Customer.class, formParams).getValue();
    }

    public void activateCustomer(String customerId) throws InstantServersApiException {
        post("/customers/{id}/activate", null, Empty.class, null, customerId).getValue();
    }

    public Customer updateCustomer(String customerId, Customer data) throws InstantServersApiException {
        Map<String, Object> formParams = new HashMap<String, Object>();
        try {
            tryAddParam("customer", JSONUtils.serialize(data), formParams);
        } catch (IOException e) {
            throw new IOError(e); // not recoverable error
        }

        return put("/customers/{id}", null, Customer.class, formParams, customerId).getValue();
    }

    public void deleteCustomer(String customerId) throws InstantServersApiException {
        delete("/customers/{id}", customerId);
    }

    public CustomerKey[] listKeys(String customerId) throws InstantServersApiException {
        return get("/customers/{id}/keys", CustomerKey[].class, customerId).getValue();
    }

    public CustomerKey getKey(String customerId, String name) throws InstantServersApiException {
        return get("/customers/{id}/keys/{name}", CustomerKey.class, customerId, name).getValue();
    }

    public CustomerKey createKey(String customerId, String name, String key) throws InstantServersApiException {
        Map<String, Object> formParams = new HashMap<String, Object>();
        tryAddParam("name", name, formParams);
        tryAddParam("key", key, formParams);
        return post("/customers/{id}/keys", null, CustomerKey.class, formParams, customerId).getValue();
    }

    public void deleteKey(String customerId, String name) throws InstantServersApiException {
        delete("/customers/{id}/keys/{name}", customerId, name);
    }

    // http://apidocs.joyent.com/sdcapidoc/capi/CustomersApi/Resource/CustomersMetadata.html#PUT___customers__customer_id_metadata__app_key__name_-instance_route
    public void setCustomerMetadata(String customerId, String appKey, HashMap<String,String> metadataMap) throws InstantServersApiException {
        for (Map.Entry<String, String> metadata : metadataMap.entrySet()) {
            put("/customers/{id}/metadata/{appKey}/{name}",
                    metadata.getValue(), Void.class, null, customerId, appKey, metadata.getKey());
        }
    }
    
    public HashMap<String, String> getCustomerMetadata(String customerId, String appKey) throws InstantServersApiException {
        HashMap<String, String> appMetadata = new HashMap<String, String>();
        String[] appMetadataKeys = null;

        try {
            appMetadataKeys = get("/customers/{id}/metadata/{app_key}", String[].class, customerId, appKey).getValue();
            for (String tokenKey : appMetadataKeys) {
                String tokenValue = get("/customers/{id}/metadata/{app_key}/{token}", String.class, customerId, appKey, tokenKey).getValue();
                appMetadata.put(tokenKey, tokenValue);
            }
        } catch (InstantServersApiException ex) {
            if (ex.getStatusCode() == 404) { //metadata not found for appKey
                return appMetadata;
            } else {
                throw ex;
            }
        }
        return appMetadata;

    }

}
