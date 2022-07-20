## Oauth2 Client in Spring-Security-Oauth2

1. Attempt authorization 失败，将抛出AccessDeniedException -- FilterSecurityInterceptor(AbstractSecurityInterceptor)
2. 捕获上一步抛出AccessDeniedException异常，将重定向到鉴权endpoint(/authorization/authorizationserver)  --ExceptionTranslationFilter