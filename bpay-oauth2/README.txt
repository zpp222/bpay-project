1、授权码（认证码）模式 response_type=code
http://localhost:9999/auth/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://baidu.com
2、token
Authorization: Basic Auth (client/secret)
post http://localhost:9999/auth/oauth/token
x-www-form-urlencoded (header为Content-Type: application/x-www-form-urlencoded)
body:
client:client
secret:secret
grant_type:authorization_code
code:yi0Rre
redirect_uri:http://baidu.com
scope:openid
2、客户端模式 grant_type=client_credential
x-www-form-urlencoded (header为Content-Type: application/x-www-form-urlencoded)
post http://localhost:9999/auth/oauth/token
Authorization: Basic Auth (app/secret)
body:
grant_type:client_credentials
scope:openid
or post request :
post http://localhost:9999/auth/oauth/token?grant_type=client_credentials&client_id=app&client_secret=secret
3、简化（隐形）Impilict模式 response_type=token
browser: http://localhost:9999/auth/oauth/authorize?response_type=token&client_id=h5&redirect_uri=https://baidu.com
4、用户名密码模式 grant_type=password
post http://localhost:9999/auth/oauth/token?grant_type=password&client_id=app&client_secret=secret&username=zpp&password=zpp!

5.refresh_token
post http://localhost:9999/auth/oauth/token?grant_type=refresh_token&refresh_token=cf3cd5a1-63d4-453a-ba65-448c3b3b5f8f&client_id=app&client_secret=secret