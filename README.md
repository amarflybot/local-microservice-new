Welcome to Spring Cloud Services with OAuth2!
===================

<p>Rest if the servers are simple Spring Cloud service.</p>
<p>The Zull Proxy Gateway is now oauth Protected</p>
<p>The oauth-server is the authentication server</p>


Test Oauth Server
-------------
- Step1:
http://localhost:9191/uaa/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://example.com
Basic Auth: Mentioned as per Account Database

- Step2:
Get code

- Step3:

    POST: 
    acme:acmesecret@localhost:9191/uaa/oauth/token
    
    Body Type:   x-www-form-urlencoded
    
    Body Key Value:
    
    | KEY     | VALUE 		|
    | :------------ | ----------------------: | 
    | grant_type | authorization_code |
    | client_id    | acme   |
    | redirect_uri     | http://example.com    |
    | code     | {code}    |

- Step4:
    
    This shall return:
    {"access_token":"e6d574d3-83da-4008-964a-243402dbffaa","token_type":"bearer","refresh_token":"e0993c4e-37f7-4222-9c3e-f6030b6ecece","expires_in":43199,"scope":"openid"}

- Step5:
    
    GET:
    localhost:8032/yahoo/INTC
    
    Header:
    
    | KEY     | VALUE 		|
    | :------------ | ----------------------: | 
    | Authorization | Bearer {access_token} |
    
- Step6: 
    This is it!!