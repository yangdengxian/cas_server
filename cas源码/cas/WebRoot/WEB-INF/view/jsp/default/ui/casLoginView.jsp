<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<jsp:directive.include file="includes/top.jsp" />
<%@ page pageEncoding="gb2312" %>
<%@ page contentType="text/html; charset=gb2312" %>
<c:if test="${not pageContext.request.secure}">
</c:if>
<div class="fore-login-main">
      <div class="fore-login-logo"></div>
      <div class="fore-login-form-bg clear">
      		<div class="fore-login-form">
      		<h2 class="fore-login-form-title">请输入用户名与密码</h2>
			<form:form method="post" id="fore-sys-login-form" cssClass="fm-v clearfix" commandName="${commandName}" htmlEscape="true">
                 
                <!-- <spring:message code="screen.welcome.welcome" /> -->
				

                      
						<c:if test="${not empty sessionScope.openIdLocalId}">
						<strong>${sessionScope.openIdLocalId}</strong>
						<label>用户名：</label>
						<input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}" />
						</c:if>

						<c:if test="${empty sessionScope.openIdLocalId}">
						
						<spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />
					
				    		<div class="fore-login-input-bg clear">
									<label class="fore-login-username" for="username"></label>
									<form:input class="fore-login-input" placeholder="请输入用户名" type="text" id="username"  tabindex="1" accesskey="${userNameAccessKey}" path="username" />
							</div>
				    												
						</c:if>

					<div class="fore-login-input-bg clear">
						<label class="fore-login-password" for="password"></label>
						<spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey" />
						<input name="password" type="password"							
						 class="fore-login-input" placeholder="请输入密码"
						 id="password"  path="password"  accesskey="${passwordAccessKey}" autocomplete="off" />
					</div>
					<!-- <form:errors path="*" id="msg" cssClass="errors" element="div" /> -->
                    <div class="fore-login-btn-box clear">
						<input type="hidden" name="lt" value="${loginTicket}" />
						<input type="hidden" name="execution" value="${flowExecutionKey}" />
						<input type="hidden" name="_eventId" value="submit" />
						<input name="button"  accesskey="l"  type="submit" value="登 录" 
						class="fore-login-btn dl"/>
                    </div>
                    <form:errors path="*" id="msg" cssClass="errors" element="div" 
					style="color:red;width:200px;margin:0 auto;"/>
            </form:form>
          </div>
     </div>
     <div class="fore-login-msg clear">
     	<h2 class="fore-login-msg-browser">建议使用IE9以上浏览器或者谷歌浏览器访问，三维视图仅支持IE9以上浏览器
     	</h2>
     	<h2 class="fore-login-msg-org">版权所有：梅州林业局</h2>
     </div>
</div>
<style type="text/css">
	.fore-login-msg {
		position: relative;
	    width: 666px;
	    height: 100px;
	    top: 450px;
	    left: 50%;
	    margin-left: -333px;
	}
	.fore-login-msg h2{
		color: #FFF;
		text-align: center;
		text-shadow: 0 0 0.1em #000;
	}
</style>

            
           
