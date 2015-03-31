/**
 * Copyright 2010-2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.bdawg.simplerecipemanager.utils;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.regions.Regions;

import java.util.HashMap;
import java.util.Map;

public class CognitoSyncClientUtil {

    private static final String TAG = "CognitoSyncClientUtil";

    /**
     * Enter here the Identity Pool associated with your app and the AWS
     * region where it belongs. Get this information from the AWS console.
     */

    private static final String IDENTITY_POOL_ID = "us-east-1:866d80cc-24e2-4c83-b6b4-f1c722310e23";
    private static final Regions REGION = Regions.US_EAST_1;
    protected static CognitoCachingCredentialsProvider credentialsProvider = null;
    private static CognitoSyncManager syncClient;

    private CognitoSyncClientUtil() {

    }

    /**
     * Initializes the Cognito Identity and Sync clients. This must be called before getInstance().
     *
     * @param context a context of the app
     */
    public static void init(Context context) {

        if (syncClient != null) {
            return;
        }

        credentialsProvider = new CognitoCachingCredentialsProvider(context, IDENTITY_POOL_ID,
                REGION);
        syncClient = new CognitoSyncManager(context, REGION, credentialsProvider);
    }

    /**
     * Sets the login so that you can use authorized identity. This requires a
     * network request, so you should call it in a background thread.
     *
     * @param providerName the name of the external identity provider
     * @param token        openId token
     */
    public static void addLogins(String providerName, String token) {
        if (syncClient == null) {
            throw new IllegalStateException("CognitoSyncClientManager not initialized yet");
        }
        if (token == null){
            return;
        }
        Map<String, String> logins = credentialsProvider.getLogins();
        if (logins == null) {
            logins = new HashMap<String, String>();
        }

        logins.put(providerName, token);
        credentialsProvider.setLogins(logins);
    }

    /**
     * Gets the singleton instance of the CognitoClient. init() must be called
     * prior to this.
     *
     * @return an instance of CognitoClient
     */
    public static CognitoSyncManager getInstance() {
        if (syncClient == null) {
            throw new IllegalStateException("CognitoSyncClientManager not initialized yet");
        }
        return syncClient;
    }

    /**
     * Returns a credentials provider object
     *
     * @return
     */
    public static CognitoCredentialsProvider getCredentialsProvider() {
        return credentialsProvider;
    }

    public static boolean hasAuthorizedLogins() {
        if (syncClient == null) {
            throw new IllegalStateException("CognitoSyncClientManager not initialized yet");
        }
        if (credentialsProvider.getLogins().size() == 0){
            return false;
        } else {
            boolean hadValid = false;
            for (Map.Entry<String, String> entry: credentialsProvider.getLogins().entrySet()){
                if (entry.getKey() != null && entry.getValue() != null){
                    hadValid=true;
                }

            }
            return hadValid;
        }
    }
}
