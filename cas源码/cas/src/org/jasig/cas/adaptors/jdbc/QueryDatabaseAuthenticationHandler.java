/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.cas.adaptors.jdbc;

import javax.validation.constraints.NotNull;

import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.forestar.cas.CompareDate;
import com.forestar.cas.UserOutErrorException;

/**
 * Class that if provided a query that returns a password (parameter of query
 * must be username) will compare that password to a translated version of the
 * password provided by the user. If they match, then authentication succeeds.
 * Default password translator is plaintext translator.
 * 
 * @author Scott Battaglia
 * @author Dmitriy Kopylenko
 * @version $Revision$ $Date$
 * @since 3.0
 */
public class QueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

    @NotNull
    private String sql;

    @SuppressWarnings("deprecation")
	protected final boolean authenticateUsernamePasswordInternal(final UsernamePasswordCredentials credentials) throws AuthenticationException {
        final String username = getPrincipalNameTransformer().transform(credentials.getUsername());
        final String password = credentials.getPassword();
        
        
        final String encryptedPassword = this.getPasswordEncoder().encode(
            password);
        final String SqlType = "SELECT C_TYPE FROM fs_pt_yw_base_user WHERE C_USERNAME=?";
        final String SqlOutTime = "SELECT OUTTIME FROM fs_pt_yw_base_user WHERE C_USERNAME=?";
        String type = "1";
        String outtime = "";
        
        try {
        	
            final String dbPassword = getJdbcTemplate().queryForObject(this.sql, String.class, username);
            type = getJdbcTemplate().queryForObject(SqlType, String.class, username);
            outtime = getJdbcTemplate().queryForObject(SqlOutTime, String.class, username);
            //验证用户是否过期
            if ("2".equals(type) && CompareDate.isOutTime(outtime)) {
            	throw new UserOutErrorException();
            } else {
            	return dbPassword.equals(encryptedPassword);
            }
            
        } catch (final IncorrectResultSizeDataAccessException e) {
            // this means the username was not found.
            return false;
        }
    }

    /**
     * @param sql The sql to set.
     */
    public void setSql(final String sql) {
        this.sql = sql;
    }
}
