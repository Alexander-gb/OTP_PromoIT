В приложении реализована возможность генерации защитного кода и отправка его на почту.
Для запуска приложения необходимо прописать свои данные от почты Google в application.properties
spring.mail.username=test@google.com
spring.mail.password=testpassword

В Postman заходим на http://localhost:8080/api/auth/register и в боди вносим: username, password,email
Например:
{
    "username": "frosty",
    "password": "frosty123",
    "email": "frosty.qwerty@yandex.ru"

}

Далее переходим на http://localhost:8080/api/operations, выбираем Basic Auth и указываем наш логин/пароль,
затем в body указываем почту для отправки кода:
{
    "description": "Test operation ",
    "email": "frosty.qwerty@yandex.ru"
}

Затем для подтверждения заходим на http://localhost:8080/api/operations/verify и указываем полученный
шестизначный код на почту:

{
    "email": "frosty.qwerty@yandex.ru",
    "code": "873556"
}



Автор:
Сизов А.С.
tg@frostyklol