/*
 * Copyright (c) 2020, Oracle and/or its affiliates.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2.0, as published by the
 * Free Software Foundation.
 *
 * This program is also distributed with certain software (including but not
 * limited to OpenSSL) that is licensed under separate terms, as designated in a
 * particular file or component or in included license documentation. The
 * authors of MySQL hereby grant you an additional permission to link the
 * program and your derivative works with the separately licensed software that
 * they have included with MySQL.
 *
 * Without limiting anything contained in the foregoing, this file, which is
 * part of MySQL Connector/J, is also subject to the Universal FOSS Exception,
 * version 1.0, a copy of which can be found at
 * http://oss.oracle.com/licenses/universal-foss-exception.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License, version 2.0,
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.mysql.cj.sasl;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslClientFactory;
import javax.security.sasl.SaslException;

import com.mysql.cj.util.StringUtils;

/**
 * A {@link SaslClientFactory} for {@link ScramSha1SaslClient} instances.
 */
public class ScramSha1SaslClientFactory implements SaslClientFactory {
    private static final String[] SUPPORTED_MECHANISMS = { ScramSha1SaslClient.MECHANISM_NAME };

    @Override
    public SaslClient createSaslClient(String[] mechanisms, String authorizationId, String protocol, String serverName, Map<String, ?> props,
            CallbackHandler cbh) throws SaslException {
        for (String mech : mechanisms) {
            if (mech.equals(ScramSha1SaslClient.MECHANISM_NAME)) {
                return new ScramSha1SaslClient(authorizationId, getUsername(authorizationId, cbh), getPassword(cbh));
            }
        }
        return null;
    }

    @Override
    public String[] getMechanismNames(Map<String, ?> props) {
        return SUPPORTED_MECHANISMS.clone();
    }

    /**
     * Gets the authentication id, which is provided by a {@link CallbackHandler} to be implemented by the requester of this model.service.
     * 
     * @param authorizationId
     *            the authorization id that can be used as default authentication id if none is provided.
     * @param cbh
     *            the callback handler to use.
     * @return
     *         an authentication id
     * @throws SaslException
     *             if the callback handler is null or not supported by the callback handler implementer.
     */
    private String getUsername(String authorizationId, CallbackHandler cbh) throws SaslException {
        if (cbh == null) {
            throw new SaslException("Callback handler required to get username.");
        }

        try {
            String prompt = ScramSha1SaslClient.MECHANISM_NAME + " authentication id:";
            NameCallback ncb = StringUtils.isNullOrEmpty(authorizationId) ? new NameCallback(prompt) : new NameCallback(prompt, authorizationId);
            cbh.handle(new Callback[] { ncb });

            String userName = ncb.getName();
            return userName;
        } catch (IOException | UnsupportedCallbackException e) {
            throw new SaslException("Cannot get username", e);
        }
    }

    /**
     * Gets the password, which is provided by a {@link CallbackHandler} to be implemented by the requester of this model.service.
     * 
     * @param cbh
     *            the callback handler to use.
     * @return
     *         a password
     * @throws SaslException
     *             if the callback handler is null or not supported by the callback handler implementer.
     */
    private String getPassword(CallbackHandler cbh) throws SaslException {
        if (cbh == null) {
            throw new SaslException("Callback handler required to get password.");
        }

        try {
            String prompt = ScramSha1SaslClient.MECHANISM_NAME + " password:";
            PasswordCallback pcb = new PasswordCallback(prompt, false);
            cbh.handle(new Callback[] { pcb });

            String password = new String(pcb.getPassword());
            pcb.clearPassword();
            return password;
        } catch (IOException | UnsupportedCallbackException e) {
            throw new SaslException("Cannot get password", e);
        }
    }
}
