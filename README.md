# Дипломка по Scalafix + FP Laws

## Структура проекта

- `scalafix/input/src/main/scala/test/{Rule}.scala`

Входные данные для проверки работоспособности rewrite-правила `{Rule}`

- `scalafix/output/src/main/scala/test/{Rule}.scala`

Ожидаемый результат выполненяи rewrite-правила `{Rule}`

- `scalafix/rules/src/main/scala/fix/{Rule}.scala`

Описание rewrite-правила `{Rule}`

- `scalafix/rules/src/main/resources/META-INF/services/scalafix.v1.Rule`

Список всех правил

## Проверить работоспособность

~~~
cd scalafix
sbt ~tests/test
~~~

## Реализованные правила

- `Functor[F].as(fa, ())` -> `Functor[F].void(fa)`
