/**
 * Copyright 2011 Archfirst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.archfirst.bfoms.restservice.account;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.archfirst.bfcommon.restutils.Link;
import org.archfirst.bfoms.domain.account.brokerage.BrokerageAccountService;
import org.archfirst.bfoms.domain.account.brokerage.BrokerageAccountSummary;

/**
 * BrokerageAccountsResource
 *
 * @author Naresh Bhatia
 */
@Stateless
@Path("/secure/brokerage_accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BrokerageAccountsResource {

    @POST
    public Response createBrokerageAccount(
            @Context SecurityContext sc,
            CreateBrokerageAccountRequest request) {
        
        Long id = brokerageAccountService.openNewAccount(
                getUsername(sc), request.getAccountName());

        // Return link to self
        Link self = new Link(uriInfo, Long.toString(id));
        return Response.created(self.getUri()).entity(self).build();
    }
    
    private String getUsername(SecurityContext sc) {
        return sc.getUserPrincipal().getName();
    }

    @GET
    public List<BrokerageAccountSummary> getBrokerageAccountSummaries(
            @Context SecurityContext sc) {
        return this.brokerageAccountService.getAccountSummaries(getUsername(sc));
    }
    
    // ----- Helper Classes -----
    private static class CreateBrokerageAccountRequest {
        private String accountName;
        public String getAccountName() {
            return accountName;
        }
        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }
    }

    // ----- Attributes -----
    @Context
    private UriInfo uriInfo;

    @Inject
    private BrokerageAccountService brokerageAccountService;
}